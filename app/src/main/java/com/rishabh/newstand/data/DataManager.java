package com.rishabh.newstand.data;

import android.content.Context;

import com.rishabh.newstand.data.api.ApiManager;
import com.rishabh.newstand.data.database.AppDatabase;
import com.rishabh.newstand.pojo.headlinesresponse.HeadlinesResponse;
import com.rishabh.newstand.pojo.sources.SourcesResponse;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by appinventiv on 27/3/18.
 */

public class DataManager implements IDataManager {

    private final AppDatabase mDb;
    private ApiManager apiManager;
    private static DataManager instance;


    public static void init(Context applicationContext) {
        if (instance == null)
            instance = new DataManager(applicationContext);
    }

    public static DataManager getInstance() {
        return instance;
    }

    private DataManager(Context applicationContext) {
        apiManager = ApiManager.getInstance();
        mDb = AppDatabase.getInstance(applicationContext);
    }

    /*public static DataManager getInstance() {
        if (instance == null) {
            synchronized (DataManager.class){
                if (instance == null)
                    instance = new DataManager(applicationContext);
            }
        }
        return instance;
    }*/


    public AppDatabase getDatabase() {
        return mDb;
    }

    @Override
    public Call<HeadlinesResponse> getHeadlines(HashMap<String, String> hashMap) {
        return apiManager.getHeadlines(hashMap);
    }

    @Override
    public Call<SourcesResponse> getSources(HashMap<String, String> hashMap) {
        return apiManager.getSources(hashMap);
    }
}
