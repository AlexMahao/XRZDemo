package com.mahao.xrzdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mahao.xrzdemo.MyApp;
import com.mahao.xrzdemo.bean.Event;
import com.mahao.xrzdemo.ui.BroadWoodHeadActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2015/10/19.
 */
public class HeadAdapter extends PagerAdapter{

    private Context context;
    private List<Event> events;

    public HeadAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
    }

    public void addDate(List<Event> events){
        this.events.clear();
        this.events.addAll(events);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgView = new ImageView(context);
        imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageLoader.getInstance().displayImage(events.get((position % events.size())).getAdurl(), imgView, MyApp.getApp().getDisplayImageOptions());
        container.addView(imgView);
        final  int p = position;
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BroadWoodHeadActivity.class);
                intent.putExtra("mobileURL",events.get((p % events.size())).getMobileURL());
                context.startActivity(intent);
            }
        });
        return imgView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
