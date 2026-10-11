package com.qa.framework.engine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GlobalParams {

    private static final Logger LOGGER = LogManager.getLogger(GlobalParams.class);

    private static final Path CONFIG_DIRECTORY =
            Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "config");

    private static final Path CONFIG_FILE_PATH = CONFIG_DIRECTORY.resolve("Config.properties");

    private static final Path API_CONFIG_FILE_PATH = CONFIG_DIRECTORY.resolve("APIConfig.properties");

    private static final Properties PROPERTIES = new Properties();

    private static volatile boolean initialized;

    private GlobalParams() {
    }

    public static void initializeGlobalParams() {
        if (initialized) {
            return;
        }
        synchronized (GlobalParams.class) {
            if (initialized) {
                return;
            }
            if (!Files.isRegularFile(CONFIG_FILE_PATH)) {
                throw new IllegalStateException("Required property file was not found: " + CONFIG_FILE_PATH);
            }
            Properties loaded = new Properties();
            loadProperties(loaded, CONFIG_FILE_PATH);
            if (Files.isRegularFile(API_CONFIG_FILE_PATH)) {
                loadProperties(loaded, API_CONFIG_FILE_PATH);
            }
            PROPERTIES.putAll(loaded);
            initialized = true;
            LOGGER.info("Framework properties loaded successfully. Total properties: {}", PROPERTIES.size());
        }
    }

    private static void loadProperties(Properties target, Path file) {
        try (InputStream inputStream = Files.newInputStream(file)) {
            target.load(inputStream);
            LOGGER.info("Loaded property file: {}", file);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load property file: " + file, exception);
        }
    }

    public static String getProperty(String key) {
        String value = getOptionalProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Required property is missing or empty in Config.properties: " + key);
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        String value = getOptionalProperty(key);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }

    public static String getOptionalProperty(String key) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Configuration property key cannot be null or blank.");
        }
        initializeGlobalParams();

        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue.trim();
        }
        String value = PROPERTIES.getProperty(key);
        return value == null ? null : value.trim();
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getOptionalProperty(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        if (!"true".equalsIgnoreCase(value) && !"false".equalsIgnoreCase(value)) {
            throw new IllegalArgumentException("Property '" + key + "' must be true or false. Actual value: " + value);
        }
        return Boolean.parseBoolean(value);
    }

    public static int getIntegerProperty(String key, int defaultValue) {
        String value = getOptionalProperty(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "Property '" + key + "' must be a valid integer. Actual value: " + value, exception);
        }
    }

    public static boolean containsProperty(String key) {
        String value = getOptionalProperty(key);
        return value != null && !value.isBlank();
    }

    public static String getConfigFilePath() {
        return CONFIG_FILE_PATH.toString();
    }
}
