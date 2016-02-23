package com.mahao.xrzdemo.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.widget.HeartProgressBar;

/**
 * 加载进度
 */
public class ProgressDialogUtil {

    private Dialog dialog;

    public void createDialog(Context cotext){
        LinearLayout view = (LinearLayout) LayoutInflater.from(cotext)
                .inflate(R.layout.heartprogress, null);
        HeartProgressBar progress = (HeartProgressBar) view.findViewById(R.id.progressBarId);

        dialog = new Dialog(cotext,R.style.dialog);
        dialog.setContentView(view,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        progress.start();
    }

    public void show(){
        dialog.show();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                dialog.dismiss();
            }
        }.start();

    }

    public void dimiss(){
        dialog.dismiss();
    }

}
