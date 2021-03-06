// SPDX-License-Identifier: MIT

#define OQS_VERSION_TEXT "0.5.0-dev"
#define OQS_COMPILE_BUILD_TARGET "x86_64-Linux-4.4.0-19041-Microsoft"
#define OQS_PORTABLE_BUILD 1

#define OQS_KEM_DEFAULT OQS_KEM_alg_frodokem_640_aes
#define OQS_SIG_DEFAULT OQS_SIG_alg_dilithium_2

/* #undef OQS_USE_OPENSSL */
/* #undef OQS_USE_AES_OPENSSL */
/* #undef OQS_USE_SHA2_OPENSSL */
/* #undef OQS_USE_SHA3_OPENSSL */

#define OQS_USE_PTHREADS_IN_TESTS 1
/* #undef OQS_USE_CPU_EXTENSIONS */

/* #undef OQS_USE_AES_INSTRUCTIONS */
/* #undef OQS_USE_AVX_INSTRUCTIONS */
/* #undef OQS_USE_AVX2_INSTRUCTIONS */
/* #undef OQS_USE_AVX512_INSTRUCTIONS */
/* #undef OQS_USE_BMI1_INSTRUCTIONS */
/* #undef OQS_USE_BMI2_INSTRUCTIONS */
/* #undef OQS_USE_PCLMUL_INSTRUCTIONS */
/* #undef OQS_USE_POPCNT_INSTRUCTIONS */
/* #undef OQS_USE_SSE_INSTRUCTIONS */
/* #undef OQS_USE_SSE2_INSTRUCTIONS */
/* #undef OQS_USE_SSE3_INSTRUCTIONS */

#define OQS_ENABLE_KEM_BIKE 1
#define OQS_ENABLE_KEM_bike1_l1_cpa 1
#define OQS_ENABLE_KEM_bike1_l3_cpa 1
#define OQS_ENABLE_KEM_bike1_l1_fo 1
#define OQS_ENABLE_KEM_bike1_l3_fo 1

#define OQS_ENABLE_KEM_FRODOKEM 1
#define OQS_ENABLE_KEM_frodokem_640_aes 1
#define OQS_ENABLE_KEM_frodokem_640_shake 1
#define OQS_ENABLE_KEM_frodokem_976_aes 1
#define OQS_ENABLE_KEM_frodokem_976_shake 1
#define OQS_ENABLE_KEM_frodokem_1344_aes 1
#define OQS_ENABLE_KEM_frodokem_1344_shake 1

#define OQS_ENABLE_KEM_SIDH 1
#define OQS_ENABLE_KEM_sidh_p434 1
#define OQS_ENABLE_KEM_sidh_p434_compressed 1
#define OQS_ENABLE_KEM_sidh_p503 1
#define OQS_ENABLE_KEM_sidh_p503_compressed 1
#define OQS_ENABLE_KEM_sidh_p610 1
#define OQS_ENABLE_KEM_sidh_p610_compressed 1
#define OQS_ENABLE_KEM_sidh_p751 1
#define OQS_ENABLE_KEM_sidh_p751_compressed 1

/* #undef OQS_ENABLE_KEM_SIKE */
/* #undef OQS_ENABLE_KEM_sike_p434 */
/* #undef OQS_ENABLE_KEM_sike_p434_compressed */
/* #undef OQS_ENABLE_KEM_sike_p503 */
/* #undef OQS_ENABLE_KEM_sike_p503_compressed */
/* #undef OQS_ENABLE_KEM_sike_p610 */
/* #undef OQS_ENABLE_KEM_sike_p610_compressed */
/* #undef OQS_ENABLE_KEM_sike_p751 */
/* #undef OQS_ENABLE_KEM_sike_p751_compressed */

