package com.chirp.community.controller;

import com.chirp.community.model.SiteUserDto;
import com.chirp.community.model.request.AuthRequest;
import com.chirp.community.model.response.AuthReadResponse;
import com.chirp.community.model.response.ErrorResponse;
import com.chirp.community.model.response.KeyReadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Auth API",
        description = "보안 관련 API"
)
public interface AuthDocs {
    @Operation(
            summary = "JWT 토큰 발급 API",
            description = """
                    이메일과 비밀 번호를 제출하면 정합성 확인 후,\s
                    JWT 토큰을 Body의 token으로 전달.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AuthReadResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "401",
            description = "'~~'는 존재하지 않는 계정입니다. OR 올바른 비밀번호가 아닙니다.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    AuthReadResponse getJwtToken(
            @Parameter(
                    description = "로그인 시, 필요한 이메일과 비밀번호",
                    schema = @Schema(implementation = AuthRequest.class)
            )
            @RequestBody AuthRequest request
    );

    @Operation(
            summary = "이메일 보안 코드 발급 API",
            description = """
                    이메일 인증으로 사용자의 계정의 실존 여부를 따지는 API.
                    해당 API로 JWT 토큰을 전송하면, 해당 계정의 Email로 
                    일련의 보안 코드를 전송. 사용자는 보안 코드를 적절한 방식으로
                    API 서버로 전송함. 이 전송된 보안 코드를 서버에서 대조한 뒤,
                    적절하다면 User 계정 권한을 승격시킴.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동 - 그저 200 응답만 보낼 뿐이다.",
            content = @Content(
                    mediaType = MediaType.TEXT_PLAIN_VALUE
            )
    )
    @ApiResponse(
            responseCode = "409",
            description = "해당 계정은 이미 이메일 인증되어 있습니다.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    void sendCodeWithEmail(
            @AuthenticationPrincipal SiteUserDto principal
    );

    @Operation(
            summary = "공개키 발급 API",
            description = """
                    JWT 해독용 키인 공개키를 전달하기 위한 API.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = KeyReadResponse.class)
            )
    )
    KeyReadResponse getPublicKey();
}
