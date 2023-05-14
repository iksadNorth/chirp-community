package com.chirp.community.repository;

import com.chirp.community.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByBoard_Id(Long id, Pageable pageable);

    Page<Article> findByWriter_Id(Long id, Pageable pageable);
}
