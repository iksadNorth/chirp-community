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
        String errorColumn = extractColumn(e.getMessage()).orElse("[알 수 없는 데이터]");
        String errorMessage = makeWarnMessage(errorColumn);

        log.warn(errorMessage);
        log.warn(makeLogMessage(request));
        return new ResponseEntity<>(ErrorResponse.of(errorMessage), HttpStatus.CONFLICT);
    }

    public static Optional<String> extractColumn(String trgStr) {
        Pattern pattern = Pattern.compile(" \\*\\/ '([a-zA-Z]+)' \\)\"");

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
        return String.format("입력한 %s가 이미 존재하거나 빈 칸으로 제출되었습니다. 다른 %s를 입력해주세요.", column, column);
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
