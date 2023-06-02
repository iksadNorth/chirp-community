package com.chirp.community.controller;

import com.chirp.community.model.ArticleDto;
import com.chirp.community.model.LikesDto;
import com.chirp.community.model.request.ArticleCreateRequest;
import com.chirp.community.model.response.ArticleReadRecentlyResponse;
import com.chirp.community.model.response.ArticleReadMyPageRowResponse;
import com.chirp.community.model.request.ArticleUpdateRequest;
import com.chirp.community.model.response.ArticleReadBestLikesResponse;
import com.chirp.community.model.response.ArticleReadResponse;
import com.chirp.community.model.response.LikesReadResponse;
import com.chirp.community.service.ArticleLikesService;
import com.chirp.community.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController implements ArticleDocs {
    private final ArticleService siteUserService;
    private final ArticleLikesService articleLikesService;

    @PostMapping
    public ArticleReadResponse create(@RequestBody ArticleCreateRequest request) {
        ArticleDto dto = siteUserService.create(request.title(), request.content(), request.board_id());
        return ArticleReadResponse.of(dto);
    }

    @GetMapping("/{id}")
    public ArticleReadResponse readById(@PathVariable Long id) {
        ArticleDto dto = siteUserService.readById(id);
        return ArticleReadResponse.of(dto);
    }

    @GetMapping
    public Page<ArticleReadRecentlyResponse> readAll(@PageableDefault Pageable pageable) {
        Page<ArticleDto> page = siteUserService.readAll(pageable);
        return page.map(ArticleReadRecentlyResponse::of);
    }

    @PatchMapping("/{id}")
    public ArticleReadResponse updateById(@PathVariable Long id, @RequestBody ArticleUpdateRequest request) {
        ArticleDto dto = siteUserService.updateById(id, request.title(), request.content());
        return ArticleReadResponse.of(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        siteUserService.deleteById(id);
    }

    @GetMapping("/{id}/sum-likes")
    public LikesReadResponse readLikes(@PathVariable Long id) {
        LikesDto dto = articleLikesService.readLikes(id);
        return LikesReadResponse.of(dto);
    }

    @PostMapping("/{id}/like")
    public LikesReadResponse toggleLikes(@PathVariable Long id) {
        LikesDto dto = articleLikesService.toggleLikes(id);
        return LikesReadResponse.of(dto);
    }

    @PostMapping("/{id}/dislike")
    public LikesReadResponse toggleDisLikes(@PathVariable Long id) {
        LikesDto dto = articleLikesService.toggleDisLikes(id);
        return LikesReadResponse.of(dto);
    }
    
    @GetMapping("/best/views")
    public Page<ArticleReadMyPageRowResponse> readBestByViews(@PageableDefault Pageable pageable) {
        Page<ArticleDto> page = siteUserService.readBestByViews(pageable);
        return page.map(ArticleReadMyPageRowResponse::of);
    }
    
    @GetMapping("/best/likes")
    public Page<ArticleReadBestLikesResponse> readBestByLikes(@PageableDefault Pageable pageable) {
        Page<ArticleDto> page = siteUserService.readBestByLikes(pageable);
        return page.map(ArticleReadBestLikesResponse::of);
    }
}
