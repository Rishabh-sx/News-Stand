package com.rishabh.newstand.home;

import com.rishabh.newstand.base.BasePresenter;

/**
 * Created by appinventiv on 27/3/18.
 */

public class HomePresenter extends BasePresenter<HomeView> implements HomeModelListener {


    private HomeModel model;

    public HomePresenter(HomeView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        model = new HomeModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {

    }
}
