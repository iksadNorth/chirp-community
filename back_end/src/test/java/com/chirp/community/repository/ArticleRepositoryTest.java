package com.chirp.community.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.chirp.community.entity.projection.ArticleMapperWithNumLikes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@DataJpaTest
class ArticleRepositoryTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void readBestByLikes() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekAgo = now.minus(1, ChronoUnit.WEEKS);
        Pageable pageable = PageRequest.of(0, 5);

        // When
        Page<ArticleMapperWithNumLikes> page = articleRepository.readBestByLikes(weekAgo, pageable);

        // then
        page.forEach(mapper -> {
            log.info(mapper.getArticle().toString());
            log.info(String.valueOf(mapper.getNumLikes()));
        });
    }
}