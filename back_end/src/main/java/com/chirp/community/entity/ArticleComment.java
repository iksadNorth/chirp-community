package com.chirp.community.entity;

import com.chirp.community.model.ArticleCommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "article_comment", indexes = {
        @Index(columnList = "article_id")
})
@Entity
public class ArticleComment extends BaseEntity{

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private SiteUser writer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "articleComment", cascade = {CascadeType.REMOVE})
    private List<ArticleCommentLikes> likes;

    public static ArticleComment of(String content) {
        ArticleComment entity = new ArticleComment();
        entity.setContent(content);
        return entity;
    }





}
