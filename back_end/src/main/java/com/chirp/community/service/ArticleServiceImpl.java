package com.chirp.community.service;

import com.chirp.community.entity.Article;
import com.chirp.community.entity.Board;
import com.chirp.community.exception.CommunityException;
import com.chirp.community.model.ArticleDto;
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
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    private Board loadBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(
                        () -> CommunityException.of(
                                HttpStatus.NOT_FOUND,
                                String.format("%s번 게시판은 존재하지 않음.", id)
                        )
                );
    }

    private Article loadArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(
                        () -> CommunityException.of(
                                HttpStatus.NOT_FOUND,
                                String.format("%s번 게시물은 존재하지 않음.", id)
                        )
                );
    }

    private Article addViews(Article entity) {
        entity.setViews(entity.getViews() + 1);
        return articleRepository.save(entity);
    }


    @Override
    public ArticleDto create(String title, String content, Long board_id) {
        Article entity = Article.of(title, content);
        entity.setBoard(loadBoardById(board_id));
        Article saved = articleRepository.save(entity);
        return ArticleDto.fromEntity(saved);
    }

    @Override
    public ArticleDto readById(Long id) {
        Article entity = loadArticleById(id);
        Article saved = addViews(entity);
        return ArticleDto.fromEntity(saved);
    }

    @Override
    public ArticleDto updateById(Long id, String title, String content) {
        Article entity = loadArticleById(id);

        if(title!=null)
            entity.setTitle(title);
        if(content!=null)
            entity.setContent(content);

        Article saved = articleRepository.save(entity);
        return ArticleDto.fromEntity(saved);
    }

    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public Page<ArticleDto> readByBoardId(Long id, Pageable pageable) {
        return articleRepository.findByBoard_Id(id, pageable)
                .map(ArticleDto::fromEntity);
    }

    @Override
    public Page<ArticleDto> readBySiteUserId(Long id, String keyword, Pageable pageable) {
        if(keyword != null && !keyword.isBlank()) {
            return articleRepository.findByWriter_IdWithKeyword(id, keyword, pageable)
                    .map(ArticleDto::fromEntity);
        } else {
            return articleRepository.findByWriter_Id(id, pageable)
                    .map(ArticleDto::fromEntity);
        }
    }
}
