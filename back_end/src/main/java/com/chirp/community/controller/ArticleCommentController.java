package com.chirp.community.controller;


import com.chirp.community.model.ArticleCommentDto;
import com.chirp.community.model.request.ArticleCommentCreateRequest;
import com.chirp.community.model.response.ArticleCommentReadResponse;
import com.chirp.community.service.ArticleCommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/article_comment")
@RestController
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @GetMapping("/{id}")
    public ArticleCommentReadResponse readByArticleCommentId(@PathVariable Long id) {
        ArticleCommentDto dto = articleCommentService.readById(id);
        return ArticleCommentReadResponse.of(dto);
    }

    @GetMapping("/article/{id}")
    public Page<ArticleCommentReadResponse> readAllByBArticleId(@PathVariable Long id, @PageableDefault(size = 10) Pageable pageable) {
        Page<ArticleCommentDto> dto = articleCommentService.readAllByArticleId(id, pageable);
        return dto.map(ArticleCommentReadResponse::of);
    }

    @GetMapping("/user/{id}")
    public Page<ArticleCommentReadResponse> readAllBySiteUserId(@PathVariable Long id, @PageableDefault(size = 10) Pageable pageable) {
        Page<ArticleCommentDto> dto = articleCommentService.readAllBySiteUserId(id, pageable);
        return dto.map(ArticleCommentReadResponse::of);
    }

    @PostMapping("/create")
    public ArticleCommentReadResponse createArticleComment(@RequestBody ArticleCommentCreateRequest request) {
        ArticleCommentDto dto = articleCommentService.createArticleComment(request.content(), request.article_id());
        return ArticleCommentReadResponse.of(dto);
    }


    @PatchMapping("/{id}")
    public ArticleCommentReadResponse updateArticleComment(@PathVariable Long id, @RequestBody ArticleCommentCreateRequest request) {
        ArticleCommentDto dto = articleCommentService.updateArticleComment(id, request.content());
        return ArticleCommentReadResponse.of(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteArticleComment(@PathVariable Long id) {
        articleCommentService.deleteById(id);
    }


}
