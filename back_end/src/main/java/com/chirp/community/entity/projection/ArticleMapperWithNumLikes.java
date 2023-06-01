package com.chirp.community.entity.projection;

import com.chirp.community.entity.Article;

public interface ArticleMapperWithNumLikes {
    Article getArticle();
    Long getNumLikes();
}
