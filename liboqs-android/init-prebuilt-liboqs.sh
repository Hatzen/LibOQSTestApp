#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ROOT_DIR="$(cd "${SCRIPT_DIR}/.." && pwd)"

PREBUILT_ROOT="${SCRIPT_DIR}/prebuild-liboqs"
LIBOQS_JAVA_DIR="${PREBUILT_ROOT}/liboqs-java"
LIBOQS_DIR="${PREBUILT_ROOT}/liboqs"

JNI_DIR="${SCRIPT_DIR}/jni/jni"
JNI_LIB_DIR="${SCRIPT_DIR}/jni/jniLibs"
JNI_INCLUDE_DIR="${SCRIPT_DIR}/jni/include/oqs"
JAVA_SRC_DIR="${SCRIPT_DIR}/src/main/java"
JAVA_OQS_DIR="${JAVA_SRC_DIR}/org/openquantumsafe"

SDK_VERSION="${SDK_VERSION:-21}"
ABIS_INPUT="${ABIS:-armeabi-v7a arm64-v8a x86 x86_64}"
NDK_PATH="${ANDROID_NDK_LATEST_HOME:-${ANDROID_NDK_HOME:-${ANDROID_NDK_ROOT:-${ANDROID_NDK:-}}}}"
USER_CMAKE_FLAGS="${EXTRA_CMAKE_FLAGS:-}"
CLEAN=false
LIBOQS_REPO="${LIBOQS_REPO:-https://github.com/open-quantum-safe/liboqs.git}"
LIBOQS_JAVA_REPO="${LIBOQS_JAVA_REPO:-https://github.com/open-quantum-safe/liboqs-java.git}"
LIBOQS_REF="${LIBOQS_REF:-}"
LIBOQS_JAVA_REF="${LIBOQS_JAVA_REF:-}"

show_help() {
    cat <<EOF
Usage: ./init-prebuilt-liboqs.sh [options]

Options:
  -n, --ndk <dir>           Path to Android NDK directory (auto-detected if omitted)
  -a, --abis <abis>         Space or comma-separated Android ABIs (default: "armeabi-v7a arm64-v8a x86 x86_64")
  -s, --sdk <version>       Minimum Android SDK API level for liboqs (default: 21)
  -f, --cmake-flags <flags> Additional CMake flags passed to liboqs build (env: EXTRA_CMAKE_FLAGS)
  -c, --clean               Remove existing prebuild-liboqs clones before cloning fresh
  -h, --help                Show this help message
EOF
}

while [[ $# -gt 0 ]]; do
    case "$1" in
        -n|--ndk)
            NDK_PATH="$2"
            shift 2
            ;;
        -a|--abis)
            ABIS_INPUT="$2"
            shift 2
            ;;
        -s|--sdk)
            SDK_VERSION="$2"
            shift 2
            ;;
        -f|--cmake-flags)
            USER_CMAKE_FLAGS="$2"
            shift 2
            ;;
        -c|--clean)
            CLEAN=true
            shift
            ;;
        -h|--help)
            show_help
            exit 0
            ;;
        *)
            echo "Unknown option: $1" >&2
            show_help
            exit 1
            ;;
    esac
done

to_unix_path() {
    local p="$1"
    if command -v cygpath >/dev/null 2>&1; then
        cygpath -u "$p"
    else
        printf '%s\n' "$p"
    fi
}

to_cmake_path() {
    local p="$1"
    if command -v cygpath >/dev/null 2>&1; then
        cygpath -m "$p"
    else
        printf '%s\n' "$p"
    fi
}

read_local_property() {
    local key="$1"
    local props_file="${ROOT_DIR}/local.properties"
    if [[ ! -f "${props_file}" ]]; then
        return 0
    fi
    local val
    val=$(grep -E "^${key}=" "${props_file}" | head -n 1 | cut -d'=' -f2- | tr -d '\r' || true)
    # Unescape Gradle properties (e.g., C\:\\Users\\... -> C:\Users\...)
    val="${val//\\\\/\\}"
    val="${val//\\:/:}"
    if [[ -n "${val}" ]]; then
        to_unix_path "${val}"
    fi
}

