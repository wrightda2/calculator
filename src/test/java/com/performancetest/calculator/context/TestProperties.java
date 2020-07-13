package com.performancetest.calculator.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestProperties {

    private static Properties testProperties;
    private static String PROPERTY_FILE_NAME = "test.properties";

    /**
     * <p>Looks for the property value for the supplied key<br>
     * Expects that a test.properties file to exist in Thread.currentThread().getContextClassLoader()</p>
     *
     * @param key Property name
     * @return value as String
     */
    public static String getAsString(String key) {
        if (testProperties == null)
            init();

        String value = testProperties.getProperty(key);

        if(value == null)
            throw new RuntimeException(String.format("Could not find property %s in %s", key, PROPERTY_FILE_NAME));

        return value;
    }

    /**
     * <p>Looks for the property value for the supplied key and parse as integer<br>
     * Expects that a test.properties file to exist in Thread.currentThread().getContextClassLoader()</p>
     *
     * @param key Property name
     * @return value as Integer
     */
    public static int getAsInt(String key) {
        return Integer.parseInt(getAsString(key));
    }

    /**
     * <p>Looks for the property value for the supplied key and parse as Boolean<br>
     * Expects that a test.properties file to exist in Thread.currentThread().getContextClassLoader()</p>
     *
     * @param key Property name
     * @return value as Boolean
     */
    public static Boolean getAsBool(String key) {
        return Boolean.parseBoolean(getAsString(key));
    }

    private static void init() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        testProperties = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(PROPERTY_FILE_NAME)) {
            testProperties.load(resourceStream);
            testProperties.putAll(System.getProperties());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

