package com.chirp.community.repository;

import com.chirp.community.entity.ArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

    @EntityGraph(attributePaths = {"article"})
    Page<ArticleComment> findByArticle_Id(Long id, Pageable pageable);

    @EntityGraph(attributePaths = {"writer"})
    Page<ArticleComment> findByWriter_Id(Long id, Pageable pageable);

}
