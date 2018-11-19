package com.rishabh.newstand;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.rishabh.newstand.data.DataManager;
import com.rishabh.newstand.data.database.viewmodel.MainViewModel;
import com.rishabh.newstand.pojo.headlinesresponse.Article;
import com.rishabh.newstand.utils.AppConstants;

import java.util.List;

public class NewsStandApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.init(getApplicationContext());
    }
}
