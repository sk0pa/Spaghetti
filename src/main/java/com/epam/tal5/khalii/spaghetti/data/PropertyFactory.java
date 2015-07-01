package com.epam.tal5.khalii.spaghetti.data;

import org.testng.Reporter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyFactory {
    public static Properties properties;
    public static final String TEST_PROPERTY_FILE = "test.properties";

    public static void getInstance() {
        Properties properties = new Properties();
        InputStream is;
        try {
            is = Files.newInputStream(Paths.get(".").resolve(TEST_PROPERTY_FILE));
            properties.load(is);
            PropertyFactory.properties = properties;
        } catch (FileNotFoundException e) {
            Reporter.log(e.getMessage());
        } catch (IOException e) {
            Reporter.log(e.getMessage());
        }
    }

    public static String getProperty(String property){
        getInstance();
        return properties.getProperty(property);
    }
}