find_sdk_root() {
    local candidates=()
    [[ -n "${ANDROID_HOME:-}" ]] && candidates+=("$(to_unix_path "${ANDROID_HOME}")")
    [[ -n "${ANDROID_SDK_ROOT:-}" ]] && candidates+=("$(to_unix_path "${ANDROID_SDK_ROOT}")")

    local prop_sdk
    prop_sdk="$(read_local_property "sdk.dir")"
    [[ -n "${prop_sdk}" ]] && candidates+=("${prop_sdk}")

    if [[ -n "${LOCALAPPDATA:-}" ]]; then
        candidates+=("$(to_unix_path "${LOCALAPPDATA}")/Android/Sdk")
    fi
    candidates+=(
        "${HOME}/Android/Sdk"
        "${HOME}/Library/Android/sdk"
        "/usr/local/lib/android/sdk"
        "/Users/runner/Library/Android/sdk"
    )

    for dir in "${candidates[@]}"; do
        if [[ -n "${dir}" && -d "${dir}" ]]; then
            printf '%s\n' "${dir}"
            return 0
        fi
    done
    return 1
}

is_valid_ndk() {
    local dir="$1"
    [[ -n "${dir}" && -f "${dir}/build/cmake/android.toolchain.cmake" ]]
}

find_ndk() {
    if [[ -n "${NDK_PATH}" ]]; then
        local resolved
        resolved="$(to_unix_path "${NDK_PATH}")"
        if is_valid_ndk "${resolved}"; then
            printf '%s\n' "${resolved}"
            return 0
        fi
        echo "Warning: Provided NDK path is invalid: ${NDK_PATH}" >&2
    fi

    local prop_ndk
    prop_ndk="$(read_local_property "ndk.dir")"
    if is_valid_ndk "${prop_ndk}"; then
        printf '%s\n' "${prop_ndk}"
        return 0
    fi

    if [[ -n "${SDK_ROOT:-}" ]]; then
        if [[ -d "${SDK_ROOT}/ndk" ]]; then
            local latest_ndk
            latest_ndk=$(ls -1 "${SDK_ROOT}/ndk" 2>/dev/null | sort -V | tail -n 1 || true)
            if [[ -n "${latest_ndk}" ]] && is_valid_ndk "${SDK_ROOT}/ndk/${latest_ndk}"; then
                printf '%s\n' "${SDK_ROOT}/ndk/${latest_ndk}"
                return 0
            fi
        fi
        if is_valid_ndk "${SDK_ROOT}/ndk-bundle"; then
            printf '%s\n' "${SDK_ROOT}/ndk-bundle"
            return 0
        fi
    fi

    local prebuilt_ndk
    for prebuilt_ndk in "${PREBUILT_ROOT}"/android-ndk-*; do
        if is_valid_ndk "${prebuilt_ndk}"; then
            printf '%s\n' "${prebuilt_ndk}"
            return 0
        fi
    done

    if [[ "$(uname -s)" == "Linux" ]]; then
        local ndk_version="r26d"
        local ndk_zip="android-ndk-${ndk_version}-linux.zip"
        local ndk_dir="${PREBUILT_ROOT}/android-ndk-${ndk_version}"
        echo "No installed Android NDK found. Downloading Android NDK ${ndk_version}..." >&2
        mkdir -p "${PREBUILT_ROOT}"
        if command -v curl >/dev/null 2>&1; then
            curl -fSL -o "${PREBUILT_ROOT}/${ndk_zip}" "https://dl.google.com/android/repository/${ndk_zip}"
        elif command -v wget >/dev/null 2>&1; then
            wget -q -O "${PREBUILT_ROOT}/${ndk_zip}" "https://dl.google.com/android/repository/${ndk_zip}"
        else
            echo "Error: Neither curl nor wget is available to download the Android NDK." >&2
            return 1
        fi
        unzip -q -o "${PREBUILT_ROOT}/${ndk_zip}" -d "${PREBUILT_ROOT}"
        rm -f "${PREBUILT_ROOT}/${ndk_zip}"
        if is_valid_ndk "${ndk_dir}"; then
            printf '%s\n' "${ndk_dir}"
            return 0
        fi
    fi

    return 1
}

