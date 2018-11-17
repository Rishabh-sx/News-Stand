package com.rishabh.newstand.home.news;

import com.rishabh.newstand.base.BaseModel;

/**
 * Created by appinventiv on 27/3/18.
 */

class NewsModel extends BaseModel<NewsModelListener> {

    private static final String TAG = "HomeModel";

    public NewsModel(NewsModelListener listener) {
        super(listener);
    }

    @Override
    public void init() {

    }

}