/* #undef OQS_ENABLE_SIG_PICNIC */
/* #undef OQS_ENABLE_SIG_picnic_L1_UR */
/* #undef OQS_ENABLE_SIG_picnic_L1_FS */
/* #undef OQS_ENABLE_SIG_picnic_L1_full */
/* #undef OQS_ENABLE_SIG_picnic_L3_UR */
/* #undef OQS_ENABLE_SIG_picnic_L3_FS */
/* #undef OQS_ENABLE_SIG_picnic_L3_full */
/* #undef OQS_ENABLE_SIG_picnic_L5_UR */
/* #undef OQS_ENABLE_SIG_picnic_L5_FS */
/* #undef OQS_ENABLE_SIG_picnic_L5_full */
/* #undef OQS_ENABLE_SIG_picnic3_L1 */
/* #undef OQS_ENABLE_SIG_picnic3_L3 */
/* #undef OQS_ENABLE_SIG_picnic3_L5 */

///// OQS_COPY_FROM_PQCLEAN_FRAGMENT_ADD_ALG_ENABLE_DEFINES_START

#define OQS_ENABLE_KEM_CLASSIC_MCELIECE 1
#define OQS_ENABLE_KEM_classic_mceliece_348864 1
#define OQS_ENABLE_KEM_classic_mceliece_348864f 1
#define OQS_ENABLE_KEM_classic_mceliece_460896 1
#define OQS_ENABLE_KEM_classic_mceliece_460896f 1
#define OQS_ENABLE_KEM_classic_mceliece_6688128 1
#define OQS_ENABLE_KEM_classic_mceliece_6688128f 1
#define OQS_ENABLE_KEM_classic_mceliece_6960119 1
#define OQS_ENABLE_KEM_classic_mceliece_6960119f 1
#define OQS_ENABLE_KEM_classic_mceliece_8192128 1
#define OQS_ENABLE_KEM_classic_mceliece_8192128f 1

#define OQS_ENABLE_KEM_HQC 1
#define OQS_ENABLE_KEM_hqc_128 1
/* #undef OQS_ENABLE_KEM_hqc_128_avx2 */
#define OQS_ENABLE_KEM_hqc_192 1
/* #undef OQS_ENABLE_KEM_hqc_192_avx2 */
#define OQS_ENABLE_KEM_hqc_256 1
/* #undef OQS_ENABLE_KEM_hqc_256_avx2 */

#define OQS_ENABLE_KEM_KYBER 1
#define OQS_ENABLE_KEM_kyber_512 1
/* #undef OQS_ENABLE_KEM_kyber_512_avx2 */
#define OQS_ENABLE_KEM_kyber_768 1
/* #undef OQS_ENABLE_KEM_kyber_768_avx2 */
#define OQS_ENABLE_KEM_kyber_1024 1
/* #undef OQS_ENABLE_KEM_kyber_1024_avx2 */
#define OQS_ENABLE_KEM_kyber_512_90s 1
/* #undef OQS_ENABLE_KEM_kyber_512_90s_avx2 */
#define OQS_ENABLE_KEM_kyber_768_90s 1
/* #undef OQS_ENABLE_KEM_kyber_768_90s_avx2 */
#define OQS_ENABLE_KEM_kyber_1024_90s 1
/* #undef OQS_ENABLE_KEM_kyber_1024_90s_avx2 */

#define OQS_ENABLE_KEM_NTRU 1
#define OQS_ENABLE_KEM_ntru_hps2048509 1
/* #undef OQS_ENABLE_KEM_ntru_hps2048509_avx2 */
#define OQS_ENABLE_KEM_ntru_hps2048677 1
/* #undef OQS_ENABLE_KEM_ntru_hps2048677_avx2 */
#define OQS_ENABLE_KEM_ntru_hps4096821 1
/* #undef OQS_ENABLE_KEM_ntru_hps4096821_avx2 */
#define OQS_ENABLE_KEM_ntru_hrss701 1
/* #undef OQS_ENABLE_KEM_ntru_hrss701_avx2 */

