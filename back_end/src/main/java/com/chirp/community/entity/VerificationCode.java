package com.chirp.community.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class VerificationCode {
    @Id
    private Long userid;
    private String code;

    public static VerificationCode of(Long userid, String code) {
        VerificationCode entity = new VerificationCode();
        entity.setUserid(userid);
        entity.setCode(code);
        return entity;
    }
}
