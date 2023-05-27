package com.chirp.community.repository;

import com.chirp.community.entity.VerificationCode;
import org.springframework.data.repository.CrudRepository;

public interface VerificationCodeRepository extends CrudRepository<VerificationCode, Long> {
}
