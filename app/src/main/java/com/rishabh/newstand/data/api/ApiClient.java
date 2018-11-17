package com.rishabh.newstand.data.api;

import com.rishabh.newstand.pojo.headlinesresponse.HeadlinesResponse;
import com.rishabh.newstand.pojo.sources.SourcesResponse;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

interface ApiClient {

    String ENDPOINT = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<HeadlinesResponse> getHeadlineTypes(@QueryMap HashMap<String, String> params);

    @GET("sources")
    Call<SourcesResponse> getSources(@QueryMap HashMap<String,String> hashMap);

}
