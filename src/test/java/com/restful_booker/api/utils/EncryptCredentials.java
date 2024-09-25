package com.restful_booker.api.utils;

import com.restful_booker.api.payloads.ResponseData;

import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class EncryptCredentials {

    private static final  WriteToFile writeToFile = new WriteToFile();
    private static final String propertiesPath = System.getProperty("user.dir") + "/src/main/resources/config.properties";

    public static void main(String[] args) {

        try {
            SecretKey secretKey = EncryptionUtil.generateKey(); // Generate the secret key
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

            // Save Secret Key
            writeToFile.writeSecretKeyToFile(encodedKey);

            encryptAndSaveCredentials(secretKey);
        } catch (Exception e) {
            System.err.println("Key generation error: " + e.getMessage());
        }
    }

    private static void encryptAndSaveCredentials(SecretKey secretKey) {
        Properties properties = loadProperties();

        // Encrypt base_url
        String base_url = properties.getProperty("base_url");
        if (base_url != null) {
            String encryptedBaseUrl = EncryptionUtil.encrypt(base_url, secretKey);
            if (encryptedBaseUrl != null) {
                properties.setProperty("base_url", encryptedBaseUrl); // Use "base_url"
            }
        }

        // Encrypt username
        String username = properties.getProperty("username");
        if (username != null) {
            String encryptedUsername = EncryptionUtil.encrypt(username, secretKey);
            if (encryptedUsername != null) {
                properties.setProperty("username", encryptedUsername);
            }
        }

        // Encrypt password
        String password = properties.getProperty("password");
        if (password != null) {
            String encryptedPassword = EncryptionUtil.encrypt(password, secretKey);
            if (encryptedPassword != null) {
                properties.setProperty("password", encryptedPassword);
            }
        }

        // Save the updated properties file
        try (FileOutputStream outputStream = new FileOutputStream(propertiesPath)) {
            properties.store(outputStream, null);
            System.out.println("Encryption is done on config properties.");
        } catch (IOException e) {
            System.err.println("Could not save properties file: " + e.getMessage());
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(propertiesPath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.println("Could not load properties file: " + e.getMessage());
        }
        return properties;
    }
}
