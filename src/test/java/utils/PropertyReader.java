package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                // Instead of returning, you can throw a runtime exception
                throw new RuntimeException("Config file not found");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            // You might want to throw an exception here as well
            throw new RuntimeException("Failed to load properties from config file", ex);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
