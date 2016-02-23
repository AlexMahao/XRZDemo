package com.mahao.xrzdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.bean.Event;
import com.mahao.xrzdemo.ui.EventDescActivity;

import java.util.List;

/**
 * Created by mdw on 2015/11/19.
 */
public class EventListAdapter extends BaseAppAdapter<Event> {
    public EventListAdapter(List<Event> list, int itemLayoutId, Context context) {
        super(list, itemLayoutId, context);
    }

    @Override
    public void bindData(ViewHolder viewHolder, final Event entity, ViewGroup parent) {
        viewHolder.getView(R.id.beautyday_title).setVisibility(View.GONE);

        viewHolder.getView(R.id.beautyday_theme).setVisibility(View.GONE);

        viewHolder.loadSimple(R.id.beautyday_event_bg, entity.getImgs().get(0));
        ((TextView) viewHolder.getView(R.id.beautyday_event_title)).setText(entity.getTitle());
        ((TextView) viewHolder.getView(R.id.beautyday_event_address)).setText(entity.getAddress());

        viewHolder.getView(R.id.beautyday_event_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDescActivity.class);
                intent.putExtra("event", entity);
                context.startActivity(intent);
            }
        });

    }
}
