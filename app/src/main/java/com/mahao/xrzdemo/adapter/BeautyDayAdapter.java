package com.mahao.xrzdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.bean.Event;
import com.mahao.xrzdemo.bean.ExploreShop;
import com.mahao.xrzdemo.bean.Theme;
import com.mahao.xrzdemo.ui.EventDescActivity;
import com.mahao.xrzdemo.ui.ThemeDescActivity;
import com.mahao.xrzdemo.utils.DateUtils;

import java.util.List;

/**
 * Created by mdw on 2015/11/12.
 */
public class BeautyDayAdapter extends BaseAppAdapter<ExploreShop> {


    public BeautyDayAdapter(List<ExploreShop> list, int itemLayoutId,Context context) {
        super(list, itemLayoutId,context);
    }

    @Override
    public void bindData(ViewHolder viewHolder, ExploreShop entity, ViewGroup parent) {
        ((TextView) viewHolder.getView(R.id.beautyday_date_month)).setText(DateUtils.getMonth(entity.getDate()));
        ((TextView) viewHolder.getView(R.id.beautyday_date_day)).setText(DateUtils.getDay(entity.getDate()));
        ((TextView) viewHolder.getView(R.id.beautyday_title_tv)).setText(entity.getEvents().get(0).getFeeltitle());
        /**
         * 加载事件
         */
         final Event event = entity.getEvents().get(0);
        viewHolder.loadSimple(R.id.beautyday_event_bg, event.getImgs().get(0));
        ((TextView) viewHolder.getView(R.id.beautyday_event_title)).setText(event.getTitle());
        ((TextView) viewHolder.getView(R.id.beautyday_event_address)).setText(event.getAddress());

        viewHolder.getView(R.id.beautyday_event_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDescActivity.class);
                intent.putExtra("event", event);
               context.startActivity(intent);
            }
        });
        /**
         * 加载主题
         */
        if(entity.getThemes().size()==0){
            viewHolder.getView(R.id.beautyday_theme).setVisibility(View.GONE);
        }else{
            final Theme theme = entity.getThemes().get(0);
            ((TextView) viewHolder.getView(R.id.beautyday_theme_title)).setText(theme.getTitle());
            viewHolder.loadSimple(R.id.beautyday_theme_bg, theme.getImg());

            viewHolder.getView(R.id.beautyday_theme_bg).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ThemeDescActivity.class);
                    intent.putExtra("id",theme.getId()+"");
                    context.startActivity(intent);
                }
            });
        }
    }
}
