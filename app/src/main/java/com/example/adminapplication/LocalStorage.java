package com.example.adminapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {
    private static final String PREFS_NAME = "my_prefs";
    private SharedPreferences mPrefs;

    public LocalStorage(Context context) {
        mPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void saveInt(String key, int value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        return mPrefs.getString(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return mPrefs.getInt(key, defaultValue);
    }

    public boolean contains(String key) {
        return mPrefs.contains(key);
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.remove(key);
        editor.apply();
    }
}
