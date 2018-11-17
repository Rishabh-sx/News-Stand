package com.rishabh.newstand.home.sources;

import com.rishabh.newstand.base.BasePresenter;
import com.rishabh.newstand.utils.AppConstants;

/**
 * Created by appinventiv on 27/3/18.
 */

public class SourcesPresenter extends BasePresenter<SourcesView> implements SourcesModelListener {


    private SourcesModel model;

    public SourcesPresenter(SourcesView view) {
        super(view);
    }

    @Override
    protected void setModel() {
        model = new SourcesModel(this);
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

        setModel();
        model.getSources();
    }

    public void fragmentConfigChanged() {
        setupView();
    }

    private void setupView() {
    if(getView()!=null)
        getView().initView();
        getView().initListeners();
    }
}
