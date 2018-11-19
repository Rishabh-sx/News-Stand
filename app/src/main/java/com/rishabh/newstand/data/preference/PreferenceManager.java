package com.rishabh.newstand.data.preference;

import android.content.Context;
import android.content.SharedPreferences;



public class PreferenceManager {

    private static PreferenceManager instance;
    private SharedPreferences preference;
    private final Context context;

    private PreferenceManager(Context context) {
        this.context = context;
        initPreference();
    }


    public static PreferenceManager getInstance(Context context) {
        if (instance == null) {
            synchronized (PreferenceManager.class) {
                if (instance == null)
                    instance = new PreferenceManager(context);
            }
        }
        return instance;
    }


    private void initPreference() {
        if (preference == null) {
            preference = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public void setString(String preferenceKeys,String value) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(preferenceKeys, value);
        editor.apply();
    }

    public void setInteger(String preferenceKeys,Integer value) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(preferenceKeys, value);
        editor.apply();
    }

    public String getString(String key) {
        return preference.getString(key, "");
    }

    public Integer getInteger(String key) {
        return preference.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return preference.getBoolean(key, false);
    }

    public void setBoolean(String key, boolean value ) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void clearPreferences() {
        SharedPreferences.Editor editor = preference.edit();
        editor.clear();
        editor.apply();

    }
}
