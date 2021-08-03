# Define Paths looking from root of this project.
prebuiltRoot = "prebuild-liboqs"
liboqsScriptsDir = "${prebuiltRoot}/liboqs/scripts"
liboqsBuildDir = "${prebuiltRoot}/liboqs/build"
ndkPath = "${prebuiltRoot}/android-ndk-r21"
jniLibDir = "jni/jniLibs"

# Get sources
cd $prebuiltRoot/liboqs
git clone --recursive -j8 -n https://github.com/open-quantum-safe/liboqs.git

# Get Tools
cd $prebuiltRoot
apt-get update # Not necessary as with docker we are up to date
wget https://dl.google.com/android/repository/android-ndk-r21-linux-x86_64.zip
apt-get install unzip
unzip android-ndk-r21-linux-x86_64.zip
apt-get install -y build-essential cmake libssl-dev

# Build
cd $liboqsScriptsDir

# declare -a arr=("x86" "x86_64" "arm64-v8a" "armeabi-v7a") # 32 Bit is currently not working.
declare -a arr=("x86_64" "arm64-v8a")
sdkVersion = 19

for abi in "${arr[@]}"
do
	# turn off errors as different abis might compile differently well.
	set -e
	build-android.sh $ndkPath -s $sdkVersion -a $abi
	liboqsFile = "${liboqsBuildDir}/liboqs.so"
	abiFile = "${jniLibDir}/${abi}/liboqs.so"
	mv $liboqsFile $abiFile  # TODO: Is the order of target and source correct?
done
# TODO: Copy include folder

# You can access them using echo "${arr[0]}", "${arr[1]}" also