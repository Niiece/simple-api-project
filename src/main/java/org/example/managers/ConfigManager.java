package org.example.managers;

import org.aeonbits.owner.ConfigFactory;
import org.example.config.Configuration;

public class ConfigManager {
    private static final Configuration CONFIG = ConfigFactory.create(Configuration.class, System.getProperties());

    public static Configuration getConfig() {
        return CONFIG;
    }
}