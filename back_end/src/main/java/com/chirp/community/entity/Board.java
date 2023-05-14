package com.chirp.community.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Collection;

@Entity @Table(name = "board", indexes = {
        @Index(columnList = "name")
})
@NoArgsConstructor @Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class Board extends BaseEntity {
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public static Board of(Long id, String name) {
        Board entity = new Board();
        entity.setId(id);
        entity.setName(name);
        return entity;
    }
    public static Board of(String name) {
        return of(null, name);
    }
}