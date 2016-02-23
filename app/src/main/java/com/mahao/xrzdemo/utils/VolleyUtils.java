package com.mahao.xrzdemo.utils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.mahao.xrzdemo.MyApp;

/**
 * Volley封装工具类
 * Created by mdw on 2015/11/12.
 */
public class VolleyUtils {

    private static  RequestQueue queue;


    public static RequestQueue getInstance(){
        if(queue==null){
            queue = Volley.newRequestQueue(MyApp.getApp());
        }
        return queue;
    }
}
