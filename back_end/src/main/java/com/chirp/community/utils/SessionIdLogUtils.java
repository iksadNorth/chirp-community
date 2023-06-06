package com.chirp.community.utils;

import lombok.Builder;
import org.slf4j.Logger;
import org.springframework.boot.logging.LogLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SessionIdLogUtils {
    public static <T> SessionIdResult<T> returnWithLogs(SessionIdDto dto, Supplier<T> joinPoint) {
        // MapForLog 생성.
        Map<String, String> mapForLog = new HashMap<>();

        // 실행 시간 측정.
        long startTime = System.currentTimeMillis();

        // 메서드 실행.
        T result = joinPoint.get();

        // 실행 시간 로깅.
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        mapForLog.put("execution-time-milli-seconds", String.valueOf(executionTime));

        // 로깅 위치 로깅.
        mapForLog.put("class-logging", dto.nameClassLogging());
        mapForLog.put("method-logging", dto.nameMethodLogging());

        // Page Session ID 로깅.
        mapForLog.put("page-session-id", dto.pageSessionId());

        // 요청 Session ID 로깅.
        mapForLog.put("http-request-session-id", dto.reqSessionId());

        return SessionIdResult.<T>builder()
                .result(result)
                .mapForLog(mapForLog)
                .build();
    }

    public static SessionIdResult<Void> returnWithLogs(SessionIdDto dto, Runnable joinPoint) {
        return returnWithLogs(dto, () -> {
            joinPoint.run();
            return null;
        });
    }

    @Builder(toBuilder = true)
    public record SessionIdDto(
            String nameClassLogging, String nameMethodLogging,
            String pageSessionId, String reqSessionId
    ) {}

    @Builder(toBuilder = true)
    public record SessionIdResult<T>(
            T result,
            Map<String, String> mapForLog
    ) {
        public SessionIdResult<T> put(String key, String value) {
            mapForLog.put(key, value);
            return this;
        }

        public T doWithMessage(Consumer<Map<String, String>> logFunc) {
            logFunc.accept(mapForLog);
            return result;
        }
    }
}
