package com.rishabh.newstand.home.search.searched;

import com.rishabh.newstand.base.BaseModelListener;
import com.rishabh.newstand.pojo.headlinesresponse.Article;

import java.util.List;

/**
 * Created by appinventiv on 27/3/18.
 */

interface SearchModelListener extends BaseModelListener {

    void onSearchResponse(List<Article> articles);
}
