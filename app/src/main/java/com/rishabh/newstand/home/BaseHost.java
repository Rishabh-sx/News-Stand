package com.rishabh.newstand.home;

import com.rishabh.newstand.pojo.headlinesresponse.Article;

public interface BaseHost {
    void openArticleDetail(Article article);
    void share(String url);
}
