package com.rishabh.newstand.widget;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
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
import com.rishabh.newstand.splash.SplashActivity;
import com.rishabh.newstand.utils.AppConstants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewsStandWidget extends AppWidgetProvider {

    public static final String EXTRA_LABEL = "fd";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(
                    context.getPackageName(),
                    R.layout.layout_widget
            );
            Intent intent = new Intent(context, MyWidgetRemoteViewsService.class);
            views.setRemoteAdapter(R.id.widgetListView, intent);
            appWidgetManager.updateAppWidget(appWidgetId, views);

            Intent clickIntentTemplate = new Intent(context, HomeActivity.class);
            PendingIntent clickPendingIntentTemplate = TaskStackBuilder.create(context)
                    .addNextIntentWithParentStack(clickIntentTemplate)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            views.setPendingIntentTemplate(R.id.widgetListView, clickPendingIntentTemplate);
/*

            Intent home = new Intent(context, SplashActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, home, 0);
            views.setOnClickPendingIntent(R.id.widgetTitleLabel, pendingIntent);
*/

        }


    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        final String action = intent.getAction();
        if (action != null && action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            // refresh all your widgets
            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            ComponentName cn = new ComponentName(context, NewsStandWidget.class);
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.widgetListView);
            Intent clickIntentTemplate = new Intent(context, HomeActivity.class);
            PendingIntent clickPendingIntentTemplate = TaskStackBuilder.create(context)
                    .addNextIntentWithParentStack(clickIntentTemplate)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setPendingIntentTemplate(R.id.widgetListView, clickPendingIntentTemplate);

/*            Intent home = new Intent(context, SplashActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, home,0);
            views.setOnClickPendingIntent(R.id.widgetTitleLabel, pendingIntent);*/

        }




    }
}
