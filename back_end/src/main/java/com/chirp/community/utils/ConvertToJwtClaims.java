package com.chirp.community.utils;

import io.jsonwebtoken.Claims;

public interface ConvertToJwtClaims<T> {
    Claims convertToClaims(T entity);
}
