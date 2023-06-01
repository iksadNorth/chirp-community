package com.chirp.community.model.projection;

import com.chirp.community.entity.Article;
import com.chirp.community.entity.Board;
import com.chirp.community.model.ArticleDto;
import com.chirp.community.model.BoardDto;

public interface ArticleMapperWithBestLikes{
    Article getArticle();
//    Integer getNumLikes();
}
