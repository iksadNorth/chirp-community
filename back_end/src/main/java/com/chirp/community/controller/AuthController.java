package com.chirp.community.controller;


import com.chirp.community.model.SiteUserDto;
import com.chirp.community.model.request.AuthRequest;
import com.chirp.community.model.response.AuthReadResponse;
import com.chirp.community.model.response.KeyReadResponse;
import com.chirp.community.properties.JwtProperties;
import com.chirp.community.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController implements AuthDocs {
    private final AuthService authService;
    private final JwtProperties jwtProperties;

    @PostMapping("/access_token")
    public AuthReadResponse getJwtToken(@RequestBody AuthRequest request) {
        String token = authService.getJwtToken(request.email(), request.password());
        return AuthReadResponse.of(token);
    }

    @PostMapping("/email_verification_code")
    public void sendCodeWithEmail(@AuthenticationPrincipal SiteUserDto principal) {
        authService.sendCodeWithEmail(principal.id(), principal.email(), principal.role());
    }

    @PostMapping("/public_key")
    public KeyReadResponse getPublicKey() {
        String publicKey = jwtProperties.getPublicKeyTrimmed();
        return KeyReadResponse.of(publicKey);
    }
}
