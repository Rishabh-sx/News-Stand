package com.rishabh.newstand;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.SystemClock;

import java.io.File;
import java.io.FileOutputStream;

public class ImageStorage {



    public static String saveToSdCard(Bitmap bitmap) {
        final String NEWS_STAND = "News Stand";
        final String JPG = ".jpg";

        String stored = null;

        File sdcard = Environment.getExternalStorageDirectory();

        File folder = new File(sdcard.getAbsoluteFile(), NEWS_STAND);//the dot makes this directory hidden to the user
        folder.mkdir();
        File file = new File(folder.getAbsoluteFile(), SystemClock.currentThreadTimeMillis() + JPG);
        if (file.exists())
            return stored;

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            stored = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stored;
    }


}