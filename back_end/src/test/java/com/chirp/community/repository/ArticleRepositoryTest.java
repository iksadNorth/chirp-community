package com.chirp.community.repository;

import com.chirp.community.entity.Article;
import com.chirp.community.model.ArticleDto;
import com.chirp.community.model.ArticleDtoWithNumLikes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
        Page<ArticleDtoWithNumLikes> page = articleRepository.readBestByLikes(weekAgo, pageable);

        // then
//        page.map(ArticleMapperWithBestLikes::getNumLikes)
//                .map(String::valueOf)
//                .forEach(log::info);
    }
}