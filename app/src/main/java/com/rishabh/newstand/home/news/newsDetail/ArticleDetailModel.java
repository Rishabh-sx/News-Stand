package com.rishabh.newstand.home.news.newsDetail;

import com.rishabh.newstand.base.BaseModel;
import com.rishabh.newstand.data.database.AppExecutors;
import com.rishabh.newstand.pojo.headlinesresponse.Article;

/**
 * Created by appinventiv on 27/3/18.
 */

class ArticleDetailModel extends BaseModel<ArticleDetailModelListener> {

    private static final String TAG = "MovieListModel";

    public ArticleDetailModel(ArticleDetailModelListener listener) {
        super(listener);
    }

    @Override
    public void init() {

    }


    public void updateArticle(final Article article) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                getDataManager().getDatabase().newsDao().updateArticle(article);

            }
        });
    }
}
