package com.chirp.community.service;

import com.chirp.community.entity.SiteUser;
import com.chirp.community.exception.CommunityException;
import com.chirp.community.model.EmailDto;
import com.chirp.community.model.SiteUserDto;
import com.chirp.community.properties.JwtProperties;
import com.chirp.community.properties.VerificationCodeProperties;
import com.chirp.community.repository.SiteUserRepository;
import com.chirp.community.type.RoleType;
import com.chirp.community.utils.ConvertToJwtClaims;
import com.chirp.community.utils.EmailContentUtils;
import com.chirp.community.utils.JwtTokenWithRS256Utils;
import com.chirp.community.utils.VerificationCodeUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PrivateKey;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final SiteUserRepository siteUserRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtProperties jwtProperties;
    private final PrivateKey privateKey;
    private final ConvertToJwtClaims<SiteUser> converterToJwtClaims;

    private final EmailService emailService;
    private final VerificationCodeService verificationCodeService;
    private final VerificationCodeProperties verificationCodeProperties;

    @Override
    public String getJwtToken(SiteUser entity) {
        Claims claims = converterToJwtClaims.convertToClaims(entity);
        return JwtTokenWithRS256Utils.generateJwtToken(claims, jwtProperties.getExpiredTimeMs(), privateKey);
    }

    @Transactional(readOnly = true)
    @Override
    public String getJwtToken(String email, String password) {
        SiteUser entity = siteUserRepository.findByEmail(email)
                .orElseThrow(
                        () -> CommunityException.of(HttpStatus.UNAUTHORIZED, String.format("'%s'는 존재하지 않는 계정입니다.", email))
                );

        if(!passwordEncoder.matches(password, entity.getPassword())) {
            throw CommunityException.of(HttpStatus.UNAUTHORIZED, "올바른 비밀번호가 아닙니다.");
        }

        return getJwtToken(entity);
    }

    @Override
    public void sendCodeWithEmail(Long id, String email, RoleType roleType) {
        // 인증 요청을 한 주체가 이메일 인증이 되어 있는지 여부를 따지기. 했으면 오류 발생.
        if(roleType.equals(RoleType.USER_VERIFIED_WITH_EMAIL)) {
            throw CommunityException.of(
                    HttpStatus.CONFLICT,
                    "해당 계정은 이미 이메일 인증되어 있습니다.",
                    String.format("해당 계정은 이미 이메일 인증되어 있습니다.\nid: '%s'\nemail: '%s'", id, email)
            );
        }

        // 인증을 요청한 주체 id와 보안 코드 6자리를 생성한다.
        String code = VerificationCodeUtils.generateCode(verificationCodeProperties.getNumOfCodeDigits());
        verificationCodeService.saveVerificationCode(id, code);


        // 인증을 요청한 주체의 이메일에 미리 작성된 안내문을 내보낸다.
        String origin = verificationCodeProperties.getRedirectOrigin();
        String path = verificationCodeProperties.getRedirectPath();
        EmailDto dto = EmailContentUtils.generateForEmailVerificationCode(origin, path, id, code);
        emailService.sendEmail(email, dto.title(), dto.content());
    }

    @Override
    public SiteUserDto verifyCodeWithEmail(Long userId, String codeMustBeConfirmed) {
        String codeLoadedByUserId = verificationCodeService.loadVerificationCode(userId);

        // 검증 결과, 보안코드가 같지 않다면 에러 발생.
        if(!codeMustBeConfirmed.equals(codeLoadedByUserId)) {
            throw CommunityException.of(
                    HttpStatus.BAD_REQUEST,
                    "제출된 보안 코드가 맞지 않습니다.",
                    String.format("제출된 보안 코드가 맞지 않습니다.\ncodeMustBeConfirmed: '%s'\nemail: 'codeLoadedByUserId'", codeMustBeConfirmed, codeLoadedByUserId)
            );
        }

        // 검증 결과, 문제가 없다면 '이메일 검증된 유저'로 승격.
        SiteUser entity = siteUserRepository.findById(userId).orElseThrow(
                () -> CommunityException.of(
                        HttpStatus.BAD_REQUEST,
                        "해당 계정은 존재하지 않습니다.",
                        String.format("해당 계정(id: '%s')은 존재하지 않습니다.", userId)
                )
        );
        entity.setRole(RoleType.USER_VERIFIED_WITH_EMAIL);
        SiteUser saved = siteUserRepository.save(entity);

        // 새로운 엔티티를 가지고 토큰 재발급
        String token = getJwtToken(saved);

        return SiteUserDto.fromEntity(saved, token);
    }
}
