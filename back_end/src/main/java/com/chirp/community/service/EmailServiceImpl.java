package com.chirp.community.service;

import com.chirp.community.exception.CommunityException;
import com.chirp.community.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;

    @Override
    public void sendEmail(String email, String title, String content) {
        try {
            emailRepository.send(email, title, content);

        } catch (MailParseException mailParseException) {
            throw CommunityException.of(
                    HttpStatus.BAD_REQUEST,
                    "잘못된 이메일 형식이나 파싱할 수 없는 본문 내용이 포함되어 있습니다."
            );
        } catch (MailAuthenticationException mailParseException) {
            throw CommunityException.of(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "서버 내부에 문제로 인해 진행이 불가합니다.",
                    "잘못된 사용자 이름 또는 비밀번호를 사용하여 메일 서버에 인증을 시도했거나, 인증이 필요한 메일 서버에 접근할 수 없습니다."
            );
        } catch (MailSendException mailParseException) {
            throw CommunityException.of(
                    HttpStatus.CONFLICT,
                    "이메일 주소가 잘못되었습니다.",
                    "이메일 전송과 관련된 문제로 예측되며, 해결하기 위해서는 이메일 주소의 유효성을 확인하거나, 메일 서버 연결을 확인하거나, 전송 실패한 이메일 주소를 처리해야 합니다."
            );
        }
    }
}
