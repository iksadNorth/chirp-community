package com.chirp.community.repository;

import com.chirp.community.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByBoard_Id(Long id, Pageable pageable);

    @EntityGraph(attributePaths = {"writer", "board"})
    Page<Article> findByWriter_Id(Long id, Pageable pageable);

    @EntityGraph(attributePaths = {"writer", "board"})
    @Query(
            value = "SELECT a FROM Article a WHERE a.writer.id = :id AND (a.title LIKE CONCAT('%', :keyword, '%') OR a.content LIKE CONCAT('%', :keyword, '%'))",
            countQuery = "SELECT COUNT(a) FROM Article a WHERE a.writer.id = :id AND (a.title LIKE CONCAT('%', :keyword, '%') OR a.content LIKE CONCAT('%', :keyword, '%'))"
    )
    Page<Article> findByWriter_IdWithKeyword(Long id, String keyword, Pageable pageable);
}
