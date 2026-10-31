package com.enterprise.app.config;

import java.util.HashMap;
import java.util.Map;

/**
 * =============================================================================
 *          DESIGN PATTERN: SINGLETON (Configuration Management)
 * =============================================================================
 * Ensures a single, globally accessible configuration registry across the application.
 * Implemented using the thread-safe Bill Pugh Initialization-on-demand holder idiom.
 */
public class AppConfig {

    private final Map<String, String> properties = new HashMap<>();

    // Private constructor prevents instantiation from other classes
    private AppConfig() {
        properties.put("app.name", "EnterpriseJavaApp");
        properties.put("app.version", "1.0.0");
        properties.put("app.environment", "Production");
        properties.put("app.currency", "USD");
    }

    // Static inner holder class loaded only upon the first call to getInstance()
    private static class Holder {
        private static final AppConfig INSTANCE = new AppConfig();
    }

    public static AppConfig getInstance() {
        return Holder.INSTANCE;
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

    public void setProperty(String key, String value) {
        properties.put(key, value);
    }
}
