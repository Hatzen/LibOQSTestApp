package com.example.android;

import android.util.Log;

import com.example.liboqs.KEMs;
import com.example.liboqs.KeyEncapsulation;
import com.example.liboqs.MechanismNotSupportedError;
import com.example.liboqs.Pair;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@RunWith(Parameterized.class)
public class KEMTest {

    @Parameterized.Parameter(value = 0)
    public String kem_name;

    static ArrayList<String> ignoredKems = new ArrayList<>();

    /**
     * Method to convert the list of KEMs to a stream for input to testAllKEMs
     */
    @Parameterized.Parameters
    public static List<Object> getEnabledKEMs() {

        ignoredKems.add("Classic-McEliece-348864");
        ignoredKems.add("Classic-McEliece-348864f");
        ignoredKems.add("Classic-McEliece-460896");
        ignoredKems.add("Classic-McEliece-460896f");
        ignoredKems.add("Classic-McEliece-6688128");
        ignoredKems.add("Classic-McEliece-6688128f");
        ignoredKems.add("Classic-McEliece-6960119");
        ignoredKems.add("Classic-McEliece-6960119f");
        ignoredKems.add("Classic-McEliece-8192128");
        ignoredKems.add("Classic-McEliece-8192128f");

        System.out.println("Initialize list of enabled KEMs");
        ArrayList<String> enabled_kems = KEMs.get_enabled_KEMs();

        // Do not use java streams as they are only supported on Android Nougat (7.0 = SDK 24) and above.
        List<Object> parameters = new ArrayList<>();
        for (String kemName: enabled_kems) {
            if (ignoredKems.contains(kemName)) {
                continue;
            }
            parameters.add( new Object[] { kemName });
        }
        return parameters;
    }

    /**
     * Test all enabled KEMs
     */
    @ParameterizedTest(name = "Testing {arguments}")
    @MethodSource("getEnabledKEMs")
    @Test
    public void testAllKEMs() {
        try {

            Log.e(getClass().getSimpleName(), "Test " + kem_name);
            StringBuilder sb = new StringBuilder();
            sb.append(kem_name);
            sb.append(String.format("%1$" + (40 - kem_name.length()) + "s", ""));

            // Create client and server
            KeyEncapsulation client = new KeyEncapsulation(kem_name);
            KeyEncapsulation server = new KeyEncapsulation(kem_name);

            // Generate client key pair
            byte[] client_public_key = client.generate_keypair();

            // Server: encapsulate secret with client's public key
            Pair<byte[], byte[]> server_pair = server.encap_secret(client_public_key);
            byte[] ciphertext = server_pair.getLeft();
            byte[] shared_secret_server = server_pair.getRight();

            // Client: decapsulate
            byte[] shared_secret_client = client.decap_secret(ciphertext);

            // Check if equal
            assertArrayEquals(shared_secret_client, shared_secret_server, kem_name);

            // If successful print KEM name, otherwise an exception will be thrown
            sb.append("\033[0;32m").append("PASSED").append("\033[0m");
            System.out.println(sb.toString());
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Exception " + kem_name);
        }
    }

    /**
     * Test the MechanismNotSupported Exception
     */
    public void testUnsupportedKEMExpectedException() {
        Assertions.assertThrows(MechanismNotSupportedError.class, () -> new KeyEncapsulation("MechanismNotSupported"));
    }

