package com.chirp.community.service;

public interface AuthService {
    String getJwtToken(String email, String password);
}
