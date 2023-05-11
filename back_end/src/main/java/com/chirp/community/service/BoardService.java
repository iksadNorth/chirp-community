package com.chirp.community.service;

import com.chirp.community.model.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    BoardDto create(String name);

    Page<BoardDto> readAllByKeyword(String keyword, Pageable pageable);

    BoardDto readById(Long id);

    BoardDto updateById(Long id, String name);

    void deleteById(Long id);

}
