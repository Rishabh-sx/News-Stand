package com.rishabh.newstand.utils;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.rishabh.newstand.R;

public class AppUtils {
    public static void shareText(FragmentActivity activity, String url) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, url);

        activity.startActivity(Intent.createChooser(share, activity.getString(R.string.s_share_news)));

    }
}
