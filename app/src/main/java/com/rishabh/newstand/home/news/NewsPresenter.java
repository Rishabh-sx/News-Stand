package com.rishabh.newstand.home.news;

import com.rishabh.newstand.base.BasePresenter;

/**
 * Created by appinventiv on 27/3/18.
 */

public class NewsPresenter extends BasePresenter<NewsView> implements NewsModelListener {


    private NewsModel model;

    public NewsPresenter(NewsView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        model = new NewsModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {

    }

    public void onViewCreated() {
        if(getView()!=null)
            getView().setupViews();
    }
}
