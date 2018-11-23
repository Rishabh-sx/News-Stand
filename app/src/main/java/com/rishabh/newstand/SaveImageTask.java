package com.rishabh.newstand;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class SaveImageTask extends AsyncTask<Void, Void, String> {

    private AsyncListener asyncListener;
    private final Bitmap bitmap;
    public SaveImageTask(Bitmap bitmap,AsyncListener asyncListener) {
        this.bitmap = bitmap;
        this.asyncListener = asyncListener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return ImageStorage.saveToSdCard(bitmap);
    }

    @Override
    protected void onPostExecute(String s) {
        asyncListener.imageSavedStatus(s);
    }

    public interface AsyncListener{
        void imageSavedStatus(String s);
    }
}