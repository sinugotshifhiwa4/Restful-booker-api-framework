package com.restful_booker.api.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class WriteToFile {

    public final String filePath = System.getProperty("user.dir") + "/src/main/resources/secretKey.properties";

    public void writeSecretKeyToFile(String secretKey) {
        Properties properties = new Properties();
        properties.setProperty("SECRET_KEY", secretKey);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            properties.store(writer, null);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to file: " + e.getMessage(), e);
        }
    }

}
