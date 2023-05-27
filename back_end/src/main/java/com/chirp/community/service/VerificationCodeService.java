package com.chirp.community.service;

public interface VerificationCodeService {
    void saveVerificationCode(Long id, String code);
    String loadVerificationCode(Long id);
}
