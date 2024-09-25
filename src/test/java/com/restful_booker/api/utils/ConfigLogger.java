package com.restful_booker.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class ConfigLogger {
    private static final Logger logger = LogManager.getLogger(ConfigLogger.class);

    private ConfigLogger() {
    }

    public static Logger getLogger() {
        return logger;
    }
}
