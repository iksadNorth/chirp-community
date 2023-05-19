package com.chirp.community.utils;

import com.chirp.community.exception.CommunityException;
import org.springframework.http.HttpStatus;

public class CheckTools {
    public static void nullCheck(String... objects) {
        for(String obj : objects) {
            if(obj.isBlank()) {
                throw CommunityException.of(
                        HttpStatus.BAD_REQUEST,
                        "빈 칸으로 체출된 것이 있습니다. 다른 값을 입력해주세요.",
                        "입력된 파라미터들: '" + String.join("', '", objects) + "'"
                );
            }
        }
    }
}
