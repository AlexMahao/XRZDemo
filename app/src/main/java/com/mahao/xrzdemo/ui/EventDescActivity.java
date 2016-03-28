package com.mahao.xrzdemo.ui;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.bean.Event;
import com.mahao.xrzdemo.db.EventDAO;
import com.mahao.xrzdemo.fragment.EventDescDescFragment;
import com.mahao.xrzdemo.fragment.EventDescDiscFragment;
import com.mahao.xrzdemo.utils.ShareUtils;
import com.mahao.xrzdemo.widget.OrderView;
import com.mahao.xrzdemo.widget.TitleView;
import com.nostra13.universalimageloader.core.ImageLoader;


public class EventDescActivity extends BaseActivity implements View.OnClickListener, ShareUtils.OnShareListener {



    private ImageView titleImg;
    private TitleView title;
    private TextView discoverTv, descTv;
    private FragmentManager fMgr;
    private EventDescDescFragment descF;
    private EventDescDiscFragment discF;
    private Event event;
    private View underLine, underLineCopy;
    private TranslateAnimation toLeftAnimation, toRightAnimation;
    private int index;
    private OrderView orderView;
    private LinearLayout eventTitle, eventTitleCopy;
    public ScrollView scrollView;
    private ObjectAnimator toRight;
    private ObjectAnimator toLeft;
    private ObjectAnimator toRightCopy;
    private ObjectAnimator toLeftCopy;
    private TextView collect;
    private boolean isCollect;
    private TextView share;

    private ShareUtils shareUtils;


