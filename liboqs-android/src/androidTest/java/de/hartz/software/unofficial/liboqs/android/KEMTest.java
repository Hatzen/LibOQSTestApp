package de.hartz.software.unofficial.liboqs.android;

import android.util.Log;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openquantumsafe.KEMs;
import org.openquantumsafe.KeyEncapsulation;
import org.openquantumsafe.MechanismNotSupportedError;
import org.openquantumsafe.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@RunWith(Parameterized.class)
public class KEMTest {

    static {
        CommonAndroid.loadNativeLibrary();
    }

    private static final long OQS_STACK_SIZE_BYTES = 8L * 1024 * 1024;

    @Parameterized.Parameter(value = 0)
    public String kem_name;

    /**
     * Method to convert the list of KEMs to parameters for testAllKEMs.
     */
    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> getEnabledKEMs() {
        CommonAndroid.loadNativeLibrary();
        System.out.println("Initialize list of enabled KEMs");
        ArrayList<String> enabled_kems = KEMs.get_enabled_KEMs();

        // Do not use java streams as they are only supported on Android Nougat (7.0 = SDK 24) and above.
        List<Object[]> parameters = new ArrayList<>();
        for (String kemName : enabled_kems) {
            parameters.add(new Object[]{kemName});
        }
        return parameters;
    }

    /**
     * Test all enabled KEMs.
     */
    @Test
    public void testAllKEMs() throws Throwable {
        runWithLargeStack("oqs-kem-" + kem_name, () -> {
            Log.d(KEMTest.class.getSimpleName(), "Test " + kem_name);
            StringBuilder sb = new StringBuilder();
            sb.append(kem_name);
            sb.append(String.format("%1$" + Math.max(1, 40 - kem_name.length()) + "s", ""));

            KeyEncapsulation client = null;
            KeyEncapsulation server = null;
            try {
                // Create client and server
                client = new KeyEncapsulation(kem_name);
                server = new KeyEncapsulation(kem_name);

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
            } finally {
                if (client != null) {
                    client.dispose_KEM();
                }
                if (server != null) {
                    server.dispose_KEM();
                }
            }
        });
    }

    /**
     * Test the MechanismNotSupported Exception.
     */
    @Test
    public void testUnsupportedKEMExpectedException() {
        Assertions.assertThrows(MechanismNotSupportedError.class, () -> new KeyEncapsulation("MechanismNotSupported"));
    }

    private interface ThrowingRunnable {
        void run() throws Throwable;
    }

    private static void runWithLargeStack(String threadName, ThrowingRunnable action) throws Throwable {
        final AtomicReference<Throwable> error = new AtomicReference<>();
        Thread thread = new Thread(null, () -> {
            try {
                action.run();
            } catch (Throwable t) {
                error.set(t);
            }
        }, threadName, OQS_STACK_SIZE_BYTES);
        thread.start();
        thread.join();
        if (error.get() != null) {
            throw error.get();
        }
    }
}

