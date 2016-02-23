package com.mahao.xrzdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahao.xrzdemo.MyApp;
import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.bean.Event;
import com.mahao.xrzdemo.ui.BroadWoodContentActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2015/10/19.
 */
public class ContentAdapter extends BaseAdapter {
    private Context context;
    private List<Event> content;
    private int isHasHead = 0;
    private View headView;
    private static final int tag = 2;

    public ContentAdapter(Context context, List<Event> content, int isHasHead, View headView) {
        this.context = context;
        this.content = content;
        this.isHasHead = isHasHead;
        this.headView = headView;
    }

    @Override
    public int getCount() {
        return isHasHead + content.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (position == isHasHead-1) {

            return headView;
        }

        ViewHold viewHold;
        if (convertView == null || convertView.getTag(R.string.hello_blank_fragment)==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_broadwood, null);
            viewHold = new ViewHold(convertView);
            convertView.setTag(R.string.hello_blank_fragment,viewHold);
        } else {

            viewHold = (ViewHold) convertView.getTag(R.string.hello_blank_fragment);

        }

        ImageLoader.getInstance().displayImage(content.get(position - isHasHead).getImgs().get(0), viewHold.img, MyApp.getApp().getDisplayImageOptions());
        viewHold.tv.setText(content.get(position - isHasHead).getTitle());
         final int position1 = position;
        viewHold.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BroadWoodContentActivity.class);

                intent.putExtra("eid",content.get(position1 - isHasHead).getEid());
                context.startActivity(intent);
            }
        });


        return convertView;

    }

    class ViewHold {
        public TextView tv;
        public ImageView img;

        public ViewHold(View view) {
            tv = ((TextView) view.findViewById(R.id.tv_item_broad));
            img = ((ImageView) view.findViewById(R.id.iv_item_broad));
        }
    }


    public void addDate(List<Event> events,boolean isClear){
        if(isClear){
            content.clear();
            content.addAll(events);
            notifyDataSetChanged();
        }else{
            content.addAll(events);
            notifyDataSetChanged();
        }
    }
}
