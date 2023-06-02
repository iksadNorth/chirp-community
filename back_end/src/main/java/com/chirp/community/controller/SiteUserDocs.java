package com.chirp.community.controller;

import com.chirp.community.model.SiteUserDto;
import com.chirp.community.model.request.SiteUserCreateRequest;
import com.chirp.community.model.request.SiteUserUpdateRequest;
import com.chirp.community.model.response.ArticleReadMyPageRowResponse;
import com.chirp.community.model.response.ErrorResponse;
import com.chirp.community.model.response.SiteUserReadResponse;
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
        name = "SiteUser API",
        description = "유저 관련 API"
)
interface SiteUserDocs {
    @Operation(
            summary = "유저 생성 API",
            description = """
                    유저 생성 API. 즉 회원 가입 API.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SiteUserReadResponse.class)
            )
    )
    SiteUserReadResponse create(
            @Parameter(
                    description = "유저 생성 시, 필요한 정보들.",
                    schema = @Schema(implementation = SiteUserCreateRequest.class)
            )
            @RequestBody SiteUserCreateRequest request
    );

    @Operation(
            summary = "유저 단일 조회 API",
            description = """
                    유저 단일 조회 API. 
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SiteUserReadResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "~~번 유저는 존재하지 않음.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    SiteUserReadResponse readById(@PathVariable Long id);

    @Operation(
            summary = "유저 단일 조회 API",
            description = """
                    JWT를 이용한 유저 단일 조회 API.  
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SiteUserReadResponse.class)
            )
    )
    SiteUserReadResponse readByAuthToken(@AuthenticationPrincipal SiteUserDto principal);

    @Operation(
            summary = "유저 작성글 일괄 조회 API",
            description = """
                    유저 작성글 일괄 조회 API. 게시판에 상관없이 유저가 작성한 게시글 조회.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = """
                    정상 작동 
                    아래 PageArticleReadMyPageRowResponse 참고.
                    """,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Page.class)
            )
    )
    Page<ArticleReadMyPageRowResponse> readArticleById(
            @PathVariable Long id,
            @Parameter(
                    description = "유저가 작성한 글 중 제목이나 글 내용이 검색어를 포함할 경우에 출력.",
                    schema = @Schema(implementation = String.class)
            )
            @RequestParam(defaultValue = "") String keyword,
            @ParameterOfPageableForSwagger
            @PageableDefault Pageable pageable
    );

    @Operation(
            summary = "유저 작성글 일괄 조회 API",
            description = """
                    JWT를 이용한 유저 작성글 일괄 조회 API. 게시판에 상관없이 유저가 작성한 게시글 조회.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = """
                    정상 작동 
                    아래 PageArticleReadMyPageRowResponse 참고.
                    """,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Page.class)
            )
    )
    Page<ArticleReadMyPageRowResponse> readArticleByAuthToken(
            @AuthenticationPrincipal SiteUserDto principal,
            @Parameter(
                    description = "유저가 작성한 글 중 제목이나 글 내용이 검색어를 포함할 경우에 출력.",
                    schema = @Schema(implementation = String.class)
            )
            @RequestParam(defaultValue = "") String keyword,
            @ParameterOfPageableForSwagger
            @PageableDefault Pageable pageable
    );

    @Operation(
            summary = "유저 정보 수정 API",
            description = """
                    유저 정보 수정 API. 당사자 혹은 Prime Admin 권한을 가진 유저만 접근 가능.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = """
                    정상 작동
                    """,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SiteUserReadResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "403",
            description = """
                    당사자 혹은 Prime Admin 권한을 가진 유저가 아닌 경우.
                    """,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    SiteUserReadResponse updateById(
            @PathVariable Long id,
            @Parameter(
                    description = "유저 정보 변경을 위한 정보들.",
                    schema = @Schema(implementation = SiteUserUpdateRequest.class)
            )
            @RequestBody SiteUserUpdateRequest request
    );

    @Operation(
            summary = "유저 정보 수정 API",
            description = """
                    JWT를 이용한 유저 정보 수정 API. 당사자 혹은 Prime Admin 권한을 가진 유저만 접근 가능.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = """
                    정상 작동
                    """,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SiteUserReadResponse.class)
            )
    )
    SiteUserReadResponse updateByAuthToken(
            @AuthenticationPrincipal SiteUserDto principal,
            @Parameter(
                    description = "유저 정보 변경을 위한 정보들.",
                    schema = @Schema(implementation = SiteUserUpdateRequest.class)
            )
            @RequestBody SiteUserUpdateRequest request
    );

    @Operation(
            summary = "유저 정보 삭제 API",
            description = """
                    유저 정보 삭제 API. 당사자 혹은 Prime Admin 권한을 가진 유저만 접근 가능.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = """
                    정상 작동
                    """,
            content = @Content(
                    mediaType = MediaType.TEXT_PLAIN_VALUE
            )
    )
    void deleteById(@PathVariable Long id);

    @Operation(
            summary = "유저 USER 권한으로 변경 API",
            description = """
                    유저 USER 권한으로 변경 API. Prime Admin 권한을 가진 유저만 접근 가능.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = """
                    정상 작동
                    """,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SiteUserReadResponse.class)
            )
    )
    SiteUserReadResponse updateRoleToUser(@PathVariable Long id);

    @Operation(
                summary = "유저 BOARD_ADMIN 권한으로 변경 API",
            description = """
                    유저 BOARD_ADMIN 권한으로 변경 API. Prime Admin 권한을 가진 유저만 접근 가능.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = """
                    정상 작동
                    """,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SiteUserReadResponse.class)
            )
    )
    SiteUserReadResponse updateRoleToBoardAdmin(@PathVariable Long id);

    @Operation(
            summary = "유저 USER_VERIFIED_WITH_EMAIL 권한으로 변경 API",
            description = """
                    유저 USER_VERIFIED_WITH_EMAIL 권한으로 변경 API. 
                    Prime Admin 권한을 가진 유저만 접근 가능. 
                    이메일 인증을 위해 사용 됨.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = """
                    정상 작동
                    """,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SiteUserReadResponse.class)
            )
    )
    SiteUserReadResponse updateRoleToUserVerifiedByEmail(
            @PathVariable Long id,
            @Parameter(
                    description = "이메일 보안 코드",
                    schema = @Schema(implementation = String.class)
            )
            @RequestParam("code") String code
    );
}
