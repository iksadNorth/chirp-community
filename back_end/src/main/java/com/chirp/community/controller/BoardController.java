package com.chirp.community.controller;

import com.chirp.community.model.ArticleDto;
import com.chirp.community.model.BoardDto;
import com.chirp.community.model.request.BoardCreateRequest;
import com.chirp.community.model.request.BoardUpdateRequest;
import com.chirp.community.model.response.ArticleReadRowResponse;
import com.chirp.community.model.response.BoardReadResponse;
import com.chirp.community.service.ArticleService;
import com.chirp.community.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final ArticleService articleService;

    @PostMapping
    public BoardReadResponse create(@RequestBody BoardCreateRequest request) {
        BoardDto dto = boardService.create(request.name());
        return BoardReadResponse.of(dto);
    }

    @GetMapping
    public Page<BoardReadResponse> readAllByKeyword(@RequestParam(defaultValue = "") String keyword, @PageableDefault(size = 10) Pageable pageable) {
        Page<BoardDto> pages = boardService.readAllByKeyword(keyword, pageable);
        return pages.map(BoardReadResponse::of);
    }

    @GetMapping("/{id}")
    public BoardReadResponse readById(@PathVariable Long id) {
        BoardDto dto = boardService.readById(id);
        return BoardReadResponse.of(dto);
    }

    @GetMapping("/{id}/article")
    public Page<ArticleReadRowResponse> readArticlesById(@PathVariable Long id, @PageableDefault Pageable pageable) {
        Page<ArticleDto> dto = articleService.readByBoardId(id, pageable);
        return dto.map(ArticleReadRowResponse::of);
    }

    @PatchMapping("/{id}")
    public BoardReadResponse updateById(@PathVariable Long id, @RequestBody BoardUpdateRequest request) {
        BoardDto dto = boardService.updateById(id, request.name());
        return BoardReadResponse.of(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        boardService.deleteById(id);
    }
}
