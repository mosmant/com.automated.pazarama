package com.automated.pazarama.Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        String path = "src/main/resources/configuration.properties";
        try {
            FileInputStream file = new FileInputStream(path);
            properties = new Properties();
            properties.load(file);
        } catch (Exception e) {
            System.out.println("Configuration file couldn't found.");
        }
    }

    public static String getProperty(String key) {

        return properties.getProperty(key);
    }
}