#define OQS_ENABLE_KEM_NTRUPRIME 1
#define OQS_ENABLE_KEM_ntruprime_ntrulpr653 1
/* #undef OQS_ENABLE_KEM_ntruprime_ntrulpr653_avx2 */
#define OQS_ENABLE_KEM_ntruprime_ntrulpr761 1
/* #undef OQS_ENABLE_KEM_ntruprime_ntrulpr761_avx2 */
#define OQS_ENABLE_KEM_ntruprime_ntrulpr857 1
/* #undef OQS_ENABLE_KEM_ntruprime_ntrulpr857_avx2 */
#define OQS_ENABLE_KEM_ntruprime_sntrup653 1
/* #undef OQS_ENABLE_KEM_ntruprime_sntrup653_avx2 */
#define OQS_ENABLE_KEM_ntruprime_sntrup761 1
/* #undef OQS_ENABLE_KEM_ntruprime_sntrup761_avx2 */
#define OQS_ENABLE_KEM_ntruprime_sntrup857 1
/* #undef OQS_ENABLE_KEM_ntruprime_sntrup857_avx2 */

#define OQS_ENABLE_KEM_SABER 1
#define OQS_ENABLE_KEM_saber_lightsaber 1
/* #undef OQS_ENABLE_KEM_saber_lightsaber_avx2 */
#define OQS_ENABLE_KEM_saber_saber 1
/* #undef OQS_ENABLE_KEM_saber_saber_avx2 */
#define OQS_ENABLE_KEM_saber_firesaber 1
/* #undef OQS_ENABLE_KEM_saber_firesaber_avx2 */

#define OQS_ENABLE_SIG_DILITHIUM 1
#define OQS_ENABLE_SIG_dilithium_2 1
/* #undef OQS_ENABLE_SIG_dilithium_2_avx2 */
#define OQS_ENABLE_SIG_dilithium_3 1
/* #undef OQS_ENABLE_SIG_dilithium_3_avx2 */
#define OQS_ENABLE_SIG_dilithium_4 1
/* #undef OQS_ENABLE_SIG_dilithium_4_avx2 */

#define OQS_ENABLE_SIG_FALCON 1
#define OQS_ENABLE_SIG_falcon_512 1
/* #undef OQS_ENABLE_SIG_falcon_512_avx2 */
#define OQS_ENABLE_SIG_falcon_1024 1
/* #undef OQS_ENABLE_SIG_falcon_1024_avx2 */

#define OQS_ENABLE_SIG_RAINBOW 1
#define OQS_ENABLE_SIG_rainbow_Ia_classic 1
#define OQS_ENABLE_SIG_rainbow_Ia_cyclic 1
#define OQS_ENABLE_SIG_rainbow_Ia_cyclic_compressed 1
#define OQS_ENABLE_SIG_rainbow_IIIc_classic 1
#define OQS_ENABLE_SIG_rainbow_IIIc_cyclic 1
#define OQS_ENABLE_SIG_rainbow_IIIc_cyclic_compressed 1
#define OQS_ENABLE_SIG_rainbow_Vc_classic 1
#define OQS_ENABLE_SIG_rainbow_Vc_cyclic 1
#define OQS_ENABLE_SIG_rainbow_Vc_cyclic_compressed 1

