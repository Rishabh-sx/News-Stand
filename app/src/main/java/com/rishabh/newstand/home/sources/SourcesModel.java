package com.rishabh.newstand.home.sources;

import android.util.Log;

import com.rishabh.newstand.base.BaseModel;
import com.rishabh.newstand.data.database.AppExecutors;
import com.rishabh.newstand.network.NetworkResponse;
import com.rishabh.newstand.pojo.FailureResponse;
import com.rishabh.newstand.pojo.headlinesresponse.Article;
import com.rishabh.newstand.pojo.headlinesresponse.HeadlinesResponse;
import com.rishabh.newstand.pojo.sources.Source;
import com.rishabh.newstand.pojo.sources.SourcesResponse;
import com.rishabh.newstand.utils.AppConstants;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;


class SourcesModel extends BaseModel<SourcesModelListener> {

    private static final String TAG = "HomeModel";

    public SourcesModel(SourcesModelListener listener) {
        super(listener);
    }

    @Override
    public void init() {

    }

    public void getSources() {
        final HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(AppConstants.KEY_COUNTRY,"us");
        hashMap.put(AppConstants.KEY_LANGUAGE,"en");
        hashMap.put(AppConstants.KEY_API_KEY,AppConstants.NEWS_API_KEY);

        getDataManager().getSources(hashMap).enqueue(new NetworkResponse<SourcesResponse>(this) {
            @Override
            public void onSuccess(final SourcesResponse body) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        getDataManager().getDatabase().newsDao().deleteSources();
                        for (Source source: body.getSources()){
                            getDataManager().getDatabase().newsDao().insertSources(source);
                        }
                    }
                });
            }

            @Override
            public void onFailure(int code, FailureResponse failureResponse) {

            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }


}