    /*

2021-04-06 22:36:21.893 24661-24696/com.example.android.test E/KEMTest: Test Classic-McEliece-348864
2021-04-06 22:36:22.376 24661-24696/com.example.android.test A/libc: Fatal signal 11 (SIGSEGV), code 2, fault addr 0x7bfc7d5000 in tid 24696 (roidJUnitRunner), pid 24661 (le.android.test)
2021-04-06 22:36:22.560 24784-24784/? A/DEBUG: *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***
2021-04-06 22:36:22.560 24784-24784/? A/DEBUG: Build fingerprint: 'YOTA/Y3/Y3:8.1.0/OPM1.171019.019/52:user/dev-keys'
2021-04-06 22:36:22.560 24784-24784/? A/DEBUG: Revision: '0'
2021-04-06 22:36:22.560 24784-24784/? A/DEBUG: ABI: 'arm64'
2021-04-06 22:36:22.561 24784-24784/? A/DEBUG: pid: 24661, tid: 24696, name: roidJUnitRunner  >>> com.example.android.test <<<
2021-04-06 22:36:22.561 24784-24784/? A/DEBUG: signal 11 (SIGSEGV), code 2 (SEGV_ACCERR), fault addr 0x7bfc7d5000
2021-04-06 22:36:22.561 24784-24784/? A/DEBUG:     x0   0000007bfc7cf618  x1   0000000000000000  x2   0000000000002598  x3   0000007bfc7d5000
2021-04-06 22:36:22.561 24784-24784/? A/DEBUG:     x4   0000007bfc7d7618  x5   0000000000000004  x6   000000d000000072  x7   0000007e0000005e
2021-04-06 22:36:22.561 24784-24784/? A/DEBUG:     x8   1724acb0001340ba  x9   0000007bfc7e3620  x10  0000007bfc7ed90c  x11  0000000000000000
2021-04-06 22:36:22.561 24784-24784/? A/DEBUG:     x12  0000000000000032  x13  000000000000005d  x14  00000000000000fe  x15  0000000000000000
2021-04-06 22:36:22.561 24784-24784/? A/DEBUG:     x16  0000007bfc665470  x17  0000007c99de8c30  x18  0000000000000008  x19  0000007bfc7eb708
2021-04-06 22:36:22.561 24784-24784/? A/DEBUG:     x20  0000007bfc8d9588  x21  0000007bfc8c7b38  x22  0000000000002800  x23  0000007bfc7eb708
2021-04-06 22:36:22.561 24784-24784/? A/DEBUG:     x24  0000000000000007  x25  0000000000000080  x26  0000000000000020  x27  0000000000000007
2021-04-06 22:36:22.561 24784-24784/? A/DEBUG:     x28  0000000000000008  x29  0000007bfc803708  x30  0000007bfc1c1908
2021-04-06 22:36:22.561 24784-24784/? A/DEBUG:     sp   0000007bfc7bf590  pc   0000007c99de8d30  pstate 0000000020000000
2021-04-06 22:36:22.568 24784-24784/? A/DEBUG: backtrace:
2021-04-06 22:36:22.568 24784-24784/? A/DEBUG:     #00 pc 000000000001cd30  /system/lib64/libc.so (memset+256)
2021-04-06 22:36:22.568 24784-24784/? A/DEBUG:     #01 pc 00000000000f8904  /data/app/com.example.android.test-zV140GhcTZmmnLDJrEKWGw==/lib/arm64/liboqs.so
2021-04-06 22:36:22.568 24784-24784/? A/DEBUG:     #02 pc 00000000000f9238  /data/app/com.example.android.test-zV140GhcTZmmnLDJrEKWGw==/lib/arm64/liboqs.so
2021-04-06 22:36:22.568 24784-24784/? A/DEBUG:     #03 pc 00000000000f9238  /data/app/com.example.android.test-zV140GhcTZmmnLDJrEKWGw==/lib/arm64/liboqs.so
2021-04-06 22:36:22.568 24784-24784/? A/DEBUG:     #04 pc 00000000000f9238  /data/app/com.example.android.test-zV140GhcTZmmnLDJrEKWGw==/lib/arm64/liboqs.so
2021-04-06 22:36:22.568 24784-24784/? A/DEBUG:     #05 pc 00000000000f9238  /data/app/com.example.android.test-zV140GhcTZmmnLDJrEKWGw==/lib/arm64/liboqs.so
2021-04-06 22:36:22.568 24784-24784/? A/DEBUG:     #06 pc 00000000000f9238  /data/app/com.example.android.test-zV140GhcTZmmnLDJrEKWGw==/lib/arm64/liboqs.so
2021-04-06 22:36:22.568 24784-24784/? A/DEBUG:     #07 pc 00000000000f882c  /data/app/com.example.android.test-zV140GhcTZmmnLDJrEKWGw==/lib/arm64/liboqs.so
2021-04-06 22:36:22.568 24784-24784/? A/DEBUG:     #08 pc 00000000000ffc10  /data/app/com.example.android.test-zV140GhcTZmmnLDJrEKWGw==/lib/arm64/liboqs.so
2021-04-06 22:36:22.568 24784-24784/? A/DEBUG:     #09 pc 0000000000001e74  /data/app/com.example.android.test-zV140GhcTZmmnLDJrEKWGw==/lib/arm64/liboqs-jni.so (Java_com_example_liboqs_KeyEncapsulation_generate_1keypair+168)
2021-04-06 22:36:22.568 24784-24784/? A/DEBUG:     #10 pc 000000000001c7c4  /data/app/com.example.android.test-zV140GhcTZmmnLDJrEKWGw==/oat/arm64/base.odex (offset 0x1c000)
2021-04-06 22:36:23.610 911-911/? E//system/bin/tombstoned: Tombstone written to: /data/tombstones/tombstone_36
2021-04-06 22:36:27.385 23593-23932/? E/arrv: Phenotype API error. Event # civi@a989809a, EventCode: 5 [CONTEXT service_id=51 ]
    arra: 29501: Stale snapshot (change count changed - expected 49  but was 48)
        at arry.b(:com.google.android.gms@210915021@21.09.15 (040408-361652764):14)
        at arrx.b(:com.google.android.gms@210915021@21.09.15 (040408-361652764):0)
        at arrv.a(:com.google.android.gms@210915021@21.09.15 (040408-361652764):4)
        at arrv.fT(:com.google.android.gms@210915021@21.09.15 (040408-361652764):12)
        at abvb.run(:com.google.android.gms@210915021@21.09.15 (040408-361652764):17)
        at bryf.run(:com.google.android.gms@210915021@21.09.15 (040408-361652764):2)
        at uet.c(:com.google.android.gms@210915021@21.09.15 (040408-361652764):6)
        at uet.run(:com.google.android.gms@210915021@21.09.15 (040408-361652764):7)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1162)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:636)
        at ukt.run(:com.google.android.gms@210915021@21.09.15 (040408-361652764):0)
        at java.lang.Thread.run(Thread.java:764)
2021-04-06 22:36:27.388 23593-23932/? E/AsyncOperation: operation=CommitToConfigurationOperationCall, opStatusCode=29501 [CONTEXT service_id=51 ]
    OperationException[Status{statusCode=Stale snapshot (change count changed - expected 49  but was 48), resolution=null}]
        at arrv.a(:com.google.android.gms@210915021@21.09.15 (040408-361652764):6)
        at arrv.fT(:com.google.android.gms@210915021@21.09.15 (040408-361652764):12)
        at abvb.run(:com.google.android.gms@210915021@21.09.15 (040408-361652764):17)
        at bryf.run(:com.google.android.gms@210915021@21.09.15 (040408-361652764):2)
        at uet.c(:com.google.android.gms@210915021@21.09.15 (040408-361652764):6)
        at uet.run(:com.google.android.gms@210915021@21.09.15 (040408-361652764):7)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1162)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:636)
        at ukt.run(:com.google.android.gms@210915021@21.09.15 (040408-361652764):0)
        at java.lang.Thread.run(Thread.java:764)



     */
}
