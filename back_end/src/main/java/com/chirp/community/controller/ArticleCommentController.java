package com.chirp.community.controller;


import com.chirp.community.model.ArticleCommentDto;
import com.chirp.community.model.LikesDto;
import com.chirp.community.model.SiteUserDto;
import com.chirp.community.model.request.ArticleCommentCreateRequest;
import com.chirp.community.model.request.ArticleCommentUpdateRequest;
import com.chirp.community.model.response.ArticleCommentReadRecentlyResponse;
import com.chirp.community.model.response.ArticleCommentReadResponse;
import com.chirp.community.model.response.LikesReadResponse;
import com.chirp.community.service.ArticleCommentLikesService;
import com.chirp.community.service.ArticleCommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/article_comment")
@RestController
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;
    private final ArticleCommentLikesService articleCommentLikesService;

    @GetMapping("/{id}")
    public ArticleCommentReadResponse readByArticleCommentId(@PathVariable Long id) {
        ArticleCommentDto dto = articleCommentService.readById(id);
        return ArticleCommentReadResponse.of(dto);
    }

    @GetMapping
    public Page<ArticleCommentReadRecentlyResponse> readAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<ArticleCommentDto> page = articleCommentService.readAll(pageable);
        return page.map(ArticleCommentReadRecentlyResponse::of);
    }

    @GetMapping("/article/{id}")
    public Page<ArticleCommentReadResponse> readAllByBArticleId(@PathVariable Long id, @PageableDefault(size = 10) Pageable pageable) {
        Page<ArticleCommentDto> dto = articleCommentService.readAllByArticleId(id, pageable);
        return dto.map(ArticleCommentReadResponse::of);
    }

    @GetMapping("/user/{id}")
    public Page<ArticleCommentReadResponse> readAllBySiteUserId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<ArticleCommentDto> dto = articleCommentService.readAllBySiteUserId(id, keyword, pageable);
        return dto.map(ArticleCommentReadResponse::of);
    }

    @GetMapping("/user/me")
    public Page<ArticleCommentReadResponse> readAllByAuth(
            @AuthenticationPrincipal SiteUserDto principal,
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<ArticleCommentDto> dto = articleCommentService.readAllBySiteUserId(principal.id(), keyword, pageable);
        return dto.map(ArticleCommentReadResponse::of);
    }

    @PostMapping
    public ArticleCommentReadResponse createArticleComment(@RequestBody ArticleCommentCreateRequest request) {
        ArticleCommentDto dto = articleCommentService.createArticleComment(request.content(), request.article_id());
        return ArticleCommentReadResponse.of(dto);
    }


    @PatchMapping("/{id}")
    public ArticleCommentReadResponse updateArticleComment(@PathVariable Long id, @RequestBody ArticleCommentUpdateRequest request) {
        ArticleCommentDto dto = articleCommentService.updateArticleComment(id, request.content());
        return ArticleCommentReadResponse.of(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteArticleComment(@PathVariable Long id) {
        articleCommentService.deleteById(id);
    }

    @GetMapping("/{id}/sum-likes")
    public LikesReadResponse readLikes(@PathVariable Long id) {
        LikesDto dto = articleCommentLikesService.readLikes(id);
        return LikesReadResponse.of(dto);
    }

    @PostMapping("/{id}/like")
    public LikesReadResponse toggleLikes(@PathVariable Long id) {
        LikesDto dto = articleCommentLikesService.toggleLikes(id);
        return LikesReadResponse.of(dto);
    }

    @PostMapping("/{id}/dislike")
    public LikesReadResponse toggleDisLikes(@PathVariable Long id) {
        LikesDto dto = articleCommentLikesService.toggleDisLikes(id);
        return LikesReadResponse.of(dto);
    }

}
