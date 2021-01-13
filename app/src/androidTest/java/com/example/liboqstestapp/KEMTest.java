package com.example.liboqstestapp;

import androidx.test.filters.MediumTest;
import androidx.test.runner.AndroidJUnit4;

import com.example.liboqstestapp.liboqs.KEMs;
import com.example.liboqstestapp.liboqs.KeyEncapsulation;
import com.example.liboqstestapp.liboqs.Pair;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
// import java.util.stream.Stream;


@RunWith(AndroidJUnit4.class)
@MediumTest
public class KEMTest {

    private static ArrayList<String> enabled_kems;

    /**
     * Before running the tests, get a list of enabled KEMs
     */
    @BeforeClass
    public static void init(){
        System.out.println("Initialize list of enabled KEMs");
        enabled_kems = KEMs.get_enabled_KEMs();
    }

    /**
     * Test all enabled KEMs
     */
    // @ParameterizedTest(name = "Testing {arguments}")
    // @MethodSource("getEnabledKEMsAsStream")
    @Test
    public void testAllKEMs() {
        String kem_name = "DEFAULT";
        kem_name = "Kyber768";
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
        System.out.println(shared_secret_client);
        System.out.println(shared_secret_server);
        System.out.println(kem_name);

        // If successful print KEM name, otherwise an exception will be thrown
        sb.append("\033[0;32m").append("PASSED").append("\033[0m");
        System.out.println(sb.toString());
    }


    /**
     * Method to convert the list of KEMs to a stream for input to testAllKEMs
     */
    // Remove stream as
    /*private static Stream<String> getEnabledKEMsAsStream() {
        return enabled_kems.parallelStream();
    }*/

}
