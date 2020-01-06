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
public class Utils {
    private static final String TAG = "Utils";

    public static Class homeActivity;

    //*************************************Intents*****************************************//

    public static void setIntent(Context context, Class destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }

    public static void setIntentNoBackLog(Context context, Class destination) {
        Intent intent = new Intent(context, destination);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public static void setIntentExtra(Context context, Class destination, String key, Bundle data) {
        Intent intent = new Intent(context, destination);
        intent.putExtra(key, data);
        context.startActivity(intent);
    }

    public static void sendToMain(Context context) {
        setIntentNoBackLog(context, homeActivity);
    }

    // --------------------------- Logs and Toasts ------------------------------------------ //
    public static void showMessage(Context context, String message, int toast_length) {
        int length;

        if (toast_length == 1) {
            length = Toast.LENGTH_LONG;
        } else {
            length = Toast.LENGTH_SHORT;
        }

        Toast.makeText(context, message, length).show();
    }

    public static void showLogE(String TAG, String message, String exception) {
        Log.e(TAG, "showLog: " + message + " " + exception);
    }

    public static void showLogE(String TAG, String message, Exception exception) {
        Log.e(TAG, "showLog: " + message + " " + exception);
    }

    public static void showLogE(String TAG, String message) {
        Log.e(TAG, "showLog: " + message);
    }

    public static void showLogE(String TAG, Exception exception) {
        Log.e(TAG, "showLog: " + exception);
    }

    public static void showLogE(String message) {
        Log.e(TAG, "showLog: " + message);
    }

    public static void showLogE(Exception exception) {
        Log.e(TAG, "showLog: " + exception);
    }

    // -------------------------- ImagePicker --------------------------------------------- //

    public static void imagePicker(Activity container, int x, int y) {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(x, y)
                .start(container);
    }

    public static void imagePicker(Activity container) {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(container);
    }

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
            return false;
        } else {
            return false;
        }
    }

    //Show no internet Alert
    public static void showAlertConnectionError(@NonNull Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("No Internet");
        builder.setMessage("No Internet Available, Please try again");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public static void showAlertConnectionError(@NonNull Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    //-----------------------------------------------------------------------------------------//
}
