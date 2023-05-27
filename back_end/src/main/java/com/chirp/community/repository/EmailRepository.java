package com.chirp.community.repository;

import org.springframework.mail.MailException;

public interface EmailRepository {
    void send(String email, String title, String content)  throws MailException;
}