ensure_build_tools() {
    if ! command -v cmake >/dev/null 2>&1 || ! command -v ninja >/dev/null 2>&1; then
        if [[ -n "${SDK_ROOT:-}" && -d "${SDK_ROOT}/cmake" ]]; then
            local latest_cmake
            latest_cmake=$(ls -1 "${SDK_ROOT}/cmake" 2>/dev/null | sort -V | tail -n 1 || true)
            if [[ -n "${latest_cmake}" && -d "${SDK_ROOT}/cmake/${latest_cmake}/bin" ]]; then
                export PATH="${SDK_ROOT}/cmake/${latest_cmake}/bin:${PATH}"
            fi
        fi
    fi

    if ! command -v cmake >/dev/null 2>&1; then
        echo "Error: 'cmake' was not found on PATH or inside Android SDK cmake directory." >&2
        exit 1
    fi
}

mkdir -p "${PREBUILT_ROOT}"

SDK_ROOT="$(find_sdk_root || true)"
ensure_build_tools

NDK_UNIX_PATH="$(find_ndk || true)"
if [[ -z "${NDK_UNIX_PATH}" ]]; then
    echo "Error: Android NDK not found. Install NDK via Android Studio SDK Manager, set ANDROID_NDK_HOME, or pass -n <ndk-dir>." >&2
    exit 1
fi
NDK_CMAKE_PATH="$(to_cmake_path "${NDK_UNIX_PATH}")"
echo "Using Android NDK: ${NDK_CMAKE_PATH}"

if [[ "${CLEAN}" == true ]]; then
    echo "Cleaning existing clones in ${PREBUILT_ROOT}..."
    rm -rf "${LIBOQS_JAVA_DIR}" "${LIBOQS_DIR}"
fi

# 1. Fetch and copy liboqs-java JNI C and Java files
if [[ ! -d "${LIBOQS_JAVA_DIR}/.git" ]]; then
    echo "Cloning liboqs-java..."
    if [[ -n "${LIBOQS_JAVA_REF}" ]]; then
        git clone --recursive -j8 --branch "${LIBOQS_JAVA_REF}" "${LIBOQS_JAVA_REPO}" "${LIBOQS_JAVA_DIR}"
    else
        git clone --recursive -j8 "${LIBOQS_JAVA_REPO}" "${LIBOQS_JAVA_DIR}"
    fi
else
    echo "Using existing liboqs-java checkout at ${LIBOQS_JAVA_DIR}"
fi

echo "Copying liboqs-java C and Java sources..."
rm -rf "${JNI_DIR}" "${JAVA_OQS_DIR}"
mkdir -p "${JNI_DIR}" "${JAVA_SRC_DIR}"
cp -r "${LIBOQS_JAVA_DIR}/src/main/c/." "${JNI_DIR}/"
cp -r "${LIBOQS_JAVA_DIR}/src/main/java/." "${JAVA_SRC_DIR}/"

# 2. Fetch and build liboqs for each target ABI
if [[ ! -d "${LIBOQS_DIR}/.git" ]]; then
    echo "Cloning liboqs..."
    if [[ -n "${LIBOQS_REF}" ]]; then
        git clone --recursive -j8 --branch "${LIBOQS_REF}" "${LIBOQS_REPO}" "${LIBOQS_DIR}"
    else
        git clone --recursive -j8 "${LIBOQS_REPO}" "${LIBOQS_DIR}"
    fi
else
    echo "Using existing liboqs checkout at ${LIBOQS_DIR}"
fi

