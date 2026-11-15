package de.hartz.software.unofficial.liboqs.android;

import android.util.Log;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openquantumsafe.MechanismNotSupportedError;
import org.openquantumsafe.Rand;
import org.openquantumsafe.Signature;
import org.openquantumsafe.Sigs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(Parameterized.class)
public class SignatureTest {

    static {
        CommonAndroid.loadNativeLibrary();
    }

    private static final long OQS_STACK_SIZE_BYTES = 8L * 1024 * 1024;

    @Parameterized.Parameter(value = 0)
    public String sig_name;

    /**
     * Method to convert the list of Sigs to a list for input to testAllSigs.
     */
    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> getEnabledSigs() {
        CommonAndroid.loadNativeLibrary();
        System.out.println("Initialize list of enabled Signatures");
        ArrayList<String> enabled_sigs = Sigs.get_enabled_sigs();

        // Do not use java streams as they are only supported on Android Nougat (7.0 = SDK 24) and above.
        List<Object[]> parameters = new ArrayList<>();
        for (String sigName : enabled_sigs) {
            parameters.add(new Object[]{sigName});
        }
        return parameters;
    }

    private final byte[] message = "This is the message to sign".getBytes();
    // The ML-DSA-*-extmu variants sign an externally computed 64-byte mu
    // instead of the message itself.
    private final byte[] mu = Rand.randombytes(64);

    /**
     * Test all enabled Sigs.
     */
    @Test
    public void testAllSigs() throws Throwable {
        runWithLargeStack("oqs-sig-" + sig_name, () -> {
            Log.d(SignatureTest.class.getSimpleName(), "Test " + sig_name);
            StringBuilder sb = new StringBuilder();
            sb.append(sig_name);
            sb.append(String.format("%1$" + Math.max(1, 40 - sig_name.length()) + "s", ""));

            Signature signer = null;
            Signature verifier = null;
            try {
                // Create signer and verifier
                signer = new Signature(sig_name);
                verifier = new Signature(sig_name);

                // Generate signer key pair
                byte[] signer_public_key = signer.generate_keypair();

                byte[] to_sign = sig_name.endsWith("-extmu") ? mu : message;

                // Sign the message
                byte[] signature = signer.sign(to_sign);

                // Verify the signature
                boolean is_valid = verifier.verify(to_sign, signature, signer_public_key);

                assertTrue(is_valid, sig_name);

                // If successful print Sig name, otherwise an exception will be thrown
                sb.append("\033[0;32m").append("PASSED").append("\033[0m");
                System.out.println(sb.toString());
            } finally {
                if (signer != null) {
                    signer.dispose_sig();
                }
                if (verifier != null) {
                    verifier.dispose_sig();
                }
            }
        });
    }

    /**
     * Test the MechanismNotSupported Exception.
     */
    @Test
    public void testUnsupportedSigExpectedException() {
        Assertions.assertThrows(MechanismNotSupportedError.class, () -> new Signature("MechanismNotSupported"));
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

