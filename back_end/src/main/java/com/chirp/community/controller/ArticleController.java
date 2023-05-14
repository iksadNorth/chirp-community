package com.chirp.community.controller;

import com.chirp.community.model.ArticleDto;
import com.chirp.community.model.request.ArticleUpdateRequest;
import com.chirp.community.model.request.ArticleCreateRequest;
import com.chirp.community.model.response.ArticleReadResponse;
import com.chirp.community.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService siteUserService;

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
}
