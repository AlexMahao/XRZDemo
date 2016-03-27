package com.mahao.xrzdemo;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.TypedValue;

import com.activeandroid.ActiveAndroid;
import com.mahao.xrzdemo.bean.User;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobUser;

/**
 * Created by mdw on 2015/11/12.
 */
public class MyApp extends Application {


    public static User user;
    /**
     * 城市Id
     */
    private int cityId = 101;

    private String cityName = "北京";

    private static MyApp myApp;

    private DisplayImageOptions options;

    private SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();

        myApp = this;

        sp = getSharedPreferences("config",MODE_PRIVATE);


        cityName = readBySp("cityName");
        if(TextUtils.isEmpty(readBySp("cityId"))||TextUtils.isEmpty(cityName)){
            cityId = 101;
            cityName = "北京";
        }else{
            cityId = Integer.parseInt(readBySp("cityId"));
        }


        Bmob.initialize(this, "401fd17e87ab16fede536a0d5cbc702d");

        BmobInstallation.getCurrentInstallation(this).save();

        user = BmobUser.getCurrentUser(this, User.class);

        options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.newbackground)
                .showImageOnFail(R.mipmap.newbackground)
                .cacheInMemory(true)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.ARGB_4444).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        ActiveAndroid.initialize(this);
    }

    public static MyApp getApp() {
        return myApp;
    }

    /**
     * 获取ImageLoader图片加载的配置文件
     *
     * @return
     */
    public DisplayImageOptions getDisplayImageOptions() {
        return options;
    }


    public int getCityId() {
        return cityId;
    }

    public static int dip2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getApp().getResources().getDisplayMetrics());
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;

    }


    public void write2sp(String key,String value){
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key,value);
        edit.commit();

    }

    public String readBySp(String key){
        return sp.getString(key,"");
    }
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
