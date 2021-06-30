package com.rahulgaur.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * PreferenceManager class to manage your SharedPreferences without any hassle
 */
@SuppressWarnings("unused")
public class PreferenceManager {

    private static SharedPreferences sharedPreferences;
    private static Editor editor;

    /**
     * This method will generate your Preference manager class, it is recommended to set this at application
     *
     * @param context                     current context/activity
     * @param SHARED_PREFERENCE_FILE_NAME share preference file name; recommended to set at Application
     */
    public static void setPreferenceManager(Context context, String SHARED_PREFERENCE_FILE_NAME) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    /**
     * This method will save string to shared preference
     *
     * @param key   Name of the field you want to save data for
     * @param value Data you want to save for key/name
     */
    public static void saveString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * This method will returned saved string
     *
     * @param key Name of the field you want to save data for
     * @return string for the given key
     */
    public static String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    /**
     * This method will save boolean to shared preference
     *
     * @param key   Name of the field you want to save data for
     * @param value Data you want to save for key/name
     */
    public static void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * This method will returned saved boolean
     *
     * @param key Name of the field you want to save data for
     * @return boolean for the given key
     */
    public static boolean getBoolean(String key) {
        try {
            return sharedPreferences.getBoolean(key, false);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method will save int to shared preference
     *
     * @param key   Name of the field you want to save data for
     * @param value Data you want to save for key/name
     */
    public static void saveInt(String key, Integer value) {
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * This method will returned saved int
     *
     * @param key Name of the field you want to save data for
     * @return int for the given key
     */
    public static int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    /**
     * this will clear all the saved preferences
     * eg:= use when logging out the user
     */
    public static void clearPreferences() {
        editor.clear();
        editor.apply();
    }
}
