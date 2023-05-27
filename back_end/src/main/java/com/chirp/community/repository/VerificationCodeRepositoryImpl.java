package com.chirp.community.repository;

import com.chirp.community.entity.VerificationCode;
import com.chirp.community.properties.VerificationCodeProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class VerificationCodeRepositoryImpl implements VerificationCodeRepository {
    private final RedisTemplate<String, String> redisTemplate;
    private final VerificationCodeProperties verificationCodeProperties;

    private static final String PREFIX = "VERIFICATION_CODE";
    private static final TimeUnit TIME_UNIT = TimeUnit.MICROSECONDS;

    private String wrapKey(Long key) {
        return String.format("%s:%d", PREFIX, key);
    }

    private String wrapKey() {
        return String.format("%s:*", PREFIX);
    }

    @Override
    public <S extends VerificationCode> S save(S entity) {
        Long expiredTimeMs = verificationCodeProperties.getExpiredTimeMs();
        String key = wrapKey(entity.getUserid());

        redisTemplate.opsForValue().set(
                key, entity.getCode(),
                expiredTimeMs, TIME_UNIT
        );
        return entity;
    }

    @Override
    public <S extends VerificationCode> Iterable<S> saveAll(Iterable<S> entities) {
        Long expiredTimeMs = verificationCodeProperties.getExpiredTimeMs();

        entities.forEach(entity -> {
            redisTemplate.opsForValue().set(
                    wrapKey(entity.getUserid()), entity.getCode(),
                    expiredTimeMs, TIME_UNIT
            );
        });
        return entities;
    }

    @Override
    public Optional<VerificationCode> findById(Long id) {
        String key = wrapKey(id);
        String code = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(VerificationCode.of(id, code));
    }

    @Override
    public boolean existsById(Long id) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(wrapKey(id)));
    }

    @Override
    public Iterable<VerificationCode> findAll() {
        // 구현을 위해 일일히 모든 key값에 대해 쿼리를 보내야 함.
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<VerificationCode> findAllById(Iterable<Long> longs) {
        HashSet<VerificationCode> entities = new HashSet<>();

        longs.forEach(id -> {
            String key = wrapKey(id);
            String code = redisTemplate.opsForValue().get(key);
            entities.add(VerificationCode.of(id, code));
        });
        return entities;
    }

    @Override
    public long count() {
        return redisTemplate.keys(wrapKey()).size();
    }

    @Override
    public void deleteById(Long id) {
        String key = wrapKey(id);
        redisTemplate.delete(key);
    }

    @Override
    public void delete(VerificationCode entity) {
        String key = wrapKey(entity.getUserid());
        redisTemplate.delete(key);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        List<String> idList = StreamSupport.stream(ids.spliterator(), false)
                .map(this::wrapKey).toList();
        redisTemplate.delete(idList);
    }

    @Override
    public void deleteAll(Iterable<? extends VerificationCode> entities) {
        List<String> idList = StreamSupport.stream(entities.spliterator(), false)
                .map(VerificationCode::getUserid)
                .map(this::wrapKey).toList();
        redisTemplate.delete(idList);
    }

    @Override
    public void deleteAll() {
        Set<String> keys = redisTemplate.keys(wrapKey());
        redisTemplate.delete(keys);
    }
}
