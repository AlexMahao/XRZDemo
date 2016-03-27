package com.mahao.xrzdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import com.mahao.xrzdemo.utils.AppManager;

/**
 *
 * Created by Alex_MaHao on 2016/3/22.
 */
public class BaseActivity  extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AppManager.getAppManager().finishActivity(this);

    }

    public void intent2Activity(Class clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }
}
