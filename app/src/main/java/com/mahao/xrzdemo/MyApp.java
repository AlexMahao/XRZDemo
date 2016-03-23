package com.mahao.xrzdemo;

import android.app.Application;
import android.graphics.Bitmap;

import com.activeandroid.ActiveAndroid;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by mdw on 2015/11/12.
 */
public class MyApp extends Application {

    /**
     * 城市Id
     */
    private int cityId  = 101;

    private  static MyApp myApp;

    private  DisplayImageOptions options;
    @Override
    public void onCreate() {
        super.onCreate();

        myApp = this;

        options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.newbackground)
                    .showImageOnFail(R.mipmap.newbackground)
                .cacheInMemory(true)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.ARGB_4444).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        ActiveAndroid.initialize(this);
    }

    public  static MyApp getApp(){
        return  myApp;
    }

    /**
     * 获取ImageLoader图片加载的配置文件
     * @return
     */
    public   DisplayImageOptions getDisplayImageOptions(){
        return options;
    }


    public int getCityId(){
        return  cityId;
    }

    public static int dip2px(float paramFloat)
    {
        return (int)(0.5F + paramFloat * myApp.getResources().getDisplayMetrics().density);
    }
}
