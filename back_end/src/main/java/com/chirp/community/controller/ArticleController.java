package com.chirp.community.controller;

import com.chirp.community.model.ArticleDto;
import com.chirp.community.model.request.ArticleUpdateRequest;
import com.chirp.community.model.request.ArticleCreateRequest;
import com.chirp.community.model.response.ArticleReadResponse;
import com.chirp.community.model.response.LikesReadResponse;
import com.chirp.community.model.LikesDto;
import com.chirp.community.service.ArticleService;
import com.chirp.community.service.ArticleLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {
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
}
