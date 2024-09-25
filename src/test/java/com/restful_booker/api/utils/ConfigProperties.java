package com.restful_booker.api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

    private static final Properties properties = new Properties();
    private static final String propertiesPath = System.getProperty("user.dir") + "/src/main/resources/config.properties";

    static {
        try (FileInputStream inputStream = new FileInputStream(propertiesPath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to load properties from path: " + propertiesPath, e);
        }
    }

    public static String getPropertyByKey(String key) {
        String value = properties.getProperty(key);

        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("The value for the key '" + key + "' is null or empty.");
        }

        return value;
    }
}