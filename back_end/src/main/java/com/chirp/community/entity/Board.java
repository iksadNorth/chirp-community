package com.chirp.community.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "board")
@NoArgsConstructor @Getter @Setter
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