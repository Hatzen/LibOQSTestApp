SIKE is failing with warnings/errors skipping with alg_support didnt worked. Force skipping may work but not tested yet.

root@LAPTOP-xxx:/home/hatzen/liboqs-test-android# bash scripts/build-android.sh /home/hatzen/ndk/android-ndk-r21 -s 19 -a armeabi-v7a
Valid directory for NDK at /home/hatzen/ndk/android-ndk-r21
Compiling for ABI armeabi-v7a
Compiling for SDK 19
Cleaning up previous build
Building in directory build
-- The ASM compiler identification is Clang
-- Found assembler: /home/hatzen/ndk/android-ndk-r21/toolchains/llvm/prebuilt/linux-x86_64/bin/clang
-- Check for working C compiler: /home/hatzen/ndk/android-ndk-r21/toolchains/llvm/prebuilt/linux-x86_64/bin/clang
-- Check for working C compiler: /home/hatzen/ndk/android-ndk-r21/toolchains/llvm/prebuilt/linux-x86_64/bin/clang -- works
-- Detecting C compiler ABI info
-- Detecting C compiler ABI info - done
-- Detecting C compile features
-- Detecting C compile features - done
-- Looking for pthread.h
-- Looking for pthread.h - found
-- Looking for pthread_create
-- Looking for pthread_create - found
-- Found Threads: TRUE
INSTALL TARGETS - target jni has PUBLIC_HEADER files but no PUBLIC_HEADER DESTINATION.
-- Could NOT find Doxygen (missing: DOXYGEN_EXECUTABLE)
-- Configuring done
-- Generating done
-- Build files have been written to: /home/hatzen/liboqs-test-android/build
Scanning dependencies of target sphincs_shake256_256s_simple_clean
[  0%] Building C object src/sig/sphincs/CMakeFiles/sphincs_shake256_256s_simple_clean.dir/sig_sphincs_shake256_256s_simple.c.o
[  1%] Building C object src/sig/sphincs/CMakeFiles/sphincs_shake256_256s_simple_clean.dir/pqclean_sphincs-shake256-256s-simple_clean/address.c.o
[  1%] Building C object src/sig/sphincs/CMakeFiles/sphincs_shake256_256s_simple_clean.dir/pqclean_sphincs-shake256-256s-simple_clean/fors.c.o
[  1%] Building C object src/sig/sphincs/CMakeFiles/sphincs_shake256_256s_simple_clean.dir/pqclean_sphincs-shake256-256s-simple_clean/hash_shake256.c.o
[  1%] Building C object src/sig/sphincs/CMakeFiles/sphincs_shake256_256s_simple_clean.dir/pqclean_sphincs-shake256-256s-simple_clean/sign.c.o
[  1%] Building C object src/sig/sphincs/CMakeFiles/sphincs_shake256_256s_simple_clean.dir/pqclean_sphincs-shake256-256s-simple_clean/thash_shake256_simple.c.o
[  1%] Building C object src/sig/sphincs/CMakeFiles/sphincs_shake256_256s_simple_clean.dir/pqclean_sphincs-shake256-256s-simple_clean/utils.c.o
[  1%] Building C object src/sig/sphincs/CMakeFiles/sphincs_shake256_256s_simple_clean.dir/pqclean_sphincs-shake256-256s-simple_clean/wots.c.o
[  1%] Built target sphincs_shake256_256s_simple_clean
Scanning dependencies of target common
[  1%] Building C object src/common/CMakeFiles/common.dir/aes/aes.c.o
[  1%] Building C object src/common/CMakeFiles/common.dir/aes/aes_c.c.o
[  1%] Building C object src/common/CMakeFiles/common.dir/sha2/sha2_c.c.o
[  2%] Building C object src/common/CMakeFiles/common.dir/sha3/sha3_c.c.o
[  2%] Building C object src/common/CMakeFiles/common.dir/common.c.o
[  2%] Building C object src/common/CMakeFiles/common.dir/pqclean_shims/nistseedexpander.c.o
[  2%] Building C object src/common/CMakeFiles/common.dir/rand/rand.c.o
[  2%] Building C object src/common/CMakeFiles/common.dir/rand/rand_nist.c.o
[  2%] Built target common
Scanning dependencies of target bike1_l1_cpa
[  2%] Building C object src/kem/bike/CMakeFiles/bike1_l1_cpa.dir/additional/decode.c.o
[  2%] Building C object src/kem/bike/CMakeFiles/bike1_l1_cpa.dir/additional/sampling.c.o
[  2%] Building C object src/kem/bike/CMakeFiles/bike1_l1_cpa.dir/additional/parallel_hash.c.o
[  2%] Building C object src/kem/bike/CMakeFiles/bike1_l1_cpa.dir/additional/utilities.c.o
[  2%] Building C object src/kem/bike/CMakeFiles/bike1_l1_cpa.dir/additional/aes_ctr_prf.c.o
[  2%] Building C object src/kem/bike/CMakeFiles/bike1_l1_cpa.dir/additional/error.c.o
[  2%] Building C object src/kem/bike/CMakeFiles/bike1_l1_cpa.dir/additional/sampling_portable.c.o
[  2%] Building C object src/kem/bike/CMakeFiles/bike1_l1_cpa.dir/additional/secure_decode_portable.c.o
[  2%] Building C object src/kem/bike/CMakeFiles/bike1_l1_cpa.dir/additional/gf2x_portable.c.o
[  2%] Building C object src/kem/bike/CMakeFiles/bike1_l1_cpa.dir/additional/gf2x_mul.c.o
[  3%] Building C object src/kem/bike/CMakeFiles/bike1_l1_cpa.dir/additional/kem_cpa.c.o
[  3%] Built target bike1_l1_cpa
Scanning dependencies of target kem_bike
[  3%] Building C object src/kem/bike/CMakeFiles/kem_bike.dir/kem_bike.c.o
[  3%] Built target kem_bike
Scanning dependencies of target bike1_l3_cpa
[  3%] Building C object src/kem/bike/CMakeFiles/bike1_l3_cpa.dir/additional/decode.c.o
[  3%] Building C object src/kem/bike/CMakeFiles/bike1_l3_cpa.dir/additional/sampling.c.o
[  3%] Building C object src/kem/bike/CMakeFiles/bike1_l3_cpa.dir/additional/parallel_hash.c.o
[  3%] Building C object src/kem/bike/CMakeFiles/bike1_l3_cpa.dir/additional/utilities.c.o
[  3%] Building C object src/kem/bike/CMakeFiles/bike1_l3_cpa.dir/additional/aes_ctr_prf.c.o
[  3%] Building C object src/kem/bike/CMakeFiles/bike1_l3_cpa.dir/additional/error.c.o
[  3%] Building C object src/kem/bike/CMakeFiles/bike1_l3_cpa.dir/additional/sampling_portable.c.o
[  3%] Building C object src/kem/bike/CMakeFiles/bike1_l3_cpa.dir/additional/secure_decode_portable.c.o
[  3%] Building C object src/kem/bike/CMakeFiles/bike1_l3_cpa.dir/additional/gf2x_portable.c.o
[  4%] Building C object src/kem/bike/CMakeFiles/bike1_l3_cpa.dir/additional/gf2x_mul.c.o
[  4%] Building C object src/kem/bike/CMakeFiles/bike1_l3_cpa.dir/additional/kem_cpa.c.o
[  4%] Built target bike1_l3_cpa
Scanning dependencies of target bike1_l1_fo
[  4%] Building C object src/kem/bike/CMakeFiles/bike1_l1_fo.dir/additional/decode.c.o
[  4%] Building C object src/kem/bike/CMakeFiles/bike1_l1_fo.dir/additional/sampling.c.o
[  4%] Building C object src/kem/bike/CMakeFiles/bike1_l1_fo.dir/additional/parallel_hash.c.o
[  4%] Building C object src/kem/bike/CMakeFiles/bike1_l1_fo.dir/additional/utilities.c.o
[  4%] Building C object src/kem/bike/CMakeFiles/bike1_l1_fo.dir/additional/aes_ctr_prf.c.o
[  4%] Building C object src/kem/bike/CMakeFiles/bike1_l1_fo.dir/additional/error.c.o
[  4%] Building C object src/kem/bike/CMakeFiles/bike1_l1_fo.dir/additional/sampling_portable.c.o
[  4%] Building C object src/kem/bike/CMakeFiles/bike1_l1_fo.dir/additional/secure_decode_portable.c.o
[  4%] Building C object src/kem/bike/CMakeFiles/bike1_l1_fo.dir/additional/gf2x_portable.c.o
[  5%] Building C object src/kem/bike/CMakeFiles/bike1_l1_fo.dir/additional/gf2x_mul.c.o
[  5%] Building C object src/kem/bike/CMakeFiles/bike1_l1_fo.dir/additional/kem.c.o
[  5%] Built target bike1_l1_fo
Scanning dependencies of target bike1_l3_fo
[  5%] Building C object src/kem/bike/CMakeFiles/bike1_l3_fo.dir/additional/decode.c.o
[  5%] Building C object src/kem/bike/CMakeFiles/bike1_l3_fo.dir/additional/sampling.c.o
[  5%] Building C object src/kem/bike/CMakeFiles/bike1_l3_fo.dir/additional/parallel_hash.c.o
[  5%] Building C object src/kem/bike/CMakeFiles/bike1_l3_fo.dir/additional/utilities.c.o
[  5%] Building C object src/kem/bike/CMakeFiles/bike1_l3_fo.dir/additional/aes_ctr_prf.c.o
[  5%] Building C object src/kem/bike/CMakeFiles/bike1_l3_fo.dir/additional/error.c.o
[  5%] Building C object src/kem/bike/CMakeFiles/bike1_l3_fo.dir/additional/sampling_portable.c.o
[  5%] Building C object src/kem/bike/CMakeFiles/bike1_l3_fo.dir/additional/secure_decode_portable.c.o
[  6%] Building C object src/kem/bike/CMakeFiles/bike1_l3_fo.dir/additional/gf2x_portable.c.o
[  6%] Building C object src/kem/bike/CMakeFiles/bike1_l3_fo.dir/additional/gf2x_mul.c.o
[  6%] Building C object src/kem/bike/CMakeFiles/bike1_l3_fo.dir/additional/kem.c.o
[  6%] Built target bike1_l3_fo
Scanning dependencies of target frodokem
[  6%] Building C object src/kem/frodokem/CMakeFiles/frodokem.dir/kem_frodokem640aes.c.o
[  6%] Building C object src/kem/frodokem/CMakeFiles/frodokem.dir/external/frodo640aes.c.o
[  6%] Building C object src/kem/frodokem/CMakeFiles/frodokem.dir/kem_frodokem640shake.c.o
[  6%] Building C object src/kem/frodokem/CMakeFiles/frodokem.dir/external/frodo640shake.c.o
[  6%] Building C object src/kem/frodokem/CMakeFiles/frodokem.dir/kem_frodokem976aes.c.o
[  7%] Building C object src/kem/frodokem/CMakeFiles/frodokem.dir/external/frodo976aes.c.o
[  7%] Building C object src/kem/frodokem/CMakeFiles/frodokem.dir/kem_frodokem976shake.c.o
[  7%] Building C object src/kem/frodokem/CMakeFiles/frodokem.dir/external/frodo976shake.c.o
[  7%] Building C object src/kem/frodokem/CMakeFiles/frodokem.dir/kem_frodokem1344aes.c.o
[  7%] Building C object src/kem/frodokem/CMakeFiles/frodokem.dir/external/frodo1344aes.c.o
[  7%] Building C object src/kem/frodokem/CMakeFiles/frodokem.dir/kem_frodokem1344shake.c.o
[  7%] Building C object src/kem/frodokem/CMakeFiles/frodokem.dir/external/frodo1344shake.c.o
[  7%] Built target frodokem
Scanning dependencies of target sike
[  7%] Building C object src/kem/sike/CMakeFiles/sike.dir/kem_sike.c.o
[  7%] Building C object src/kem/sike/CMakeFiles/sike.dir/external/P434/P434.c.o
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:23:9: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
        SUBC(borrow, a[i], b[i], borrow, c[i]);
        ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:165:35: note: expanded from macro 'SUBC'
        unsigned int borrowReg = (is_digit_lessthan_ct((minuend), (subtrahend)) | ((borrowIn) &is_digit_zero_ct(tempReg))); \
                                  ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:23:9: error: static function 'is_digit_zero_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
        SUBC(borrow, a[i], b[i], borrow, c[i]);
        ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:165:96: note: expanded from macro 'SUBC'
        unsigned int borrowReg = (is_digit_lessthan_ct((minuend), (subtrahend)) | ((borrowIn) &is_digit_zero_ct(tempReg))); \
                                                                                               ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:137:30: note: 'is_digit_zero_ct' declared here
