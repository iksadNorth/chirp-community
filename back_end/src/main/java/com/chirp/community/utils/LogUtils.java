package com.chirp.community.utils;

import org.slf4j.Logger;
import org.springframework.boot.logging.LogLevel;

import java.util.Map;

public class LogUtils {
    public static void logWhenLoggingLevelIs(Logger log, LogLevel level, String value) {
        switch (level) {
            case TRACE -> log.trace(value);
            case DEBUG -> log.debug(value);
            case INFO -> log.info(value);
            case WARN -> log.warn(value);
            case ERROR, FATAL -> log.error(value);
            default -> {}
        }
    }

    public static LogLevel LogLevelOf(Logger log) {
        if(log.isTraceEnabled()) {
            return LogLevel.TRACE;
        } else if(log.isDebugEnabled()) {
            return LogLevel.DEBUG;
        } else if(log.isInfoEnabled()) {
            return LogLevel.INFO;
        } else if(log.isWarnEnabled()) {
            return LogLevel.WARN;
        } else if(log.isErrorEnabled()) {
            return LogLevel.ERROR;
        } else {
            return null;
        }
    }

    public static String makeLoggingMessage(Map<String, String> table) {
        StringBuilder builder = new StringBuilder();

        builder.append("\n<properties>");
        for(Map.Entry<String, String> entry : table.entrySet()) {
            String piece = String.format(
                    "\n%s|%s",
                    entry.getKey(), entry.getValue()
            );
            builder.append(piece);
        }
        builder.append("\n</properties>");

        return builder.toString();
    }
}
