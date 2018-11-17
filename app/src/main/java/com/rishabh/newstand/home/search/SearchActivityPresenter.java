package com.rishabh.newstand.home.search;

import com.rishabh.newstand.base.BasePresenter;

public class SearchActivityPresenter extends BasePresenter<SearchActivityView> {
    public SearchActivityPresenter(SearchActivityView view) {
        super(view);
    }

    @Override
    protected void setModel() {

    }

    @Override
    protected void destroy() {

    }

    @Override
    protected void initView() {
     if (getView()!=null)
         getView().initViewsandVariables();
    }
}
