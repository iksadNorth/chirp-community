package com.chirp.community.repository;

import com.chirp.community.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
}
