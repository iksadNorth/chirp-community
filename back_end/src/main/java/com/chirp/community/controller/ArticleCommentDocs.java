package com.chirp.community.controller;

import com.chirp.community.model.SiteUserDto;
import com.chirp.community.model.request.ArticleCommentCreateRequest;
import com.chirp.community.model.request.ArticleCommentUpdateRequest;
import com.chirp.community.model.response.ArticleCommentReadRecentlyResponse;
import com.chirp.community.model.response.ArticleCommentReadResponse;
import com.chirp.community.model.response.ErrorResponse;
import com.chirp.community.model.response.LikesReadResponse;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(
        name = "ArticleComment API",
        description = "댓글 관련 API"
)
public interface ArticleCommentDocs {
    @Operation(
            summary = "댓글 단일 조회 API",
            description = """
                    댓글 단일 조회 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArticleCommentReadResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "'~~'번 댓글은 존재하지 않음.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    ArticleCommentReadResponse readByArticleCommentId(@PathVariable Long id);

    @Operation(
            summary = "댓글 단일 조회 API",
            description = """
                    댓글 단일 조회 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동 아래 PageArticleCommentReadRecentlyResponse 참고",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Page.class)
            )
    )
    Page<ArticleCommentReadRecentlyResponse> readAll(
            @ParameterOfPageableForSwagger
            @PageableDefault(size = 10) Pageable pageable
    );

    @Operation(
            summary = "특정 게시글의 댓글 일괄 조회 API",
            description = """
                    특정 게시글의 댓글 일괄 조회 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동 아래 PageArticleCommentReadResponse 참고",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Page.class)
            )
    )
    Page<ArticleCommentReadResponse> readAllByBArticleId(
            @PathVariable Long id,
            @ParameterOfPageableForSwagger
            @PageableDefault(size = 10) Pageable pageable
    );

    @Operation(
            summary = "특정 유저의 댓글 일괄 조회 API",
            description = """
                    특정 유저의 댓글 일괄 조회 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동 아래 PageArticleCommentReadResponse 참고",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Page.class)
            )
    )
    Page<ArticleCommentReadResponse> readAllBySiteUserId(
            @PathVariable Long id,
            @Parameter(
                    description = "댓글 조회 시, 내용을 기준으로 검색할 검색어.",
                    schema = @Schema(implementation = String.class)
            )
            @RequestParam(defaultValue = "") String keyword,
            @ParameterOfPageableForSwagger
            @PageableDefault(size = 10) Pageable pageable);


    @Operation(
            summary = "로그인 유저의 댓글 일괄 조회 API",
            description = """
                    현재 로그인한 유저의 댓글 일괄 조회 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동 아래 PageArticleCommentReadResponse 참고",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Page.class)
            )
    )
    Page<ArticleCommentReadResponse> readAllByAuth(
            @AuthenticationPrincipal SiteUserDto principal,
            @Parameter(
                    description = "댓글 조회 시, 내용을 기준으로 검색할 검색어.",
                    schema = @Schema(implementation = String.class)
            )
            @RequestParam(defaultValue = "") String keyword,
            @ParameterOfPageableForSwagger
            @PageableDefault(size = 10) Pageable pageable);

    @Operation(
            summary = "댓글 생성 API",
            description = """
                    댓글 생성 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArticleCommentReadResponse.class)
            )
    )
    ArticleCommentReadResponse createArticleComment(
            @Parameter(
                    description = "댓글 생성 시, 필요한 정보들.",
                    schema = @Schema(implementation = ArticleCommentCreateRequest.class)
            )
            @RequestBody ArticleCommentCreateRequest request
    );

    @Operation(
            summary = "댓글 수정 API",
            description = """
                    댓글 수정 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ArticleCommentReadResponse.class)
            )
    )
    ArticleCommentReadResponse updateArticleComment(
            @PathVariable Long id,
            @Parameter(
                    description = "댓글 수정 시, 필요한 정보들.",
                    schema = @Schema(implementation = ArticleCommentUpdateRequest.class)
            )
            @RequestBody ArticleCommentUpdateRequest request
    );

    @Operation(
            summary = "댓글 삭제 API",
            description = """
                    댓글 삭제 API
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.TEXT_PLAIN_VALUE
            )
    )
    void deleteArticleComment(@PathVariable Long id);

    @Operation(
            summary = "댓글 추천수 조회 API",
            description = """
                    댓글 추천수 조회 API
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
            summary = "댓글 추천 API",
            description = """
                    댓글 추천 API
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
            summary = "댓글 비추천 API",
            description = """
                    댓글 비추천 API
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
}
