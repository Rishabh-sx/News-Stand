package com.rishabh.newstand.home.news.newsDetail;

import com.rishabh.newstand.base.BasePresenter;
import com.rishabh.newstand.pojo.headlinesresponse.Article;

/**
 * Created by appinventiv on 27/3/18.
 */


public class MovieDetailPresenter extends BasePresenter<MovieDetailView> implements MovieDetailModelListener {


    private MovieDetailModel model;

    public MovieDetailPresenter(MovieDetailView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        model = new MovieDetailModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {
        if(getView()!=null)
            getView().initViews();
    }

    public void updateArticle(Article article) {
        model.updateArticle(article);
    }
}
