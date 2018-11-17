package com.rishabh.newstand.data.database.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.rishabh.newstand.data.database.AppDatabase;
import com.rishabh.newstand.pojo.headlinesresponse.Article;
import com.rishabh.newstand.pojo.sources.Source;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    // Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();
    private final AppDatabase database;

    public MainViewModel(Application application) {
        super(application);
        database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
    }

    public LiveData<List<Article>> getArticlesByHeadlines(String headlineType) {
        return database.newsDao().loadArticleByHeadlineType(headlineType);
    }


    public LiveData<List<Article>> getSavedArticlesByHeadlines() {
        return database.newsDao().loadSavedArticleByHeadlineType();
    }

    public LiveData<List<Source>> getSources() {
        return  database.newsDao().loadSources();
    }
}