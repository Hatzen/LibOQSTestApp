package com.example.liboqstestapp;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SymmetricEncryptionHelper {

    public static SymmetricEncryptionHelper useDefaultIv(String key) {
        // TODO: Dont use static IV better transmit the IV in plaintext within the message.
        byte[] iv = new byte[] {'#', '0', 'a', '0', 'N', '0', '0', 'z', '1', '1', '_', '0', '0', 'x', 'U', '0' };
        return new SymmetricEncryptionHelper(key.getBytes(), iv);
    }

    private String AES_ALGORITHM = "AES/CBC/PKCS5Padding";

    private byte[] aesKey;
    private byte[] iv;

    private SecretKeySpec secretKeySpec;
    private IvParameterSpec ivSpec;

    public SymmetricEncryptionHelper ( byte[] aesKey, byte[] iv) {
        this.aesKey = aesKey;
        this.iv = iv;

        ivSpec = new IvParameterSpec(iv);
        secretKeySpec = new SecretKeySpec(aesKey, AES_ALGORITHM);
    }


    public String decrypt(String text) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
            byte[] decryptedBytes = cipher.doFinal(text.getBytes());
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong.");
        }
    }

    public String encrypt(String text){
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
            byte[] encrypted  = cipher.doFinal(text.getBytes());
            return new String(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong.");
        }
    }
}
