package com.rishabh.newstand;

import android.app.Application;

import com.rishabh.newstand.data.DataManager;

public class NewsStandApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.init(getApplicationContext());

    }
}
