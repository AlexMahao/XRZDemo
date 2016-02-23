package com.mahao.xrzdemo.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahao.xrzdemo.MyApp;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by ChenLong -Y on 2015/9/2.
 */
public class ViewHolder {



    private Map<Integer, View> viewMap;
    private View itemView;

    public ViewHolder(View itemView) {
        this.itemView = itemView;
        viewMap = new HashMap<>();
    }

    public View getView(int id) {
        View view = viewMap.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            viewMap.put(id, view);
        }
        return view;
    }

    public void setText(int id,String data){
        if (data ==null)
            data = "";
        ((TextView)getView(id)).setText(data);
    }


    public void loadSimple(int id,String imgUrl){
        View view = getView(id);
        if(view instanceof ImageView){
            ImageLoader.getInstance().displayImage(imgUrl, ((ImageView) view),MyApp.getApp().getDisplayImageOptions());
        }


    }


    public void loadSimple(ImageView imgView,String imgUrl){
        ImageLoader.getInstance().displayImage(imgUrl, imgView,MyApp.getApp().getDisplayImageOptions());
    }
}
