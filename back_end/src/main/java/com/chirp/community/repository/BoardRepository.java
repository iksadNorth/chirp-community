package com.chirp.community.repository;

import com.chirp.community.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(
            value = "SELECT b FROM Board b WHERE b.name LIKE \"%:keyword%\"",
            countQuery = "SELECT COUNT(b) FROM Board b WHERE b.name LIKE \"%:keyword%\""
    )
    Page<Board> findAllByKeyword(String keyword, Pageable pageable);
}