    private int moveX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_desc);
        fMgr = getSupportFragmentManager();
        event = getIntent().getParcelableExtra("event");

        isCollect = EventDAO.getInstance(this).isExsit(event.getId());


        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        moveX= outMetrics.widthPixels/2;

        initTitleView();
        initView();
        initFragment();
        initAnimation();

        shareUtils = new ShareUtils(this,event,this);
    }




    /**
     * 初始化标题
     */
    private void initTitleView() {

        TitleView titleView = (TitleView) findViewById(R.id.title);
        titleView.setLeftListener(TitleView.BACK_TILTE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        View rightView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.widget_title_right, null);
        collect = ((TextView) rightView.findViewById(R.id.collect));
        if (isCollect) {
            collect.setText("已收藏");
        }else{
            collect.setText("收藏");
        }
        share = ((TextView) rightView.findViewById(R.id.share));

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              shareUtils.share();

            }
        });
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCollect) {
                    EventDAO.getInstance(EventDescActivity.this).delete(event.getId());
                    Toast.makeText(getApplicationContext(), "已取消收藏", Toast.LENGTH_SHORT).show();
                    collect.setText("收藏");

                } else {
                    EventDAO.getInstance(EventDescActivity.this).insert(JSON.toJSONString(event), event.getId());
                    collect.setText("已收藏");
                    Toast.makeText(getApplicationContext(), "收藏成功", Toast.LENGTH_SHORT).show();
                }
                isCollect = !isCollect;
            }
        });

        titleView.addRightListener(rightView);
    }

    private void initAnimation() {
        final float scale = getResources().getDisplayMetrics().density;
//        toRightAnimation = new TranslateAnimation(0,350,0,0);
//        toRightAnimation.setDuration(500);
//        toRightAnimation.setFillAfter(true);

        toRight = ObjectAnimator.ofFloat(underLine, "translationX", 0.0f, moveX).setDuration(500);
        toLeft = ObjectAnimator.ofFloat(underLine, "translationX", moveX, 0.0f).setDuration(500);
        toRightCopy = ObjectAnimator.ofFloat(underLineCopy, "translationX", 0.0f, moveX).setDuration(500);
        toLeftCopy = ObjectAnimator.ofFloat(underLineCopy, "translationX", moveX, 0.0f).setDuration(500);
//        toLeftAnimation = new TranslateAnimation(350,0,0,0);
//        toLeftAnimation.setDuration(500);
//        toLeftAnimation.setFillAfter(true);

    }


    private void initView() {
        discoverTv = (TextView) findViewById(R.id.event_desc_discover);
        descTv = (TextView) findViewById(R.id.event_desc_desc);
        titleImg = (ImageView) findViewById(R.id.event_desc_image);
        ImageLoader.getInstance().displayImage(event.getImgs().get(0), titleImg);

        eventTitle = (LinearLayout) findViewById(R.id.event_desc_title);
        eventTitleCopy = (LinearLayout) findViewById(R.id.event_desc_title_copy);

        scrollView = (ScrollView) findViewById(R.id.event_desc_scrollview);
        //获取自定义浮动view
        orderView = (OrderView) findViewById(R.id.orderview);
        orderView.setStayView(eventTitle, scrollView, new OrderView.StayViewListener() {
            @Override
            public void onStayViewShow() {
                //显示复制标题
                eventTitleCopy.setVisibility(View.VISIBLE);
                /*int left = 0;
                if (index == 1) {
                    left = 100;
                } else if (index == 2) {
                    left = 450;
                }

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) underLineCopy.getLayoutParams();
                params.setMargins(left, params.topMargin, params.rightMargin, params.rightMargin);
                underLineCopy.setLayoutParams(params);*/
                underLineCopy.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStayViewGone() {
                //隐藏复制标题
                eventTitleCopy.setVisibility(View.GONE);
               /* *//*underLineCopy.clearAnimation(); //Gone属性生*//*效必须对应view无动画*/
                underLineCopy.setVisibility(View.INVISIBLE);
            }
        });

        for (int i = 0; i < eventTitle.getChildCount(); i++) {
            if (eventTitle.getChildAt(i) instanceof TextView) {
                eventTitle.getChildAt(i).setOnClickListener(this);
                eventTitleCopy.getChildAt(i).setOnClickListener(this);

            }
        }

        underLine = findViewById(R.id.underLines);
        ViewGroup.LayoutParams layoutParams =
                underLine.getLayoutParams();
        layoutParams.width = moveX;
        underLine.setLayoutParams(layoutParams);
        underLineCopy = findViewById(R.id.underLine_copy);
        ViewGroup.LayoutParams layoutParams1 =
                underLineCopy.getLayoutParams();
        layoutParams1.width = moveX;
        underLineCopy.setLayoutParams(layoutParams1);

    }

    private void initFragment() {
        descF = EventDescDescFragment.newInstance(event);
        discF = EventDescDiscFragment.newInstance(event);
        fMgr.beginTransaction().add(R.id.event_desc_fragment, descF).commit();
        fMgr.beginTransaction().add(R.id.event_desc_fragment, discF).commit();
        fMgr.beginTransaction().hide(descF).commit();
    }


    public void fragmentChange(int position) {
        if (index == position) {
            return;
        }
        if (position == 1) {
            fMgr.beginTransaction().show(discF).hide(descF).commit();
            scrollView.scrollTo(0, 0);
            /*underLine.startAnimation(toLeftAnimation);
            if(underLineCopy.getVisibility()==View.VISIBLE)
                underLineCopy.startAnimation(toLeftAnimation);*/
            toLeft.start();
            toLeftCopy.start();
        }

        if (position == 2) {
            fMgr.beginTransaction().show(descF).hide(discF).commit();
           /* underLine.startAnimation(toRightAnimation);
            if(underLineCopy.getVisibility()==View.VISIBLE)
                underLineCopy.startAnimation(toRightAnimation);*/
            toRight.start();
            toRightCopy.start();

        }
        index = position;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.event_desc_desc:
                fragmentChange(2);
                break;
            case R.id.event_desc_discover:
                fragmentChange(1);
                break;
        }
    }


    @Override
    public void onResponse(boolean isShare) {

    }
}
