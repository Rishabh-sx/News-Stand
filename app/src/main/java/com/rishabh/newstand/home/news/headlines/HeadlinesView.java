package com.rishabh.newstand.home.news.headlines;

import com.rishabh.newstand.base.BaseView;

/**
 * Created by appinventiv on 27/3/18.
 */

public interface HeadlinesView extends BaseView {

    void getHeadlinesType();

    void initListeners(String fragmentType);

    void setAdapters();
}
