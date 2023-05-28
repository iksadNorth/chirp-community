package com.chirp.community.service;

import com.chirp.community.model.ArticleCommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleCommentService {

    ArticleCommentDto readById(Long id);

    Page<ArticleCommentDto> readAllByArticleId(Long id, Pageable pageable);

    Page<ArticleCommentDto> readAllBySiteUserId(Long id, Pageable pageable);

    ArticleCommentDto createArticleComment(String content, Long article_id);

    ArticleCommentDto updateArticleComment(Long id, String content);

    void deleteById(Long id);


}
