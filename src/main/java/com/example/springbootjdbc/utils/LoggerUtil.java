package com.example.springbootjdbc.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    public static String newLine = "\n";
    static Logger logger;

    static {
        logger = LogManager.getLogger(LoggerUtil.class);
    }

    public static Logger getLogger() {
        return logger;
    }
}
