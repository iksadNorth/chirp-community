package com.chirp.community.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DisplayName("ExceptionHandlerController 단위 테스트")
class ExceptionHandlerControllerTest {

    @Test
    @DisplayName("[extractColumn] 에러 메시지 중의 오류를 유발한 칼럼 추출")
    void extractColumn() {
        // Given
        String errorMessage = "could not execute statement; SQL [n/a]; constraint [\"PUBLIC.UK_8VLKW482T3GPNEBXCM03YWK9P_INDEX_8 ON " +
                "PUBLIC.SITE_USER(EMAIL NULLS FIRST) VALUES ( /* 1 */ 'email' )\"; SQL statement:\n" +
                "insert into site_user (id, created_at, email, nickname, password, role) values (default, ?, ?, ?, ?, ?) [23505-214]]";

        // When
        Optional<String> parsedColumn = ExceptionHandlerController.extractColumn(errorMessage);

        // then
        assertThat(parsedColumn.orElse("[Unknown]")).isEqualTo("email");

    }
}