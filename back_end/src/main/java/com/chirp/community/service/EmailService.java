package com.chirp.community.service;

public interface EmailService {
    void sendEmail(String email, String title, String content);
}
