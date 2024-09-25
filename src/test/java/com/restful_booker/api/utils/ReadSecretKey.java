package com.restful_booker.api.utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class ReadSecretKey {

    private static final Properties properties = new Properties();
    private static final String propertiesPath = System.getProperty("user.dir") + "/src/main/resources/secretKey.properties";

    static {
        try (FileInputStream inputStream = new FileInputStream(propertiesPath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to load properties from path: " + propertiesPath, e);
        }
    }

    public static SecretKey getSecretKeyByKey(String key) {
        String value = properties.getProperty(key);

        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("The value for the key '" + key + "' is null or empty.");
        }

        byte[] decodedKey = Base64.getDecoder().decode(value);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
}
