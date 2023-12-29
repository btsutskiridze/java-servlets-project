package com.bakari.servletsdemo.config;

import java.io.InputStream;

public class Properties {

    private static final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    public static String get(String key) {
        InputStream inputStream = classLoader.getResourceAsStream("application.properties");
        java.util.Properties properties = new java.util.Properties();
        try {
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return properties.getProperty(key);
    }
}
