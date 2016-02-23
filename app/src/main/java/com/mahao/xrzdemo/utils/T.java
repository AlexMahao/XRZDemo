package com.mahao.xrzdemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast的封装工具类
 * Created by mdw on 2015/11/10.
 */
public class T {

    private T(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    public static void show(Context context,String msg){
        if(isShow){
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }
    }

}
