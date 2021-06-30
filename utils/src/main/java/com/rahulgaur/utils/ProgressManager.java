package com.rahulgaur.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

/**
 * This class will help to show progress dialog
 */
@SuppressWarnings("unused")
public class ProgressManager {
    private static AlertDialog aDProgress;

    /**
     * This method will show the dialog
     *
     * @param context         current context/activity
     * @param progressMessage this text will be shown next to the progress bar
     */
    public static void showProgressDialog(Context context, String progressMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = LayoutInflater.from(context).inflate(R.layout.pop_up_custom_progress, null);
        builder.setCancelable(false);
        builder.setView(view);

        TextView tvProgress = view.findViewById(R.id.tvProgress);
        tvProgress.setText(progressMessage);

        aDProgress = builder.create();
        Objects.requireNonNull(aDProgress.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        aDProgress.setCanceledOnTouchOutside(false);
        aDProgress.setCancelable(false);
        aDProgress.show();
    }

    /**
     * This method will dismiss progress dialog,
     * recommended to add this method in onStop() also
     */
    public static void dismissDialog() {
        if (aDProgress != null) {
            try {
                aDProgress.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("ProgressManager", "dismissDialog: exception with progressbar ");
            }
        }
    }

}
