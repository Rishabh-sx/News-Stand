package com.rishabh.newstand.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rishabh.newstand.R;
import com.rishabh.newstand.home.HomeActivity;
import com.rishabh.newstand.pojo.headlinesresponse.Article;
import com.rishabh.newstand.utils.AppConstants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewsStandWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
     //   super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds) {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            appWidgetManager.updateAppWidget(appWidgetId, views);

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    new Intent(context, HomeActivity.class), 0);

            views.setOnClickPendingIntent(R.id.ll_layout, pendingIntent);
        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget);

        SharedPreferences sharedPref = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        String savedNews = sharedPref.getString(AppConstants.KEY_POPULAR, "");

        views.setTextViewText(R.id.tv_saved_news, "");

        if(savedNews.isEmpty()) {
            views.setTextViewText(R.id.tv_saved_news, context.getString(R.string.s_no_iten_avialable));
        } else {

            Log.e("onReceive: ",savedNews );
            Gson gson = new Gson();

            Type founderListType = new TypeToken<ArrayList<Article>>(){}.getType();


            List<Article> articlesList = gson.fromJson(savedNews, founderListType);

            StringBuilder string = new StringBuilder();

            for (Article article : articlesList)
                string.append("\n \n*").append(article.getTitle());

            Log.e("String Builder", string.toString());
            views.setTextViewText(R.id.tv_saved_news, string.toString());

        }

        PendingIntent pendingIntent = PendingIntent.
                getActivity(context,
                        0,
                        new Intent(context, HomeActivity.class),
                        0);

        views.setOnClickPendingIntent(R.id.ll_layout, pendingIntent);


        AppWidgetManager.getInstance(context).updateAppWidget(
                new ComponentName(context, NewsStandWidget.class), views);

    }
}
