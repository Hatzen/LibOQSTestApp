package de.hartz.software.unofficial.liboqstestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.tabs.TabLayout;

import org.openquantumsafe.Common;
import org.openquantumsafe.KEMs;
import org.openquantumsafe.KeyEncapsulation;
import org.openquantumsafe.Pair;

import java.util.concurrent.atomic.AtomicReference;

import de.hartz.software.unofficial.liboqs.android.CommonAndroid;

public class MainActivity extends AppCompatActivity {

    static {
        CommonAndroid.loadNativeLibrary();
    }

    private static final long OQS_STACK_SIZE_BYTES = 8L * 1024 * 1024;

    private static class Client {
        KeyEncapsulation kem;
        String sharedSecret;
        String encryptedText;
    }

    Client client1;
    Client client2;

    Client currentClient;

    private String[] algorithmNames;

    EditText keyTextField;
    EditText plainTextField;
    EditText encryptedTextField;
    EditText decryptedTextField;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Serverless Example");

        algorithmNames = KEMs.get_enabled_KEMs().toArray(new String[0]);

        client1 = new Client();
        client2 = new Client();
        currentClient = client1;

        setupView();
        initCipher(0);

        updateScreen(getOtherClient());
    }

    @Override
    protected void onDestroy() {
        disposeClientKem(client1);
        disposeClientKem(client2);
        super.onDestroy();
    }

    private static void disposeClientKem(Client client) {
        if (client != null && client.kem != null) {
            client.kem.dispose_KEM();
            client.kem = null;
        }
    }

    private void initCipher(int position) {
        final String cipher = algorithmNames[position];
        disposeClientKem(client1);
        disposeClientKem(client2);

        // Some algorithms (e.g. Classic McEliece) exceed Android's default thread stack size.
        final AtomicReference<Throwable> error = new AtomicReference<>();
        Thread worker = new Thread(null, () -> {
            try {
                client1.kem = new KeyEncapsulation(cipher);
                client2.kem = new KeyEncapsulation(cipher);

                byte[] publicKeyClient1 = client1.kem.generate_keypair();

                Pair<byte[], byte[]> sharedSecretEncryptedAndPlainText = client2.kem.encap_secret(publicKeyClient1);
                client2.sharedSecret = Common.to_hex(sharedSecretEncryptedAndPlainText.getRight())
                        .substring(0, 16);

                byte[] sharedSecretPlain = client1.kem.decap_secret(sharedSecretEncryptedAndPlainText.getLeft());
                client1.sharedSecret = Common.to_hex(sharedSecretPlain)
                        .substring(0, 16);
            } catch (Throwable t) {
                error.set(t);
            }
        }, "oqs-kem-init", OQS_STACK_SIZE_BYTES);

        worker.start();
        try {
            worker.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while initializing KEM " + cipher, e);
        }

        if (error.get() != null) {
            throw new RuntimeException("Failed to initialize KEM " + cipher, error.get());
        }
    }

    private void updateScreen(Client otherClient) {
        keyTextField.setText(currentClient.sharedSecret);

        String plainText = plainTextField.getText().toString();
        String encrypted = SymmetricEncryptionHelper.useDefaultIv(currentClient.sharedSecret).encrypt(plainText);
        encryptedTextField.setText(encrypted);

        encrypted = otherClient.encryptedText;
        if (encrypted == null || "".equals(encrypted)) {
            return;
        }
        plainText = SymmetricEncryptionHelper.useDefaultIv(currentClient.sharedSecret).decrypt(encrypted);
        decryptedTextField.setText(plainText);
    }

    private void setupView() {
        Spinner dropdown = findViewById(R.id.supportedAlgorithms);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, algorithmNames);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initCipher(i);
                updateScreen(getOtherClient());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        keyTextField = findViewById(R.id.keyText);

        plainTextField = findViewById(R.id.inputText);
        plainTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Client otherClient = getOtherClient();
                updateScreen(otherClient);
            }
        });
        encryptedTextField = findViewById(R.id.outputText);
        decryptedTextField = findViewById(R.id.outputText2);

        tabLayout = findViewById(R.id.simpleTabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentClient.encryptedText = encryptedTextField.getText().toString();

                keyTextField.setText("");
                plainTextField.setText("");
                encryptedTextField.setText("");
                decryptedTextField.setText("");

                Client otherClient = currentClient;
                currentClient = getOtherClient();
                updateScreen(otherClient);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private Client getOtherClient() {
        if (currentClient == client1) {
            return client2;
        }
        return client1;
    }

}

