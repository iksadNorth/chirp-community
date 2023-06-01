package com.chirp.community.repository;

import com.chirp.community.entity.Article;
import com.chirp.community.entity.projection.ArticleMapperWithNumLikes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @EntityGraph(attributePaths = {"writer"})
    Page<Article> findByBoard_Id(Long id, Pageable pageable);

    @EntityGraph(attributePaths = {"board"})
    Page<Article> findByWriter_Id(Long id, Pageable pageable);

    @EntityGraph(attributePaths = {"board"})
    @Query(
            value = "SELECT a FROM Article a WHERE a.writer.id = :id AND (a.title LIKE CONCAT('%', :keyword, '%') OR a.content LIKE CONCAT('%', :keyword, '%'))",
            countQuery = "SELECT COUNT(a) FROM Article a WHERE a.writer.id = :id AND (a.title LIKE CONCAT('%', :keyword, '%') OR a.content LIKE CONCAT('%', :keyword, '%'))"
    )
    Page<Article> findByWriter_IdWithKeyword(Long id, String keyword, Pageable pageable);

    @EntityGraph(attributePaths = {"writer", "board"})
    @Query("SELECT a FROM Article a WHERE a.id = :id")
    Optional<Article> findWithBoardAndWriterById(Long id);

    @EntityGraph(attributePaths = {"board"})
    @Query("SELECT a FROM Article a " +
            "WHERE :weekAgo <= a.createdAt " +
            "ORDER BY a.views DESC")
    Page<Article> readBestByViews(LocalDateTime weekAgo, Pageable pageable);
  
    @EntityGraph(attributePaths = {"board"})
    @Query(
            "SELECT al.article AS article, CAST(SUM(al.arg) AS LONG) AS numLikes " +
            "FROM ArticleLikes al " +
            "WHERE article.createdAt >= :weekAgo " +
            "GROUP BY al.article.id " +
            "ORDER BY numLikes DESC"
    )
    Page<ArticleMapperWithNumLikes> readBestByLikes(LocalDateTime weekAgo, Pageable pageable);
}
