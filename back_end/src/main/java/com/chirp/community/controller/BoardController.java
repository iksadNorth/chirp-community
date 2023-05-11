package com.chirp.community.controller;

import com.chirp.community.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import com.chirp.community.model.BoardDto;
import com.chirp.community.model.response.BoardReadResponse;
import com.chirp.community.model.request.BoardCreateRequest;
import com.chirp.community.model.request.BoardUpdateRequest;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public BoardReadResponse create(@RequestBody BoardCreateRequest request) {
        BoardDto dto = boardService.create(request.name());
        return BoardReadResponse.of(dto);
    }

    @GetMapping
    public Page<BoardReadResponse> readAllByKeyword(@RequestParam String keyword, @PageableDefault(size = 10) Pageable pageable) {
        Page<BoardDto> pages = boardService.readAllByKeyword(keyword, pageable);
        return pages.map(BoardReadResponse::of);
    }

    @GetMapping("/{id}")
    public BoardReadResponse readById(@PathVariable Long id) {
        BoardDto dto = boardService.readById(id);
        return BoardReadResponse.of(dto);
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
