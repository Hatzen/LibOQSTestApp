#!/usr/bin/env bash
set -euo pipefail

# Android SDK cmdline-tools require JDK 17+, whereas Gradle 6.5 / AGP 4.1.0 require JDK 8 or 11.
if [[ -n "${GRADLE_JAVA_HOME:-}" ]]; then
  export JAVA_HOME="${GRADLE_JAVA_HOME}"
  export PATH="${JAVA_HOME}/bin:${PATH}"
elif [[ -n "${JAVA_HOME_11_X64:-}" ]]; then
  export JAVA_HOME="${JAVA_HOME_11_X64}"
  export PATH="${JAVA_HOME}/bin:${PATH}"
fi

adb devices -l || true

if ./gradlew connectedCheck --no-daemon; then
  echo "connectedDebugAndroidTest succeeded" >&2
else
  { adb logcat -d | tail -n 400; } || true
  exit 1
fi
