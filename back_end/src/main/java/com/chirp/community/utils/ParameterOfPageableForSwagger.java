package com.chirp.community.utils;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Pageable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(
        description = """
                            page={0부터 시작하는 페이지 번호 수}, 
                            size={한 페이지당 갯수}, 
                            sort={정렬시킬 칼럼명},{asc|desc}
                            """,
        schema = @Schema(implementation = Pageable.class)
)
public @interface ParameterOfPageableForSwagger {
}
