package com.rishabh.newstand.home.search.searched;

import com.rishabh.newstand.base.BaseView;
import com.rishabh.newstand.pojo.headlinesresponse.Article;

import java.util.List;

interface SearchView extends BaseView {
    void initViewsAndVariables();

    void setUpAdapter();

    void setSearchResult(List<Article> articles);

    void clearAdapter();
}
