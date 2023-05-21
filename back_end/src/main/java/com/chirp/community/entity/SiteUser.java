package com.chirp.community.entity;

import com.chirp.community.type.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity @Table(name = "site_user", indexes = {
        @Index(columnList = "email")
})
@NoArgsConstructor @Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class SiteUser extends BaseEntity {
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "role") @ColumnDefault("'ROLE_USER'")
    @Convert(converter = RoleType.ConverterImpl.class)
    private RoleType role;

    @OneToMany(mappedBy = "writer", cascade = {CascadeType.REMOVE})
    private List<Article> articleList;

    public static SiteUser of(Long id, String email, String password, String nickname) {
        SiteUser entity = new SiteUser();
        entity.setId(id);
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setNickname(nickname);
        entity.setRole(RoleType.USER);
        return entity;
    }

    public static SiteUser of(String email, String password, String nickname) {
        return of(null, email, password, nickname);
    }
}
