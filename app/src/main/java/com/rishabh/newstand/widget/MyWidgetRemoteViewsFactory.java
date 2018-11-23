package com.rishabh.newstand.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rishabh.newstand.R;
import com.rishabh.newstand.pojo.headlinesresponse.Article;
import com.rishabh.newstand.utils.AppConstants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MyWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<Article> articleList;

    public MyWidgetRemoteViewsFactory(Context applicationContext, Intent intent) {
        mContext = applicationContext;
        articleList = new ArrayList<>();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

        final long identityToken = Binder.clearCallingIdentity();

        SharedPreferences sharedPref = android.preference.PreferenceManager.getDefaultSharedPreferences(mContext);
        String savedNews = sharedPref.getString(AppConstants.KEY_POPULAR, "");


        if(savedNews!=null && !savedNews.isEmpty()) {
            Log.e("onReceive: ",savedNews );
            Gson gson = new Gson();
            Type founderListType = new TypeToken<ArrayList<Article>>(){}.getType();
            articleList = gson.fromJson(savedNews, founderListType);
        }

        Binder.restoreCallingIdentity(identityToken);

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return articleList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.collection_widget_list_item);
        rv.setTextViewText(R.id.widgetItemTaskNameLabel, "* "+ articleList.get(position).getTitle());

        Intent fillInIntent = new Intent();
        fillInIntent.putExtra(NewsStandWidget.EXTRA_LABEL, articleList.get(position));
        rv.setOnClickFillInIntent(R.id.widgetItemContainer, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}