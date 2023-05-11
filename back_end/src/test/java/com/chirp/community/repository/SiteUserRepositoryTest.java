package com.chirp.community.repository;

import com.chirp.community.entity.SiteUser;
import com.chirp.community.utils.SetProfile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static com.chirp.community.exception.ExceptionHandlerController.extractColumn;
import static com.chirp.community.exception.ExceptionHandlerController.makeWarnMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@DataJpaTest
@SetProfile
@DisplayName("SiteUserRepository 단위 테스트")
class SiteUserRepositoryTest {
    @Autowired
    private SiteUserRepository siteUserRepository;

    @Test
    @DisplayName("[extractColumn][makeWarnMessage] DataIntegrityViolationException 에러내용을 분석하는 메서드 정상작동 확인.")
    void GivenEntity_WhenSaveSameEntityAboutEmail_ThenReturnErrorMessages() {
        // Given
        SiteUser entity1 = SiteUser.of("email", "password", "nickname");
        SiteUser entity2 = SiteUser.of("email", "password", "nickname");
        siteUserRepository.save(entity1);

        // When & Then
        try {
            siteUserRepository.save(entity2);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());

            String extractColumn = extractColumn(e.getMessage()).orElse("[알 수 없는 데이터]");
            log.error(extractColumn);

            log.error(makeWarnMessage(extractColumn));
        }
    }
    @Test
    @DisplayName("[save] email의 UniQue 제약조건에 반하는 엔티티 생성.")
    void GivenEntity_WhenSaveSameEntityAboutEmail_ThenThrowError() {
        // Given
        SiteUser entity1 = SiteUser.of("email", "password", "nickname1");
        SiteUser entity2 = SiteUser.of("email", "password", "nickname2");
        siteUserRepository.save(entity1);

        // When & Then
        assertThrows(DataIntegrityViolationException.class, () -> {siteUserRepository.save(entity2);});
    }

    @Test
    @DisplayName("[save] nickname의 UniQue 제약조건에 반하는 엔티티 생성.")
    void GivenEntity_WhenSaveSameEntityAboutNickname_ThenThrowError() {
        // Given
        SiteUser entity1 = SiteUser.of("email1", "password", "nickname");
        SiteUser entity2 = SiteUser.of("email2", "password", "nickname");
        siteUserRepository.save(entity1);

        // When & Then
        assertThrows(DataIntegrityViolationException.class, () -> {siteUserRepository.save(entity2);});
    }
}