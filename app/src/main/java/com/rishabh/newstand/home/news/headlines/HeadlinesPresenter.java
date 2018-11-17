package com.rishabh.newstand.home.news.headlines;

import com.rishabh.newstand.base.BasePresenter;
import com.rishabh.newstand.utils.AppConstants;

/**
 * Created by appinventiv on 27/3/18.
 */

public class HeadlinesPresenter extends BasePresenter<HeadlinesView> implements HeadlinesModelListener {


    private HeadlinesModel model;

    public HeadlinesPresenter(HeadlinesView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        model = new HeadlinesModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {
        if (getView() != null) {
            getView().getHeadlinesType();
        }
    }



    public void fetchDataByType(String fragmentType) {
        model.getHeadlines(fragmentType);
    }

    public void fragmentTypeFetched(String fragmentType) {
        if(!fragmentType.equals(AppConstants.KEY_SAVED))
            model.getHeadlines(fragmentType);

        if (getView() != null) {
            getView().setAdapters();
            getView().initListeners(fragmentType);
        }
    }

    public void savedInstanceStateFlow(String fragmentType) {
        if (getView() != null) {
            getView().setAdapters();
            getView().initListeners(fragmentType);
        }
    }
}
