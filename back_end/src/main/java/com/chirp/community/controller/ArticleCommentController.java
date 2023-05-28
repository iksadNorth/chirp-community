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
    public ArticleCommentReadResponse readByArticleCommentId(@RequestParam Long articleComment_id) {
        ArticleCommentDto dto = articleCommentService.readById(articleComment_id);
        return ArticleCommentReadResponse.of(dto);
    }

    @GetMapping("/{id}/article")
    public Page<ArticleCommentReadResponse> readAllByBArticleId(@RequestParam Long article_id, @PageableDefault(size = 10) Pageable pageable) {
        Page<ArticleCommentDto> dto = articleCommentService.readAllByArticleId(article_id, pageable);
        return dto.map(ArticleCommentReadResponse::of);
    }

    @GetMapping("/{id}/user")
    public Page<ArticleCommentReadResponse> readAllBySiteUserId(@RequestParam Long siteUser_id, @PageableDefault(size = 10) Pageable pageable) {
        Page<ArticleCommentDto> dto = articleCommentService.readAllBySiteUserId(siteUser_id, pageable);
        return dto.map(ArticleCommentReadResponse::of);
    }

    @PostMapping("/create")
    public ArticleCommentReadResponse createArticleComment(@RequestParam ArticleCommentCreateRequest request) {
        ArticleCommentDto dto = articleCommentService.createArticleComment(request.content(), request.article_id());
        return ArticleCommentReadResponse.of(dto);
    }

    @PatchMapping("/update/{id}")
    public ArticleCommentReadResponse updateArticleComment(@RequestParam Long articleComment_id, @RequestParam ArticleCommentCreateRequest request) {
        ArticleCommentDto dto = articleCommentService.updateArticleComment(articleComment_id, request.content());
        return ArticleCommentReadResponse.of(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArticleComment(@RequestParam Long articleComment_id) {
        articleCommentService.deleteById(articleComment_id);
    }


}
