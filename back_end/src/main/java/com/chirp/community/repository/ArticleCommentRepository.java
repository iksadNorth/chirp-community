package com.chirp.community.repository;

import com.chirp.community.entity.ArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

    @EntityGraph(attributePaths = {"article"})
    @Query(countQuery = "SELECT COUNT(c) FROM ArticleComment c WHERE c.article.id = :id")
    Page<ArticleComment> findByArticle_Id(Long id, Pageable pageable);

    @EntityGraph(attributePaths = {"writer"})
    @Query(countQuery = "SELECT COUNT(c) FROM ArticleComment c WHERE c.writer.id = :id")
    Page<ArticleComment> findByWriter_Id(Long id, Pageable pageable);

    @EntityGraph(attributePaths = {"writer"})
    @Query(
            value = "SELECT c FROM ArticleComment c WHERE c.writer.id = :id AND c.content LIKE CONCAT('%', :keyword, '%')",
            countQuery = "SELECT COUNT(c) FROM ArticleComment c WHERE c.writer.id = :id AND c.content LIKE CONCAT('%', :keyword, '%')"
    )
    Page<ArticleComment> findByWriter_IdWithKeyword(Long id, String keyword, Pageable pageable);
}
