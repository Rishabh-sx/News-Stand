package com.rishabh.newstand.home.search.searched;

import android.util.Log;

import com.rishabh.newstand.base.BaseModel;
import com.rishabh.newstand.data.database.AppExecutors;
import com.rishabh.newstand.network.NetworkResponse;
import com.rishabh.newstand.pojo.FailureResponse;
import com.rishabh.newstand.pojo.headlinesresponse.Article;
import com.rishabh.newstand.pojo.headlinesresponse.HeadlinesResponse;
import com.rishabh.newstand.utils.AppConstants;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;


class SearchModel extends BaseModel<SearchModelListener> {

    private static final String TAG = "Search Model";

    public SearchModel(SearchModelListener listener) {
        super(listener);
    }

    @Override
    public void init() {

    }

    public void getSearch(String search) {
        getDataManager().getSearch(createResponsePayload(search)).enqueue(new NetworkResponse<HeadlinesResponse>(this) {
            @Override
            public void onSuccess(final HeadlinesResponse body) {

            }

            @Override
            public void onFailure(int code, FailureResponse failureResponse) {

            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    private HashMap<String, String> createResponsePayload(String search) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(AppConstants.KEY_QUERY, search);
        hashMap.put(AppConstants.KEY_LANGUAGE, "en");
        hashMap.put(AppConstants.KEY_API_KEY, AppConstants.NEWS_API_KEY);
        return hashMap;
    }


}
