package com.restful_booker.api.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class EncryptionUtil {

    private static final String ALGORITHM = "AES";

    // Generate a new AES Key
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128); // For example, use 128-bit key
        return keyGen.generateKey();
    }

    public static String encrypt(String strToEncrypt, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(strToEncrypt.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
            return null; // Return null or handle the error as needed
        }
    }

    public static String decrypt(String strToDecrypt, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));
            return new String(decryptedBytes);
        } catch (Exception e) {
            System.err.println("Decryption error: " + e.getMessage());
            return null; // Return null or handle the error as needed
        }
    }
}
