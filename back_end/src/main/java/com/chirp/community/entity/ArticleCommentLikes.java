package com.chirp.community.entity;

import com.chirp.community.type.LikeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "article_comment_likes", indexes = {
        @Index(columnList = "article_comment_id"),
        @Index(columnList = "user_id")
})
@NoArgsConstructor @Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class ArticleCommentLikes extends BaseEntity {
    @Column(name = "arg")
    @Convert(converter = LikeType.ConverterImpl.class)
    private LikeType arg;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private SiteUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_comment_id", nullable = false)
    private ArticleComment articleComment;
}
