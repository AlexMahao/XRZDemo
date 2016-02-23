package com.mahao.xrzdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mahao.xrzdemo.MyApp;

import java.util.List;

/**
 * 基本适配器
 * Created by ChenLong -Y on 2015/9/2.
 */
public abstract class BaseAppAdapter<T> extends BaseAdapter {

    public  Context context;
    public List<T> list;
    private int itemLayoutId;

    public BaseAppAdapter(List<T> list, int itemLayoutId,Context context) {
        this.list = list;
        this.itemLayoutId = itemLayoutId;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list != null && !list.isEmpty() ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(MyApp.getApp()).inflate(itemLayoutId, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        bindData(viewHolder, list.get(position), parent);

        return convertView;
    }

    public abstract void bindData(ViewHolder viewHolder, T entity, ViewGroup parent);

}
