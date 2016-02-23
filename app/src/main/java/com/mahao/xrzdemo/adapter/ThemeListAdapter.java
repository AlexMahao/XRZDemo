package com.mahao.xrzdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.bean.Theme;

import java.util.List;

/**
 * Created by mdw on 2015/11/19.
 */
public class ThemeListAdapter extends BaseAppAdapter<Theme> {

    public ThemeListAdapter(List<Theme> list, int itemLayoutId, Context context) {
        super(list, itemLayoutId, context);
    }


    @Override
    public void bindData(ViewHolder viewHolder, Theme entity, ViewGroup parent) {
        viewHolder.getView(R.id.beautyday_title).setVisibility(View.GONE);
        viewHolder.getView(R.id.beautyday_event).setVisibility(View.GONE);

        ((TextView) viewHolder.getView(R.id.beautyday_theme_title)).setText(entity.getTitle());
        viewHolder.loadSimple(R.id.beautyday_theme_bg, entity.getImg());
    }
}
