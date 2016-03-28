package com.mahao.xrzdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.mahao.xrzdemo.R;

/**
 *
 * Created by mdw on 2016/3/28.
 */
public class ShareView extends RelativeLayout{


    public ShareView(Context context) {
        super(context);
    }

    public ShareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.widget_share,this);
    }
}
