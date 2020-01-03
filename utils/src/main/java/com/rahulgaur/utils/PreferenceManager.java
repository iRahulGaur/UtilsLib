package com.rahulgaur.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceManager {

    private Context context;
    private final String SHARED_PREFERENCE_FILE_NAME;
    private SharedPreferences sharedPreferences;
    private Editor editor;

    PreferenceManager(Context context, String shared_preference_file_name) {
        this.context = context;
        SHARED_PREFERENCE_FILE_NAME = shared_preference_file_name;
    }

    public void setPreferenceManager() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key) {
        try {
            return sharedPreferences.getBoolean(key, false);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void saveInt(String key, Integer trainerId) {
        editor.putInt(key, trainerId);
        editor.apply();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public void clearPreferences() {
        editor.clear();
        editor.apply();
    }
}
