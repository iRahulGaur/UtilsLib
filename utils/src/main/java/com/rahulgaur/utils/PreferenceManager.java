package com.rahulgaur.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceManager {

    private static SharedPreferences sharedPreferences;
    private static Editor editor;

    public static void setPreferenceManager(Context context, String SHARED_PREFERENCE_FILE_NAME) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public static void saveString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public  static void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key) {
        try {
            return sharedPreferences.getBoolean(key, false);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void saveInt(String key, Integer trainerId) {
        editor.putInt(key, trainerId);
        editor.apply();
    }

    public static int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public static void clearPreferences() {
        editor.clear();
        editor.apply();
    }
}
