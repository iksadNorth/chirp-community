package com.chirp.community.repository;

import com.chirp.community.entity.ArticleCommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleCommentLikesRepository extends JpaRepository<ArticleCommentLikes, Long> {
    Optional<ArticleCommentLikes> findByArticleComment_IdAndUser_Id(Long articleCommentId, Long userId);

    @Query("SELECT COALESCE(SUM(cl.arg), 0) FROM ArticleCommentLikes cl WHERE cl.articleComment.id = :id")
    long sumByArticle_Id(Long id);
}
