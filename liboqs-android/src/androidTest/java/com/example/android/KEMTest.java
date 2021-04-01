package com.example.android;

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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@RunWith(Parameterized.class)
public class KEMTest {

    @Parameterized.Parameter(value = 0)
    public String kem_name;

    /**
     * Method to convert the list of KEMs to a stream for input to testAllKEMs
     */
    @Parameterized.Parameters
    public static List<Object> getEnabledKEMsAsStream() {
        System.out.println("Initialize list of enabled KEMs");
        ArrayList<String> enabled_kems = KEMs.get_enabled_KEMs();
        return enabled_kems.parallelStream().map(e ->
            new Object[] { e }
        ).collect(Collectors.toList());
    }

    /**
     * Test all enabled KEMs
     */
    @ParameterizedTest(name = "Testing {arguments}")
    @MethodSource("getEnabledKEMsAsStream")
    public void testAllKEMs() {
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
    }

    /**
     * Test the MechanismNotSupported Exception
     */
    @Test
    public void testUnsupportedKEMExpectedException() {
        Assertions.assertThrows(MechanismNotSupportedError.class, () -> new KeyEncapsulation("MechanismNotSupported"));
    }
}
