package com.mahao.xrzdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahao.xrzdemo.MyApp;
import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.bean.Classifi.TagsEntity;
import com.mahao.xrzdemo.ui.ClassDesActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by mdw on 2015/11/29.
 */
public class ClassAdapter extends BaseAppAdapter<TagsEntity> {


    public ClassAdapter(List<TagsEntity> list, int itemLayoutId, Context context) {
        super(list, itemLayoutId, context);
    }

    @Override
    public void bindData(ViewHolder viewHolder, final TagsEntity entity, ViewGroup parent) {
        ((TextView) viewHolder.getView(R.id.gv_tv)).setText(entity.getName());
        ImageLoader.getInstance().displayImage(entity.getImg(), ((ImageView) viewHolder.getView(R.id.gv_img)), MyApp.getApp().getDisplayImageOptions());
        ((ImageView) viewHolder.getView(R.id.gv_img)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ClassDesActivity.class);
                intent.putExtra("id",entity.getId()+"");
                context.startActivity(intent);
            }
        });

    }
}
