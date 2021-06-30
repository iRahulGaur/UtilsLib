package com.rahulgaur.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

/**
 * Created by Rahul Gaur on 18, December, 2019
 * Email: rahul.gaur152@gmail.com
 * Github: github.com/iRahulGaur
 */
@SuppressWarnings("rawtypes, unused")
public class Utils {

    private static final String TAG = "Utils";

    /**
     * Define this on app launch; eg: Utils.homeActivity = MainActivity.java
     */
    public static Class homeActivity;

    //*************************************Intents*****************************************//

    /**
     * This is a simple intent to send from 1 activity to another
     *
     * @param context     current context/activity
     * @param destination destination activity class
     */
    public static void setIntent(Context context, Class destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }

    /**
     * This intent will remove all back log/stack of activities
     *
     * @param context     current context/activity
     * @param destination destination activity class
     */
    public static void setIntentNoBackLog(Context context, Class destination) {
        Intent intent = new Intent(context, destination);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    /**
     * This is a simple intent to send from 1 activity to another with Bundle data
     *
     * @param context     current context/activity
     * @param destination destination activity class
     * @param key         name of the data you want to send; eg: "user_data"
     * @param data        bundle data you want to send to another activity
     */
    public static void setIntentExtra(Context context, Class destination, String key, Bundle data) {
        Intent intent = new Intent(context, destination);
        intent.putExtra(key, data);
        context.startActivity(intent);
    }

    /**
     * This will send to homeActivity which is defined on start of the app
     *
     * @param context current context/activity
     */
    public static void sendToMain(Context context) {
        setIntentNoBackLog(context, homeActivity);
    }

    // --------------------------- Logs and Toasts ------------------------------------------ //

    public static int TOAST_SHORT = 0;
    public static int TOAST_LONG = 1;

    /**
     * This method will generate a toast
     *
     * @param context      current context/activity
     * @param message      toast message you want to show
     * @param toast_length toast length; 1 = LONG, 0 = SHORT; you can also use Constants like Utils.TOAST_LONG
     */
    public static void showMessage(Context context, String message, int toast_length) {
        int length;

        if (toast_length == TOAST_SHORT) {
            length = Toast.LENGTH_LONG;
        } else {
            length = Toast.LENGTH_SHORT;
        }

        Toast.makeText(context, message, length).show();
    }

    /**
     * This method will generate a log.error
     *
     * @param TAG       log's tag string
     * @param message   log's message string
     * @param exception log's exception as string; eg = exception.getMessage()
     */
    public static void showLogE(String TAG, String message, String exception) {
        Log.e(TAG, "showLog: " + message + " " + exception);
    }

    /**
     * This method will generate a log.error
     *
     * @param TAG       log's tag string
     * @param message   log's message string
     * @param exception log's exception as Exception; eg = exception
     */
    public static void showLogE(String TAG, String message, Exception exception) {
        Log.e(TAG, "showLog: " + message + " " + exception.getMessage());
    }

    /**
     * This method will generate a log.error
     *
     * @param TAG     log's tag string
     * @param message log's message string
     */
    public static void showLogE(String TAG, String message) {
        Log.e(TAG, "showLog: " + message);
    }

    /**
     * This method will generate a log.error
     *
     * @param TAG       log's tag string
     * @param exception log's exception as Exception; eg = exception
     */
    public static void showLogE(String TAG, Exception exception) {
        Log.e(TAG, "showLog: " + exception.getMessage());
    }

    /**
     * This method will generate a log.error
     *
     * @param message log's message string
     */
    public static void showLogE(String message) {
        Log.e(TAG, "showLog: " + message);
    }

    /**
     * This method will generate a log.error
     *
     * @param exception log's exception as Exception; eg = exception
     */
    public static void showLogE(Exception exception) {
        Log.e(TAG, "showLog: " + exception.getMessage());
    }

    /**
     * This method will generate a log.error
     *
     * @param TAG     log's tag string
     * @param message log's message string
     */
    public static void log(String TAG, String message) {
        Log.e(TAG, "showLog: " + message);
    }

    // -------------------------- ImagePicker --------------------------------------------- //

    /**
     * This method will open image picker with crop functionality
     * <p>
     * This method is no longer working after android 11's scoped storage problem,
     * it is working on android 10 and below
     *
     * @param container  current activity
     * @param xAxisRatio ratio for X axis
     * @param yAxisRatio ratio for y axis
     */
    @Deprecated
    public static void imagePicker(Activity container, int xAxisRatio, int yAxisRatio) {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(xAxisRatio, yAxisRatio)
                .start(container);
    }

    /**
     * This method will open image picker with crop functionality
     * <p>
     * This method is no longer working after android 11's scoped storage problem,
     * it is working on android 10 and below
     *
     * @param container current activity
     */
    @Deprecated
    public static void imagePicker(Activity container) {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(container);
    }

    /**
     * This method will return true or false about internet connectivity
     *
     * @param context current context/activity
     * @return if device is connected to internet or not
     */
    //check connectivity
    public static boolean isNetworkConnected(@NonNull Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            // for android api 28 >
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                @SuppressLint("MissingPermission") NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        return true;
                    }
                }
            } else {
                //for android api 28 <
                try {
                    @SuppressLint("MissingPermission") NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        Log.e(TAG, "Network is available : true");
                        return true;
                    }
                } catch (Exception e) {
                    Log.e(TAG, "" + e.getMessage());
                }
            }
            Log.e(TAG, "Network is available : FALSE ");
        }
        return false;
    }

    /**
     * This method will show "No internet connectivity" alert dialog mostly used with isNetworkConnected()
     *
     * @param context current context/activity
     */
    //Show no internet Alert
    public static void showAlertConnectionError(@NonNull Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("No Internet");
        builder.setMessage("No Internet Available, Please try again");
        builder.setPositiveButton("Ok", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.show();
    }

    /**
     * This method will show alert dialog
     *
     * @param context       current context/activity
     * @param title         alert's title
     * @param message       alert's body
     * @param buttonText    positive button's text
     * @param cancelable    if dialog is cancelable
     * @param clickListener click listener for positive button
     */
    public static void showAlert(@NonNull Context context, String title, String message, String buttonText, Boolean cancelable, final ClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(cancelable);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(buttonText, (dialogInterface, i) -> clickListener.onClick(dialogInterface));
        builder.show();
    }

    //-----------------------------------------------------------------------------------------//
}

/**
 * click listener for dialogAlert click
 */
interface ClickListener {

    /**
     * @param dialog alert dialog instance to close it accordingly
     */
    void onClick(DialogInterface dialog);
}