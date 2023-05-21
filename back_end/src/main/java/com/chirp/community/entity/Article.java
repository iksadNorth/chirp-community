package com.chirp.community.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity @Table(name = "article", indexes = {
        @Index(columnList = "board_id")
})
@NoArgsConstructor @Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class Article extends BaseEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private SiteUser writer;

    @Column(name = "views", nullable = false) @ColumnDefault("0")
    private Long views;

    public static Article of(Long id, String title, String content) {
        Article entity = new Article();
        entity.setId(id);
        entity.setTitle(title);
        entity.setContent(content);
        entity.setViews(0L);
        return entity;
    }
    public static Article of(String title, String content) {
        return of(null, title, content);
    }
}