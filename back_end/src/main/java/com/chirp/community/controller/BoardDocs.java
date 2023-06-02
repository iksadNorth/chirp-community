package com.chirp.community.controller;

import com.chirp.community.model.request.BoardCreateRequest;
import com.chirp.community.model.request.BoardUpdateRequest;
import com.chirp.community.model.response.ArticleReadBoardPageRowResponse;
import com.chirp.community.model.response.BoardReadResponse;
import com.chirp.community.model.response.ErrorResponse;
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
import org.springframework.web.bind.annotation.RequestParam;

@Tag(
        name = "Board API",
        description = "게시판 관련 API"
)
interface BoardDocs {
    @Operation(
            summary = "게시판 생성 API",
            description = """
                    게시판 생성 API. Prime Admin만 접근 가능하다.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BoardReadResponse.class)
            )
    )
    BoardReadResponse create(
            @Parameter(
                    description = "게시판 생성 시, 필요한 정보들.",
                    schema = @Schema(implementation = BoardCreateRequest.class)
            )
            @RequestBody BoardCreateRequest request
    );

    @Operation(
            summary = "게시판 일괄 조회 API",
            description = """
                    게시판 일괄 조회 API. 
                    random_mode=true 시, 랜덤한 게시판을 다수 가져온다.
                    keyword={검색어}, 게시판 이름 기준으로 검색한다.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = """
                    정상 작동 - 결과는 content 아래에 담겨서 전달된다. 
                    아래 PageBoardReadResponse 참고. 
                    """,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Page.class)
            )
    )
    Page<BoardReadResponse> readAllByKeyword(
            @Parameter(
                    description = "랜덤하게 게시판을 로드하고 싶을 때, true 전달.",
                    schema = @Schema(implementation = boolean.class)
            )
            @RequestParam(defaultValue = "false") boolean random_mode,
            @Parameter(
                    description = "게시판을 이름 기준으로 검색할 때, 검색어 전달.",
                    schema = @Schema(implementation = String.class)
            )
            @RequestParam(defaultValue = "") String keyword,
            @ParameterOfPageableForSwagger
            @PageableDefault(size = 10) Pageable pageable
    );

    @Operation(
            summary = "게시판 단일 조회 API",
            description = """
                    게시판 ID 이용해 게시판 단일 조회 API.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "정상 작동",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BoardReadResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "~~번 게시판은 존재하지 않음.\"",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    BoardReadResponse readById(@PathVariable Long id);

    @Operation(
            summary = "게시판 소속 게시물 일괄 조회 API",
            description = """
                    게시판 ID 이용한 게시물 일괄 조회 API.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = """
                    정상 작동 - 결과는 content 아래에 담겨서 전달된다. 
                    아래 PageArticleReadBoardPageRowResponse 참고. 
                    """,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Page.class)
            )
    )
    Page<ArticleReadBoardPageRowResponse> readArticlesById(
            @PathVariable Long id,
            @ParameterOfPageableForSwagger
            @PageableDefault Pageable pageable
    );

    @Operation(
            summary = "게시판 수정 API",
            description = """
                    게시판 수정 API. Prime Admin만 접근 가능하다.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = """
                    정상 작동
                    """,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BoardReadResponse.class)
            )
    )
    BoardReadResponse updateById(
            @PathVariable Long id,
            @Parameter(
                    description = "게시판 수정 정보.",
                    schema = @Schema(implementation = BoardUpdateRequest.class)
            )
            @RequestBody BoardUpdateRequest request
    );

    @Operation(
            summary = "게시판 삭제 API",
            description = """
                    게시판 삭제 API. Prime Admin만 접근 가능하다.
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
}
