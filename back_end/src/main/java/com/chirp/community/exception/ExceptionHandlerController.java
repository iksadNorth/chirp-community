package com.chirp.community.exception;

import com.chirp.community.model.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.chirp.community.utils.ConvertRequestToMap.convertRequestToMap;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(CommunityException.class)
    public ResponseEntity<ErrorResponse> handleCommunityException(CommunityException e) {
        log.warn(e.getDescriptionForServer());
        return new ResponseEntity<>(ErrorResponse.of(e.getDescriptionForClient()), e.getHttpStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        String errorMessage = extractColumn(e.getMessage())
                .map(ExceptionHandlerController::makeWarnMessage)
                .orElse(makeWarnMessage());

        log.warn(errorMessage);
        log.warn(makeLogMessage(request));
        return new ResponseEntity<>(ErrorResponse.of(errorMessage), HttpStatus.CONFLICT);
    }

    public static Optional<String> extractColumn(String trgStr) {
        Pattern pattern = Pattern.compile(" \\*\\/ '(.*)' \\)\"");

        String frontUnnecessary = "\\*\\/ '";
        String rearUnnecessary = "' \\)\"";

        Matcher matcher = pattern.matcher(trgStr);
        if(matcher.find()) {
            String trg = matcher.group();
            String trgTrim = trg.substring(frontUnnecessary.length() - 1, trg.length() - rearUnnecessary.length() + 1);
            return Optional.of(trgTrim);
        } else {
            return Optional.empty();
        }
    }

    public static String makeWarnMessage(String column) {
        return String.format("입력한 '%s'가 이미 존재합니다. 다른 값을 입력해주세요.", column);
    }

    public static String makeWarnMessage() {
        return "빈 칸으로 체출된 것이 있습니다. 다른 값을 입력해주세요.";
    }

    public static String makeLogMessage(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();

        Set<Map.Entry<String, String>> entries = convertRequestToMap(request).entrySet();
        for(Map.Entry<String, String> entry : entries) {
            builder.append(String.format("key: %s, value: $s\n", entry.getKey(), entry.getValue()));
        }
        return builder.toString();
    }
}
