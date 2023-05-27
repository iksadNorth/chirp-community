package com.chirp.community.controller;


import com.chirp.community.model.SiteUserDto;
import com.chirp.community.model.request.AuthRequest;
import com.chirp.community.model.response.AuthReadResponse;
import com.chirp.community.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthReadResponse getJwtToken(@RequestBody AuthRequest request) {
        String token = authService.getJwtToken(request.email(), request.password());
        return AuthReadResponse.of(token);
    }

    @PostMapping("/email_verification_code")
    public void sendCodeWithEmail(@AuthenticationPrincipal SiteUserDto principal) {
        authService.sendCodeWithEmail(principal.id(), principal.email(), principal.role());
    }

    @GetMapping("/email_verification_code")
    public AuthReadResponse getCodeWithEmail(@RequestParam("user_id") Long user_id, @RequestParam("code") String code) {
        String token = authService.verifyCodeWithEmail(user_id, code);
        return AuthReadResponse.of(token);
    }
}
