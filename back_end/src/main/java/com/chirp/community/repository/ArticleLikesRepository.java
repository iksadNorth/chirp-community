package com.chirp.community.repository;

import com.chirp.community.entity.ArticleLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleLikesRepository extends JpaRepository<ArticleLikes, Long> {
    Optional<ArticleLikes> findByArticle_IdAndUser_Id(Long articleId, Long userId);

    @Query("SELECT COALESCE(SUM(al.arg), 0) FROM ArticleLikes al WHERE al.article.id = :articleId")
    long sumByArticle_Id(Long articleId);
}
