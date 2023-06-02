package com.chirp.community.controller;

import com.chirp.community.model.request.ArticleCreateRequest;
import com.chirp.community.model.request.ArticleUpdateRequest;
import com.chirp.community.model.response.*;
import com.chirp.community.utils.ParameterOfPageableForSwagger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Article API",
        description = "게시글 관련 API"
)
public interface ArticleDocs {
    @Operation(
            summary = "게시글 생성 API",
            description = """
                    게시글 생성 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArticleReadResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "'~~'번 게시판은 존재하지 않음.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    ArticleReadResponse create(
            @Parameter(
                    description = "게시글 생성 시, 필요한 정보들",
                    schema = @Schema(implementation = ArticleCreateRequest.class)
            )
            @RequestBody ArticleCreateRequest request
    );

    @Operation(
            summary = "게시글 단일 조회 API",
            description = """
                    게시글 단일 조회 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArticleReadResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "'~~'번 게시물은 존재하지 않음.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    ArticleReadResponse readById(@PathVariable Long id);

    @Operation(
            summary = "게시글 일괄 조회 API",
            description = """
                    게시글 일괄 조회 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동 아래 PageArticleReadRecentlyResponse 참고",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Page.class)
            )
    )
    Page<ArticleReadRecentlyResponse> readAll(
            @ParameterOfPageableForSwagger
            @PageableDefault Pageable pageable
    );

    @Operation(
            summary = "게시글 수정 API",
            description = """
                    게시글 수정 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArticleReadResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "'~~'번 게시물은 존재하지 않음.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    ArticleReadResponse updateById(
            @PathVariable Long id,
            @Parameter(
                    description = "게시글 수정 시, 필요한 정보들",
                    schema = @Schema(implementation = ArticleUpdateRequest.class)
            )
            @RequestBody ArticleUpdateRequest request
    );

    @Operation(
            summary = "게시글 삭제 API",
            description = """
                    게시글 삭제 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
    )
    void deleteById(@PathVariable Long id);

    @Operation(
            summary = "게시글 추천수 조회 API",
            description = """
                    게시글 추천수 조회 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = LikesReadResponse.class)
            )
    )
    LikesReadResponse readLikes(@PathVariable Long id);

    @Operation(
            summary = "게시글 추천 API",
            description = """
                    게시글 추천 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = LikesReadResponse.class)
            )
    )
    LikesReadResponse toggleLikes(@PathVariable Long id);

    @Operation(
            summary = "게시글 비추천 API",
            description = """
                    게시글 비추천 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = LikesReadResponse.class)
            )
    )
    LikesReadResponse toggleDisLikes(@PathVariable Long id);

    @Operation(
            summary = "조회수 기준 베스트 게시물 API",
            description = """
                    조회수 기준 베스트 게시물 API. 1주일을 기준으로 조회수가 많았던 게시물 출력.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동 아래 PageArticleReadMyPageRowResponse 참고",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Page.class)
            )
    )
    Page<ArticleReadMyPageRowResponse> readBestByViews(@PageableDefault Pageable pageable);

    @Operation(
            summary = "추천수 기준 베스트 게시물 API",
            description = """
                    추천수 기준 베스트 게시물 API. 1주일을 기준으로 추천수가 많았던 게시물 출력.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동 아래 PageArticleReadBestLikesResponse 참고",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Page.class)
            )
    )
    Page<ArticleReadBestLikesResponse> readBestByLikes(@PageableDefault Pageable pageable);
}
