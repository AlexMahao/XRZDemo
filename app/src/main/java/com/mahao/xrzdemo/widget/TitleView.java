package com.mahao.xrzdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mahao.xrzdemo.R;

/**
 * 自定义标题栏
 * Created by mdw on 2015/11/11.
 */
public class TitleView extends LinearLayout {

    public static final int CITY_TITLE = 1;
    public static final int BACK_TILTE = 2;
    public static final int ALL_HIDE_TITLE = 3;

    private LinearLayout ll_left;
    private RelativeLayout rl_left;
    private ImageView left_back;
    private RelativeLayout rl_center;
    private TextView right_tv;
    private View underline;
    private TranslateAnimation startAnim;
    private TranslateAnimation endAnim;
    private boolean isFirst;


    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.widget_title, this, true);
        initView();

    }

    /**
     * 初始化下划线的动画
     */
    private void initAnimation() {
        startAnim = new TranslateAnimation(0,underline.getWidth(),0,0);
        startAnim.setDuration(500);
        startAnim.setFillAfter(true);
        endAnim = new TranslateAnimation(underline.getWidth(),0,0,0);
        endAnim.setDuration(500);
        startAnim.setFillAfter(true);
    }

    /**
     * 查找控件
     */
    private void initView() {
        ll_left = ((LinearLayout) findViewById(R.id.title_ll_left));
        rl_left = ((RelativeLayout) findViewById(R.id.rl_left_title));
        left_back = ((ImageView) findViewById(R.id.title_left_img));
        rl_center = ((RelativeLayout) findViewById(R.id.title_rl_center));
        right_tv = ((TextView) findViewById(R.id.title_tv_right_));
    }

    /**
     * 设置左边监听
     * @param type  显示城市还是返回图标
     * @param l   借口回调
     */
    public void setLeftListener(int type,OnClickListener l){
        if(type==CITY_TITLE){
            ll_left.setVisibility(View.VISIBLE);
            left_back.setVisibility(View.GONE);
            rl_left.setOnClickListener(l);
        }else if(type==BACK_TILTE){
            ll_left.setVisibility(View.GONE);
            left_back.setVisibility(View.VISIBLE);
            rl_left.setOnClickListener(l);
        }else if(ALL_HIDE_TITLE==type){
            ll_left.setVisibility(View.GONE);
            left_back.setVisibility(View.GONE);
        }



    }

    /**
     * 设置中心显示的样式
     * @param view
     */
    public void setCenterView(View view){

        rl_center.removeAllViews();
        rl_center.addView(view);
    }


    public void setCenterNavigation(){
        RelativeLayout navigationRl = (RelativeLayout) rl_center.getChildAt(0);
        if(navigationRl!=null){
            navigationRl.setVisibility(VISIBLE);
            underline = navigationRl.findViewById(R.id.underLine);

        }
    }

    /**
     * 移动导航栏
     * @param isStart
     */
    public void moveUnderLine(boolean isStart){

        if(underline==null){
            return;
        }
        if (!isFirst) {
            initAnimation();
            isFirst = true;
        }
        initAnimation();

        if(isStart){
            //导航栏向左移动

            underline.startAnimation(startAnim);
        }else{
            underline.startAnimation(endAnim);
        }
    }




    /**
     * 设置右边的布局
     * @param visibity
     * @param l
     */
    public void setRightListener(int visibity,String tag, OnClickListener l){


        right_tv.setVisibility(visibity);
        if(visibity==VISIBLE) {
            right_tv.setText(tag);
            right_tv.setOnClickListener(l);
        }
    }


    /**
     * 添加右边的自定义试图
     * @param ll
     */
    public void addRightListener(View ll){
        RelativeLayout right_rl = (RelativeLayout) findViewById(R.id.right_rl);
        right_rl.removeAllViews();
        RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(params);
        if(ll!=null)
        right_rl.addView(ll);
    }



}
