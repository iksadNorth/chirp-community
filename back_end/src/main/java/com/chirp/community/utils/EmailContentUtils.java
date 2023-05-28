package com.chirp.community.utils;

import com.chirp.community.model.EmailDto;
import org.springframework.web.util.UriComponentsBuilder;

public class EmailContentUtils {
    public static EmailDto generateForEmailVerificationCode(Long userId, String code) {
        return EmailDto.builder()
                .title(generateTitleForEmailVerificationCode())
                .content(generateContentForEmailVerificationCode(userId, code))
                .build();
    }

    private static String generateTitleForEmailVerificationCode() {
        return "[Chirp_Community][가입_계정_확인] Account Email Verification";
    }

    private static String generateContentForEmailVerificationCode(Long userId, String code) {
        // 안내문에 포함되는 내용: 메일 전송 이유 및 경고문, 보안 코드, 보안 코드를 검증하는 URI.
        return String.format("""
                해당 메일은 Chirp Community 계정을 보안을 위한 이메일 확인용 메일입니다.
                
                [보안 코드]
                %s
                
                [경고]
                만약 Chirp Community에서 이메일 보안 코드를 요청하지 않았는데 메일을 받은 것이라면,
                아래 링크를 절대 누르지 마세요.
                
                [보안 코드 자동 인식 링크]
                %s
                """, code, generateQueryUriForEmailVerificationCode(userId, code));
    }

    private static String generateQueryUriForEmailVerificationCode(Long userId, String code) {
        // AuthController.getCodeWithEmail 참고.
        String origin = "http://localhost:3000";
        String path = "/myPage";

        return UriComponentsBuilder.fromOriginHeader(origin)
                .path(path)
                .queryParam("user_id", userId)
                .queryParam("code", code)
                .build()
                .toUriString();
    }
}