static __inline unsigned int is_digit_zero_ct(digit_t x) { // Is x = 0?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:28:9: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
        ADDC(borrow, c[i], ((digit_t*)p434x2)[i], borrow, c[i]);
        ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:158:23: note: expanded from macro 'ADDC'
        (carryOut) = (is_digit_lessthan_ct(tempReg, (digit_t)(carryIn)) | is_digit_lessthan_ct((sumOut), tempReg)); \
                      ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:28:9: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
        ADDC(borrow, c[i], ((digit_t*)p434x2)[i], borrow, c[i]);
        ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:158:75: note: expanded from macro 'ADDC'
        (carryOut) = (is_digit_lessthan_ct(tempReg, (digit_t)(carryIn)) | is_digit_lessthan_ct((sumOut), tempReg)); \
                                                                          ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:38:9: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
        SUBC(borrow, a[i], b[i], borrow, c[i]);
        ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:165:35: note: expanded from macro 'SUBC'
        unsigned int borrowReg = (is_digit_lessthan_ct((minuend), (subtrahend)) | ((borrowIn) &is_digit_zero_ct(tempReg))); \
                                  ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:38:9: error: static function 'is_digit_zero_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
        SUBC(borrow, a[i], b[i], borrow, c[i]);
        ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:165:96: note: expanded from macro 'SUBC'
        unsigned int borrowReg = (is_digit_lessthan_ct((minuend), (subtrahend)) | ((borrowIn) &is_digit_zero_ct(tempReg))); \
                                                                                               ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:137:30: note: 'is_digit_zero_ct' declared here
static __inline unsigned int is_digit_zero_ct(digit_t x) { // Is x = 0?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:43:9: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
        ADDC(borrow, c[i], ((digit_t*)p434x4)[i], borrow, c[i]);
        ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:158:23: note: expanded from macro 'ADDC'
        (carryOut) = (is_digit_lessthan_ct(tempReg, (digit_t)(carryIn)) | is_digit_lessthan_ct((sumOut), tempReg)); \
                      ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:43:9: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
        ADDC(borrow, c[i], ((digit_t*)p434x4)[i], borrow, c[i]);
        ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:158:75: note: expanded from macro 'ADDC'
        (carryOut) = (is_digit_lessthan_ct(tempReg, (digit_t)(carryIn)) | is_digit_lessthan_ct((sumOut), tempReg)); \
                                                                          ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:54:3: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
                ADDC(carry, a[i], b[i], carry, c[i]);
                ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:158:23: note: expanded from macro 'ADDC'
        (carryOut) = (is_digit_lessthan_ct(tempReg, (digit_t)(carryIn)) | is_digit_lessthan_ct((sumOut), tempReg)); \
                      ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:54:3: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
                ADDC(carry, a[i], b[i], carry, c[i]);
                ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:158:75: note: expanded from macro 'ADDC'
        (carryOut) = (is_digit_lessthan_ct(tempReg, (digit_t)(carryIn)) | is_digit_lessthan_ct((sumOut), tempReg)); \
                                                                          ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:59:3: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
                SUBC(carry, c[i], ((digit_t *) p434x2)[i], carry, c[i]);
                ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:165:35: note: expanded from macro 'SUBC'
        unsigned int borrowReg = (is_digit_lessthan_ct((minuend), (subtrahend)) | ((borrowIn) &is_digit_zero_ct(tempReg))); \
                                  ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:59:3: error: static function 'is_digit_zero_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
                SUBC(carry, c[i], ((digit_t *) p434x2)[i], carry, c[i]);
                ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:165:96: note: expanded from macro 'SUBC'
        unsigned int borrowReg = (is_digit_lessthan_ct((minuend), (subtrahend)) | ((borrowIn) &is_digit_zero_ct(tempReg))); \
                                                                                               ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:137:30: note: 'is_digit_zero_ct' declared here
static __inline unsigned int is_digit_zero_ct(digit_t x) { // Is x = 0?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:65:3: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
                ADDC(carry, c[i], ((digit_t *) p434x2)[i] & mask, carry, c[i]);
                ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:158:23: note: expanded from macro 'ADDC'
        (carryOut) = (is_digit_lessthan_ct(tempReg, (digit_t)(carryIn)) | is_digit_lessthan_ct((sumOut), tempReg)); \
                      ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:65:3: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
                ADDC(carry, c[i], ((digit_t *) p434x2)[i] & mask, carry, c[i]);
                ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:158:75: note: expanded from macro 'ADDC'
        (carryOut) = (is_digit_lessthan_ct(tempReg, (digit_t)(carryIn)) | is_digit_lessthan_ct((sumOut), tempReg)); \
                                                                          ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:76:3: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
                SUBC(borrow, a[i], b[i], borrow, c[i]);
                ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:165:35: note: expanded from macro 'SUBC'
        unsigned int borrowReg = (is_digit_lessthan_ct((minuend), (subtrahend)) | ((borrowIn) &is_digit_zero_ct(tempReg))); \
                                  ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:76:3: error: static function 'is_digit_zero_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
                SUBC(borrow, a[i], b[i], borrow, c[i]);
                ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:165:96: note: expanded from macro 'SUBC'
        unsigned int borrowReg = (is_digit_lessthan_ct((minuend), (subtrahend)) | ((borrowIn) &is_digit_zero_ct(tempReg))); \
                                                                                               ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:137:30: note: 'is_digit_zero_ct' declared here
static __inline unsigned int is_digit_zero_ct(digit_t x) { // Is x = 0?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:82:3: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
                ADDC(borrow, c[i], ((digit_t *) p434x2)[i] & mask, borrow, c[i]);
                ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:158:23: note: expanded from macro 'ADDC'
        (carryOut) = (is_digit_lessthan_ct(tempReg, (digit_t)(carryIn)) | is_digit_lessthan_ct((sumOut), tempReg)); \
                      ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:82:3: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
                ADDC(borrow, c[i], ((digit_t *) p434x2)[i] & mask, borrow, c[i]);
                ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:158:75: note: expanded from macro 'ADDC'
        (carryOut) = (is_digit_lessthan_ct(tempReg, (digit_t)(carryIn)) | is_digit_lessthan_ct((sumOut), tempReg)); \
                                                                          ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
In file included from /home/hatzen/liboqs-test-android/src/kem/sike/external/P434/P434.c:154:
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/generic/fp_generic.c:91:3: error: static function 'is_digit_lessthan_ct' is used in an inline function with external linkage [-Werror,-Wstatic-in-inline]
                SUBC(borrow, ((digit_t *) p434x2)[i], a[i], borrow, a[i]);
                ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:165:35: note: expanded from macro 'SUBC'
        unsigned int borrowReg = (is_digit_lessthan_ct((minuend), (subtrahend)) | ((borrowIn) &is_digit_zero_ct(tempReg))); \
                                  ^
/home/hatzen/liboqs-test-android/src/kem/sike/external/P434/../config.h:141:30: note: 'is_digit_lessthan_ct' declared here
static __inline unsigned int is_digit_lessthan_ct(digit_t x, digit_t y) { // Is x < y?
                             ^
fatal error: too many errors emitted, stopping now [-ferror-limit=]
20 errors generated.
src/kem/sike/CMakeFiles/sike.dir/build.make:86: recipe for target 'src/kem/sike/CMakeFiles/sike.dir/external/P434/P434.c.o' failed
make[2]: *** [src/kem/sike/CMakeFiles/sike.dir/external/P434/P434.c.o] Error 1
CMakeFiles/Makefile2:662: recipe for target 'src/kem/sike/CMakeFiles/sike.dir/all' failed
make[1]: *** [src/kem/sike/CMakeFiles/sike.dir/all] Error 2
Makefile:129: recipe for target 'all' failed
make: *** [all] Error 2
root@LAPTOP-xxx:/home/hatzen/liboqs-test-android#