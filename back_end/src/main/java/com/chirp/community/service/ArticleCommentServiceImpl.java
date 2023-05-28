package com.chirp.community.service;

import com.chirp.community.entity.Article;
import com.chirp.community.entity.ArticleComment;
import com.chirp.community.exception.CommunityException;
import com.chirp.community.model.ArticleCommentDto;
import com.chirp.community.repository.ArticleCommentRepository;
import com.chirp.community.repository.ArticleRepository;
import com.chirp.community.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommentServiceImpl implements ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    private Article loadArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(
                        () -> CommunityException.of(
                                HttpStatus.NOT_FOUND,
                                String.format("%s번 게시물은 존재하지 않음.", id)
                        )
                );
    }

    private ArticleComment loadArticleCommentById(Long id) {
        return articleCommentRepository.findById(id)
                .orElseThrow(
                        () -> CommunityException.of(
                                HttpStatus.NOT_FOUND,
                                String.format("%s번 댓글은 존재하지 않음.", id)
                        )
                );
    }


    @Override
    @Transactional(readOnly = true)
    public ArticleCommentDto readById(Long id) {
        return ArticleCommentDto.fromEntity(loadArticleCommentById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArticleCommentDto> readAllByArticleId(Long id, Pageable pageable) {
        return articleCommentRepository.findByArticle_Id(id, pageable)
                .map(ArticleCommentDto::fromEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArticleCommentDto> readAllBySiteUserId(Long id, Pageable pageable) {
        return articleCommentRepository.findByWriter_Id(id, pageable)
                .map(ArticleCommentDto::fromEntity);
    }

    @Override
    public ArticleCommentDto createArticleComment(String content, Long article_id) {
        ArticleComment entity = ArticleComment.of(content);
        entity.setArticle(loadArticleById(article_id));
        ArticleComment saved = articleCommentRepository.save(entity);
        return ArticleCommentDto.fromEntity(saved);
    }

    @Override
    public ArticleCommentDto updateArticleComment(Long id, String content) {
        ArticleComment entity = loadArticleCommentById(id);
        if(content != null)
            entity.setContent(content);
        ArticleComment saved = articleCommentRepository.save(entity);
        return ArticleCommentDto.fromEntity(entity);

    }

    @Override
    public void deleteById(Long id) {
        articleCommentRepository.deleteById(id);
    }





}