#define OQS_ENABLE_SIG_SPHINCS 1
#define OQS_ENABLE_SIG_sphincs_haraka_128f_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_haraka_128f_robust_aesni */
#define OQS_ENABLE_SIG_sphincs_haraka_128f_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_haraka_128f_simple_aesni */
#define OQS_ENABLE_SIG_sphincs_haraka_128s_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_haraka_128s_robust_aesni */
#define OQS_ENABLE_SIG_sphincs_haraka_128s_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_haraka_128s_simple_aesni */
#define OQS_ENABLE_SIG_sphincs_haraka_192f_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_haraka_192f_robust_aesni */
#define OQS_ENABLE_SIG_sphincs_haraka_192f_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_haraka_192f_simple_aesni */
#define OQS_ENABLE_SIG_sphincs_haraka_192s_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_haraka_192s_robust_aesni */
#define OQS_ENABLE_SIG_sphincs_haraka_192s_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_haraka_192s_simple_aesni */
#define OQS_ENABLE_SIG_sphincs_haraka_256f_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_haraka_256f_robust_aesni */
#define OQS_ENABLE_SIG_sphincs_haraka_256f_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_haraka_256f_simple_aesni */
#define OQS_ENABLE_SIG_sphincs_haraka_256s_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_haraka_256s_robust_aesni */
#define OQS_ENABLE_SIG_sphincs_haraka_256s_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_haraka_256s_simple_aesni */
#define OQS_ENABLE_SIG_sphincs_sha256_128f_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_sha256_128f_robust_avx2 */
#define OQS_ENABLE_SIG_sphincs_sha256_128f_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_sha256_128f_simple_avx2 */
#define OQS_ENABLE_SIG_sphincs_sha256_128s_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_sha256_128s_robust_avx2 */
#define OQS_ENABLE_SIG_sphincs_sha256_128s_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_sha256_128s_simple_avx2 */
#define OQS_ENABLE_SIG_sphincs_sha256_192f_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_sha256_192f_robust_avx2 */
#define OQS_ENABLE_SIG_sphincs_sha256_192f_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_sha256_192f_simple_avx2 */
#define OQS_ENABLE_SIG_sphincs_sha256_192s_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_sha256_192s_robust_avx2 */
#define OQS_ENABLE_SIG_sphincs_sha256_192s_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_sha256_192s_simple_avx2 */
#define OQS_ENABLE_SIG_sphincs_sha256_256f_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_sha256_256f_robust_avx2 */
#define OQS_ENABLE_SIG_sphincs_sha256_256f_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_sha256_256f_simple_avx2 */
#define OQS_ENABLE_SIG_sphincs_sha256_256s_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_sha256_256s_robust_avx2 */
#define OQS_ENABLE_SIG_sphincs_sha256_256s_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_sha256_256s_simple_avx2 */
#define OQS_ENABLE_SIG_sphincs_shake256_128f_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_shake256_128f_robust_avx2 */
#define OQS_ENABLE_SIG_sphincs_shake256_128f_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_shake256_128f_simple_avx2 */
#define OQS_ENABLE_SIG_sphincs_shake256_128s_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_shake256_128s_robust_avx2 */
#define OQS_ENABLE_SIG_sphincs_shake256_128s_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_shake256_128s_simple_avx2 */
#define OQS_ENABLE_SIG_sphincs_shake256_192f_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_shake256_192f_robust_avx2 */
#define OQS_ENABLE_SIG_sphincs_shake256_192f_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_shake256_192f_simple_avx2 */
#define OQS_ENABLE_SIG_sphincs_shake256_192s_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_shake256_192s_robust_avx2 */
#define OQS_ENABLE_SIG_sphincs_shake256_192s_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_shake256_192s_simple_avx2 */
#define OQS_ENABLE_SIG_sphincs_shake256_256f_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_shake256_256f_robust_avx2 */
#define OQS_ENABLE_SIG_sphincs_shake256_256f_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_shake256_256f_simple_avx2 */
#define OQS_ENABLE_SIG_sphincs_shake256_256s_robust 1
/* #undef OQS_ENABLE_SIG_sphincs_shake256_256s_robust_avx2 */
#define OQS_ENABLE_SIG_sphincs_shake256_256s_simple 1
/* #undef OQS_ENABLE_SIG_sphincs_shake256_256s_simple_avx2 */
///// OQS_COPY_FROM_PQCLEAN_FRAGMENT_ADD_ALG_ENABLE_DEFINES_END
