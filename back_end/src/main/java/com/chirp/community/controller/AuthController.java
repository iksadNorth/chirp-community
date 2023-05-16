package com.chirp.community.controller;


import com.chirp.community.model.request.AuthRequest;
import com.chirp.community.model.response.AuthReadResponse;
import com.chirp.community.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
