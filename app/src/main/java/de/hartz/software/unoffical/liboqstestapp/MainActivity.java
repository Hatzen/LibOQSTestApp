package de.hartz.software.unoffical.liboqstestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.openquantumsafe.Common;
import org.openquantumsafe.KEMs;
import org.openquantumsafe.KeyEncapsulation;
import org.openquantumsafe.Pair;
import com.example.liboqstestapp.R;
import com.google.android.material.tabs.TabLayout;

import de.hartz.software.unofficial.liboqs.android.CommonAndroid;

public class MainActivity extends AppCompatActivity {

    static {
        // We need this instead of Common.loadNativeLibrary() as we cannot extract the lib on android.
        CommonAndroid.loadNativeLibrary();
    }

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

        algorithmNames= KEMs.get_enabled_KEMs().toArray(new String[0]);

        client1 = new Client();
        client2 = new Client();
        currentClient = client1;

        setupView();
        initCipher(0);

        updateScreen(getOtherClient());
    }

    private void initCipher (int position) {
        String cipher = algorithmNames[position];
        client1.kem = new KeyEncapsulation(cipher);
        client2.kem = new KeyEncapsulation(cipher);

        byte[] publicKeyClient1 = client1.kem.generate_keypair();

        Pair<byte[], byte[]> sharedSecretEncryptedAndPlainText = client2.kem.encap_secret(publicKeyClient1);
        client2.sharedSecret = Common.to_hex(sharedSecretEncryptedAndPlainText.getRight())
                .substring(0, 16);

        byte[] sharedSecretPlain = client1.kem.decap_secret(sharedSecretEncryptedAndPlainText.getLeft());
        client1.sharedSecret = Common.to_hex(sharedSecretPlain)
            .substring(0, 16);
    }

    private void updateScreen (Client otherClient) {
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
            }
            @Override public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        keyTextField = findViewById(R.id.keyText);

        plainTextField = findViewById(R.id.inputText);
        plainTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

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
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private Client getOtherClient() {
        if (currentClient == client1) {
            return client2;
        }
        return client1;
    }

}