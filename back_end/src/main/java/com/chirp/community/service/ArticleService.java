package com.chirp.community.service;

import com.chirp.community.model.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {
    ArticleDto create(String title, String content, Long board_id);

    ArticleDto readById(Long id);

    ArticleDto updateById(Long id, String title, String content);

    void deleteById(Long id);

    Page<ArticleDto> readByBoardId(Long id, Pageable pageable);

    Page<ArticleDto> readBySiteUserId(Long id, Pageable pageable);
}