if [[ -z "${CMAKE_BUILD_PARALLEL_LEVEL:-}" ]]; then
    if command -v nproc >/dev/null 2>&1; then
        export CMAKE_BUILD_PARALLEL_LEVEL="$(nproc)"
    elif command -v sysctl >/dev/null 2>&1; then
        export CMAKE_BUILD_PARALLEL_LEVEL="$(sysctl -n hw.ncpu)"
    else
        export CMAKE_BUILD_PARALLEL_LEVEL=4
    fi
fi

EXTRA_CMAKE_FLAGS="-DOQS_BUILD_ONLY_LIB=ON"
if command -v ninja >/dev/null 2>&1; then
    EXTRA_CMAKE_FLAGS="${EXTRA_CMAKE_FLAGS} -G Ninja"
fi
if [[ -n "${USER_CMAKE_FLAGS}" ]]; then
    EXTRA_CMAKE_FLAGS="${EXTRA_CMAKE_FLAGS} ${USER_CMAKE_FLAGS}"
fi

ABIS_NORMALIZED="${ABIS_INPUT//,/ }"
LAST_BUILD_DIR=""

for abi in ${ABIS_NORMALIZED}; do
    echo ""
    echo "=== Building liboqs for ABI: ${abi} (SDK ${SDK_VERSION}) ==="
    BUILD_SUBDIR="build-${abi}"
    BUILD_FULL_DIR="${LIBOQS_DIR}/${BUILD_SUBDIR}"

    bash "${LIBOQS_DIR}/scripts/build-android.sh" \
        "${NDK_CMAKE_PATH}" \
        -s "${SDK_VERSION}" \
        -a "${abi}" \
        -b "${BUILD_SUBDIR}" \
        -f "${EXTRA_CMAKE_FLAGS}"

    SO_SOURCE=""
    if [[ -f "${BUILD_FULL_DIR}/lib/liboqs.so" ]]; then
        SO_SOURCE="${BUILD_FULL_DIR}/lib/liboqs.so"
    elif [[ -f "${BUILD_FULL_DIR}/liboqs.so" ]]; then
        SO_SOURCE="${BUILD_FULL_DIR}/liboqs.so"
    else
        echo "Error: Could not find built liboqs.so in ${BUILD_FULL_DIR}" >&2
        exit 1
    fi

    mkdir -p "${JNI_LIB_DIR}/${abi}"
    cp "${SO_SOURCE}" "${JNI_LIB_DIR}/${abi}/liboqs.so"
    LAST_BUILD_DIR="${BUILD_FULL_DIR}"
done

if [[ -z "${LAST_BUILD_DIR}" || ! -d "${LAST_BUILD_DIR}/include/oqs" ]]; then
    echo "Error: Generated include/oqs headers not found in ${LAST_BUILD_DIR}" >&2
    exit 1
fi

echo "Copying generated liboqs headers to ${JNI_INCLUDE_DIR}..."
rm -rf "${JNI_INCLUDE_DIR}"
mkdir -p "${JNI_INCLUDE_DIR}"
cp -r "${LAST_BUILD_DIR}/include/oqs/." "${JNI_INCLUDE_DIR}/"

LIBOQS_COMMIT="$(git -C "${LIBOQS_DIR}" rev-parse --short HEAD) ($(git -C "${LIBOQS_DIR}" log -1 --format=%cs))"
LIBOQS_JAVA_COMMIT="$(git -C "${LIBOQS_JAVA_DIR}" rev-parse --short HEAD) ($(git -C "${LIBOQS_JAVA_DIR}" log -1 --format=%cs))"
cat > "${SCRIPT_DIR}/jni/build-info.txt" <<EOF
liboqs: ${LIBOQS_COMMIT}
liboqs-java: ${LIBOQS_JAVA_COMMIT}
abis: ${ABIS_NORMALIZED}
sdk: ${SDK_VERSION}
EOF

echo "Done! Prebuilt liboqs libraries and headers are ready."
cat "${SCRIPT_DIR}/jni/build-info.txt"

