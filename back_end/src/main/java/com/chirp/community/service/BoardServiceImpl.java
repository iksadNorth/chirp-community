package com.chirp.community.service;

import com.chirp.community.entity.Board;
import com.chirp.community.exception.CommunityException;
import com.chirp.community.model.BoardDto;
import com.chirp.community.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public BoardDto create(String name) {
        Board entity = Board.of(name);
        Board saved = boardRepository.save(entity);
        return BoardDto.fromEntity(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BoardDto> readAllByKeyword(String keyword, Pageable pageable) {
        Page<Board> pages;
        if(keyword.isBlank()) {
            pages = boardRepository.findAll(pageable);
        } else {
            pages = boardRepository.findAllByKeyword(keyword, pageable);
        }
        return pages.map(BoardDto::fromEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardDto readById(Long id) {
        return boardRepository.findById(id)
                .map(BoardDto::fromEntity)
                .orElseThrow(
                        () -> CommunityException.of(
                                HttpStatus.NOT_FOUND,
                                String.format("%s번 게시판은 존재하지 않음.", id)
                                )
                );
    }

    @Override
    public BoardDto updateById(Long id, String name) {
        Board entity = Board.of(id, name);
        Board saved = boardRepository.save(entity);
        return BoardDto.fromEntity(saved);
    }

    @Override
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
