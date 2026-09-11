package com.portfolio.api.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigReader se encarga de cargar las propiedades desde el archivo config.properties.
 * Permite centralizar la configuración del proyecto y evitar el hardcoding.
 */
public class ConfigReader {

    private static Properties properties;

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties = new Properties();
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties. Please ensure it is in src/test/resources");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error loading config.properties");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }
}
