package com.rishabh.newstand.home.search.searched;

import com.rishabh.newstand.base.BasePresenter;

public class SearchPresenter extends BasePresenter<SearchView> implements SearchModelListener {
    private SearchModel mModel;

    public SearchPresenter(SearchView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        mModel = new SearchModel(this);
    }

    @Override
    protected void destroy() {

    }

    @Override
    protected void initView() {
        if (getView() != null) {
            getView().initViewsAndVariables();
            getView().setUpAdapter();
        }
    }

    public void getSearchedNews(String mSearch) {
        mModel.getSearch(mSearch);

    }
}
