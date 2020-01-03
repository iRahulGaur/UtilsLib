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

public class ProgressManager {
    private static AlertDialog aDProgress;
    private Context context;

    ProgressManager(Context context) {
        this.context = context;
    }

    public void showProgressDialog(String progress) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = LayoutInflater.from(context).inflate(R.layout.pop_up_custom_progress, null);
        builder.setCancelable(false);
        builder.setView(view);

        TextView tvProgress = view.findViewById(R.id.tvProgress);
        tvProgress.setText(progress);

        aDProgress = builder.create();
        Objects.requireNonNull(aDProgress.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        aDProgress.setCanceledOnTouchOutside(false);
        aDProgress.setCancelable(false);
        aDProgress.show();
    }

    public void dismissDialog() {
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
