package com.rishabh.newstand.home.news.headlines;

import com.rishabh.newstand.base.BaseModel;
import com.rishabh.newstand.data.database.AppExecutors;
import com.rishabh.newstand.network.NetworkResponse;
import com.rishabh.newstand.pojo.FailureResponse;
import com.rishabh.newstand.pojo.headlinesresponse.Article;
import com.rishabh.newstand.pojo.headlinesresponse.HeadlinesResponse;
import com.rishabh.newstand.utils.AppConstants;

import java.util.HashMap;


class HeadlinesModel extends BaseModel<HeadlinesModelListener> {

    private static final String TAG = "HomeModel";

    public HeadlinesModel(HeadlinesModelListener listener) {
        super(listener);
    }

    @Override
    public void init() {

    }

    public void getHeadlines(final String headlineType) {

        final HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(AppConstants.KEY_CATEGORY,headlineType);
        hashMap.put(AppConstants.KEY_COUNTRY,"us");
        hashMap.put(AppConstants.KEY_API_KEY,AppConstants.NEWS_API_KEY);

        getDataManager().getHeadlines(hashMap).enqueue(new NetworkResponse<HeadlinesResponse>(this) {
            @Override
            public void onSuccess(final HeadlinesResponse body) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {

                        //Delete all unsaved articles from the database by headline type
                        //which are not saved.
                        getDataManager().getDatabase().newsDao().delAllUnsavedArticlesByHeadline(headlineType);

                        //Update all saved articles by headline type to remove from recent list
                        getDataManager().getDatabase().newsDao().updateRecentArticlesByHeadlines(headlineType);

                        for (Article article: body.getArticles()){
                            if(getDataManager().getDatabase().newsDao().
                                    checkIfExistInSavedArticles
                                            (article.getUrl(),article.getPublishedAt())>0){

                                article.setHeadlineType(headlineType);
                                article.setSaved(true);
                                article.setExistInRecentList(true);
                                getDataManager().getDatabase().newsDao().updateArticle(article);

                            }else {
                                article.setSaved(false);
                                article.setExistInRecentList(true);
                                article.setHeadlineType(headlineType);
                                getDataManager().getDatabase().newsDao().insertArticle(article);

                            }
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
