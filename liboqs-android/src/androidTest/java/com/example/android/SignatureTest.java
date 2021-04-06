package com.example.android;


import android.util.Log;

import com.example.liboqs.MechanismNotSupportedError;
import com.example.liboqs.Signature;
import com.example.liboqs.Sigs;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(Parameterized.class)
public class SignatureTest {

    @Parameterized.Parameter(value = 0)
    public String sig_name;

    static ArrayList<String> ignoredSigs = new ArrayList<>();

    /**
     * Method to convert the list of Sigs to a list for input to testAllSigs.
     */
    @Parameterized.Parameters
    public static List<Object> getEnabledSigs() {

        ignoredSigs.add("Rainbow-IIIc-Cyclic");
        ignoredSigs.add("Rainbow-IIIc-Cyclic-Compressed");
        ignoredSigs.add("Rainbow-Vc-Classic");
        ignoredSigs.add("Rainbow-Vc-Cyclic");
        ignoredSigs.add("Rainbow-Vc-Cyclic-Compressed");

        System.out.println("Initialize list of enabled Signatures");
        ArrayList<String> enabled_sigs = Sigs.get_enabled_sigs();

        // Do not use java streams as they are only supported on Android Nougat (7.0 = SDK 24) and above.
        List<Object> parameters = new ArrayList<>();
        for (String kemName: enabled_sigs) {
            if (ignoredSigs.contains(kemName)) {
                continue;
            }
            parameters.add( new Object[] { kemName });
        }
        return parameters;
    }

    private final byte[] message = "This is the message to sign".getBytes();


    /**
     * Test all enabled Sigs
     */
    @ParameterizedTest(name = "Testing {arguments}")
    @MethodSource("getEnabledSigs")
    @Test
    public void testAllSigs() {
        Log.e(getClass().getSimpleName(), "Test " + sig_name);
        StringBuilder sb = new StringBuilder();
        sb.append(sig_name);
        sb.append(String.format("%1$" + (40 - sig_name.length()) + "s", ""));

        // Create signer and verifier
        Signature signer = new Signature(sig_name);
        Signature verifier = new Signature(sig_name);

        // Generate signer key pair
        byte[] signer_public_key = signer.generate_keypair();

        // Sign the message
        byte[] signature = signer.sign(message);

        // Verify the signature
        boolean is_valid = verifier.verify(message, signature, signer_public_key);

        assertTrue(is_valid, sig_name);

        // If successful print Sig name, otherwise an exception will be thrown
        sb.append("\033[0;32m").append("PASSED").append("\033[0m");
        System.out.println(sb.toString());
    }

    /**
     * Test the MechanismNotSupported Exception
     */
    public void testUnsupportedSigExpectedException() {
        Assertions.assertThrows(MechanismNotSupportedError.class, () -> new Signature("MechanismNotSupported"));
    }

    /*
    2021-04-06 23:30:41.346 2938-2970/com.example.android.test A/libc: Fatal signal 11 (SIGSEGV), code 2, fault addr 0x7bfc916000 in tid 2970 (roidJUnitRunner), pid 2938 (le.android.test)
2021-04-06 23:30:41.497 2985-2985/? A/DEBUG: *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***
2021-04-06 23:30:41.497 2985-2985/? A/DEBUG: Build fingerprint: 'YOTA/Y3/Y3:8.1.0/OPM1.171019.019/52:user/dev-keys'
2021-04-06 23:30:41.498 2985-2985/? A/DEBUG: Revision: '0'
2021-04-06 23:30:41.498 2985-2985/? A/DEBUG: ABI: 'arm64'
2021-04-06 23:30:41.498 2985-2985/? A/DEBUG: pid: 2938, tid: 2970, name: roidJUnitRunner  >>> com.example.android.test <<<
2021-04-06 23:30:41.498 2985-2985/? A/DEBUG: signal 11 (SIGSEGV), code 2 (SEGV_ACCERR), fault addr 0x7bfc916000
2021-04-06 23:30:41.498 2985-2985/? A/DEBUG:     x0   0000007bfc90b938  x1   0000000000000000  x2   0000000000003538  x3   0000007bfc916000
2021-04-06 23:30:41.498 2985-2985/? A/DEBUG:     x4   0000007bfc9195b8  x5   0000000000000004  x6   0000000000000000  x7   0000000000000001
2021-04-06 23:30:41.498 2985-2985/? A/DEBUG:     x8   0000000000099a20  x9   00000000000000cf  x10  000000000000000c  x11  0000000000000000
2021-04-06 23:30:41.498 2985-2985/? A/DEBUG:     x12  0000000000000000  x13  0000000000000000  x14  0000000000000000  x15  0000000000000000
2021-04-06 23:30:41.498 2985-2985/? A/DEBUG:     x16  0000007bfc7f9470  x17  0000007c99de8c30  x18  0000000000000020  x19  00000000779e3a6c
2021-04-06 23:30:41.498 2985-2985/? A/DEBUG:     x20  00000000779e292c  x21  0000007bfc871f18  x22  00000000779e200c  x23  00000000779e200c
2021-04-06 23:30:41.498 2985-2985/? A/DEBUG:     x24  00000000779e54ac  x25  0000007bfc90b938  x26  0000007bfc8d7d38  x27  0000000000000003
2021-04-06 23:30:41.498 2985-2985/? A/DEBUG:     x28  0000007bfc8a4138  x29  0000007bfca126f0  x30  0000007bfc4ca298
2021-04-06 23:30:41.498 2985-2985/? A/DEBUG:     sp   0000007bfc856e70  pc   0000007c99de8d30  pstate 0000000020000000
2021-04-06 23:30:41.502 2985-2985/? A/DEBUG: backtrace:
2021-04-06 23:30:41.502 2985-2985/? A/DEBUG:     #00 pc 000000000001cd30  /system/lib64/libc.so (memset+256)
2021-04-06 23:30:41.502 2985-2985/? A/DEBUG:     #01 pc 000000000026d294  /data/app/com.example.android.test-S5sUNACiqrXe0hDkyb9hBw==/lib/arm64/liboqs.so
2021-04-06 23:30:41.502 2985-2985/? A/DEBUG:     #02 pc 000000000026cbf4  /data/app/com.example.android.test-S5sUNACiqrXe0hDkyb9hBw==/lib/arm64/liboqs.so
2021-04-06 23:30:41.503 2985-2985/? A/DEBUG:     #03 pc 000000000026d798  /data/app/com.example.android.test-S5sUNACiqrXe0hDkyb9hBw==/lib/arm64/liboqs.so
2021-04-06 23:30:41.503 2985-2985/? A/DEBUG:     #04 pc 000000000000bf70  /data/app/com.example.android.test-S5sUNACiqrXe0hDkyb9hBw==/lib/arm64/liboqs.so (OQS_SIG_keypair+20)
2021-04-06 23:30:41.503 2985-2985/? A/DEBUG:     #05 pc 0000000000002920  /data/app/com.example.android.test-S5sUNACiqrXe0hDkyb9hBw==/lib/arm64/liboqs-jni.so (Java_com_example_liboqs_Signature_generate_1keypair+168)
2021-04-06 23:30:41.503 2985-2985/? A/DEBUG:     #06 pc 000000000001cfa4  /data/app/com.example.android.test-S5sUNACiqrXe0hDkyb9hBw==/oat/arm64/base.odex (offset 0x1c000)
2021-04-06 23:30:42.487 911-911/? E//system/bin/tombstoned: Tombstone written to: /data/tombstones/tombstone_06
*/
}