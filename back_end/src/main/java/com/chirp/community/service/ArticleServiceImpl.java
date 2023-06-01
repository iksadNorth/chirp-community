package com.chirp.community.service;

import com.chirp.community.entity.Article;
import com.chirp.community.entity.Board;
import com.chirp.community.exception.CommunityException;
import com.chirp.community.model.ArticleDto;
import com.chirp.community.model.BoardDto;
import com.chirp.community.model.SiteUserDto;
import com.chirp.community.repository.ArticleRepository;
import com.chirp.community.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    private Article loadArticleWithBoardWithWriterById(Long id) {
        return articleRepository.findWithBoardAndWriterById(id)
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
        Article entity = loadArticleWithBoardWithWriterById(id);
        Article saved = addViews(entity);
        return ArticleDto.fromEntity(entity)
                .toBuilder()
                .views(saved.getViews())
                .board(BoardDto.fromEntity(entity.getBoard()))
                .writer(SiteUserDto.fromEntity(entity.getWriter()))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArticleDto> readAll(Pageable pageable) {
        return articleRepository.findAll(pageable)
                .map(ArticleDto::fromEntity);
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
                .map(entity -> ArticleDto.fromEntity(entity)
                        .toBuilder()
                        .writer(SiteUserDto.fromEntity(entity.getWriter()))
                        .build());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArticleDto> readBySiteUserId(Long id, String keyword, Pageable pageable) {
        Page<Article> pages = keyword != null && !keyword.isBlank() ?
                articleRepository.findByWriter_IdWithKeyword(id, keyword, pageable) :
                articleRepository.findByWriter_Id(id, pageable);

        return pages.map(entity -> ArticleDto.fromEntity(entity)
                .toBuilder()
                .board(BoardDto.fromEntity(entity.getBoard()))
                .build());
    }

    @Override
    public Page<ArticleDto> readBestByViews(Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekAgo = now.minus(1, ChronoUnit.WEEKS);

        return articleRepository.readBestByViews(weekAgo, pageable)
                .map(entity -> ArticleDto.fromEntity(entity)
                .toBuilder()
                .board(BoardDto.fromEntity(entity.getBoard()))
                .build());
    }
    
    @Override
    public Page<ArticleDto> readBestByLikes(Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekAgo = now.minus(1, ChronoUnit.WEEKS);

        return articleRepository.readBestByLikes(weekAgo, pageable)
                .map(mapper -> ArticleDto.fromEntity(mapper.getArticle())
                .toBuilder()
                .board(BoardDto.fromEntity(mapper.getArticle().getBoard()))
                .numLikes(mapper.getNumLikes())
                .build());
    }
}
