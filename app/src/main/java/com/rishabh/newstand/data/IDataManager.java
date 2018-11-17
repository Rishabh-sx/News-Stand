package com.rishabh.newstand.data;

import com.rishabh.newstand.pojo.headlinesresponse.HeadlinesResponse;
import com.rishabh.newstand.pojo.sources.SourcesResponse;

import java.lang.ref.Reference;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;

interface IDataManager {

    Call<HeadlinesResponse> getHeadlines(HashMap<String,String> hashMap);

    Call<SourcesResponse> getSources(HashMap<String,String> hashMap);

    Call<HeadlinesResponse> getSearch(HashMap<String, String> hashMap);
}
