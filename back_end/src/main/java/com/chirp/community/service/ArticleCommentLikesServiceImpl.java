package com.chirp.community.service;

import com.chirp.community.configuration.JpaAuditingConfig;
import com.chirp.community.entity.*;
import com.chirp.community.model.LikesDto;
import com.chirp.community.model.SiteUserDto;
import com.chirp.community.repository.ArticleCommentLikesRepository;
import com.chirp.community.type.LikeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommentLikesServiceImpl implements ArticleCommentLikesService {
    private final ArticleCommentLikesRepository articleCommentLikesRepository;

    private ArticleCommentLikes getHollowShell(Long commentId, SiteUser user) {
        ArticleComment commentEntity = new ArticleComment();
        commentEntity.setId(commentId);

        ArticleCommentLikes entity = new ArticleCommentLikes();
        entity.setArticleComment(commentEntity);
        entity.setUser(user);
        entity.setArg(LikeType.NULL);
        return entity;
    }

    private ArticleCommentLikes loadByArticle_IdAndUser_Id(Long commentId, SiteUser user) {
        return articleCommentLikesRepository.findByArticleComment_IdAndUser_Id(commentId, user.getId())
                .orElseGet(() -> getHollowShell(commentId, user));
    }

    private LikesDto printLikes(Long articleId, Function<ArticleCommentLikes, ArticleCommentLikes> function) {
        LikeType likeType = JpaAuditingConfig.principal()
                .map(SiteUserDto::toEntity)

                .map(siteUser -> loadByArticle_IdAndUser_Id(articleId, siteUser))
                .map(function::apply)

                .map(ArticleCommentLikes::getArg)
                .orElse(LikeType.NULL);

        long totalLikes = articleCommentLikesRepository.sumByArticle_Id(articleId);
        return LikesDto.builder()
                .likeType(likeType)
                .numLikes(totalLikes)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public LikesDto readLikes(Long commentId) {
        return printLikes(commentId, Function.identity());
    }

    @Override
    public LikesDto toggleLikes(Long commentId) {
        return printLikes(commentId, entity -> {
            if(entity.getArg().equals(LikeType.LIKE)) {
                entity.setArg(LikeType.NULL);
            } else {
                entity.setArg(LikeType.LIKE);
            }
            return articleCommentLikesRepository.save(entity);
        });
    }

    @Override
    public LikesDto toggleDisLikes(Long commentId) {
        return printLikes(commentId, entity -> {
            if(entity.getArg().equals(LikeType.DISLIKE)) {
                entity.setArg(LikeType.NULL);
            } else {
                entity.setArg(LikeType.DISLIKE);
            }
            return articleCommentLikesRepository.save(entity);
        });
    }
}
