package com.chirp.community.entity;

import com.chirp.community.model.ArticleCommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;

@Getter
@Setter
@Table(name = "article_comment", indexes = {
        @Index(columnList = "board_id")
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



    public static ArticleComment of(String content) {
        ArticleComment entity = new ArticleComment();
        entity.setContent(content);
        return entity;
    }





}
