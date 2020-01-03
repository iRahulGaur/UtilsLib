package com.rahulgaur.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

/**
 * Created by Rahul Gaur on 18, December, 2019
 * Email: rahul.gaur152@gmail.com
 * Github: github.com/iRahulGaur
 */
public class Utils {

    private String SHARED_PREFERENCE_FILE_NAME;
    private Class homeActivity;
    private ProgressManager progressManager;
    private PreferenceManager preferenceManager;
    private FileEncryption fileEncryption;
    private DataEncryption dataEncryption;
    private Context context;

    public Utils(Context context) {
        this.context = context;
    }

    //------------------------- Encryption of Data ----------------------------------------------//

    public DataEncryption encryptData(String secretKeyString) {
        if (dataEncryption == null)
            dataEncryption = new DataEncryption(secretKeyString);
        return dataEncryption;
    }

    //------------------------------------Encrypt file------------------------------------------//

    public FileEncryption encryptFile() {
        if (fileEncryption == null)
            fileEncryption = new FileEncryption();
        return fileEncryption;
    }

    //*************************************Intents*****************************************//

    public void setIntent(Context context, Class destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }

    public void setIntentNoBackLog(Context context, Class destination) {
        Intent intent = new Intent(context, destination);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public void setIntentExtra(Context context, Class destination, String key, Bundle data) {
        Intent intent = new Intent(context, destination);
        intent.putExtra(key, data);
        context.startActivity(intent);
    }

    public void sendToMain(Context context) {
        setIntentNoBackLog(context, homeActivity);
    }

    public void setHomeActivityClass(Class homeActivity) {
        this.homeActivity = homeActivity;
    }

    // --------------------------- Logs and Toasts ------------------------------------------ //
    public void showMessage(Context context, String message, int toast_length) {
        int length;

        if (toast_length == 1) {
            length = Toast.LENGTH_LONG;
        } else {
            length = Toast.LENGTH_SHORT;
        }

        Toast.makeText(context, message, length).show();
    }

    public void showLog(String TAG, String message, String exception) {
        Log.e(TAG, "showLog: " + message + " " + exception);
    }

    // -------------------------- ImagePicker --------------------------------------------- //

    public void imagePicker(Activity container, int x, int y) {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(x, y)
                .start(container);
    }

    public void imagePicker(Activity container) {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(container);
    }

    // ---------------------------- Shared Preferences ------------------------------------ //

    public PreferenceManager getPreferenceManager() {
        if (preferenceManager == null)
            preferenceManager = new PreferenceManager(context, SHARED_PREFERENCE_FILE_NAME);
        return preferenceManager;
    }

    public void setSharedPreferencesName(String SHARED_PREFERENCE_FILE_NAME) {
        this.SHARED_PREFERENCE_FILE_NAME = SHARED_PREFERENCE_FILE_NAME;
    }

    //------------------------ Progress Bar ------------------------------------------------//

    public ProgressManager getProgressManager() {
        if (progressManager == null)
            progressManager = new ProgressManager(context);
        return progressManager;
    }


    //https://medium.com/@yegor_zatsepin/simple-way-to-publish-your-android-library-to-jcenter-d1e145bacf13
    //-----------------------------------------------------------------------------------------//
}
