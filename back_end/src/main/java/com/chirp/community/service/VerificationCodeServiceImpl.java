package com.chirp.community.service;

import com.chirp.community.entity.VerificationCode;
import com.chirp.community.exception.CommunityException;
import com.chirp.community.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {
    private final VerificationCodeRepository verificationCodeRepository;

    @Override
    public void saveVerificationCode(Long id, String code) {
        verificationCodeRepository.save(VerificationCode.of(id, code));
    }

    @Override
    public String loadVerificationCode(Long id) {
        return verificationCodeRepository.findById(id)
                .map(VerificationCode::getCode)
                .orElseThrow(() -> CommunityException.of(
                        HttpStatus.BAD_REQUEST,
                        "해당 유저는 보안 코드를 발급받은 적이 없거나 발급 유효 기간을 지나쳤습니다.",
                        String.format("해당 유저(id: '%s')는 보안 코드를 발급받은 적이 없거나 발급 유효 기간을 지나쳤습니다.", id)
                ));
    }
}
