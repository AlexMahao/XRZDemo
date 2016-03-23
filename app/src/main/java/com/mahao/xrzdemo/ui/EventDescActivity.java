package com.mahao.xrzdemo.ui;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
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
import com.mahao.xrzdemo.widget.OrderView;
import com.mahao.xrzdemo.widget.TitleView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;


public class EventDescActivity extends BaseActivity implements View.OnClickListener {


    final UMSocialService mController = UMServiceFactory
            .getUMSocialService("com.umeng.share");


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_desc);
        fMgr = getSupportFragmentManager();
        event = getIntent().getParcelableExtra("event");

        isCollect = EventDAO.getInstance(this).isExsit(event.getId());
        initTitleView();
        initView();
        initFragment();
        initAnimation();

        initShare();
        setShareContent();

    }

    private void initShare() {
        addWXPlatform();
        addQQQZonePlatform();
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
                mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE);
                mController.openShare(EventDescActivity.this, false);

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

        toRight = ObjectAnimator.ofFloat(underLine, "translationX", 0.0f, 350.0f).setDuration(500);
        toLeft = ObjectAnimator.ofFloat(underLine, "translationX", 350.0f, 0.0f).setDuration(500);
        toRightCopy = ObjectAnimator.ofFloat(underLineCopy, "translationX", 0.0f, 350.0f).setDuration(500);
        toLeftCopy = ObjectAnimator.ofFloat(underLineCopy, "translationX", 350.0f, 0.0f).setDuration(500);
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
        underLineCopy = findViewById(R.id.underLine_copy);
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



    /**
     * @功能描述 : 添加微信平台分享
     * @return
     */
    private void addWXPlatform() {
        // 注意：在微信授权的时候，必须传递appSecret
        // wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
        String appId = "wx967daebe835fbeac";
        String appSecret = "5bb696d9ccd75a38c8a0bfe0675559b3";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(this, appId, appSecret);
        wxHandler.addToSocialSDK();

        // 支持微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(this, appId, appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
    }

    /**
     * @功能描述 : 添加QQ平台支持 QQ分享的内容， 包含四种类型， 即单纯的文字、图片、音乐、视频. 参数说明 : title, summary,
     *       image url中必须至少设置一个, targetUrl必须设置,网页地址必须以"http://"开头 . title :
     *       要分享标题 summary : 要分享的文字概述 image url : 图片地址 [以上三个参数至少填写一个] targetUrl
     *       : 用户点击该分享时跳转到的目标地址 [必填] ( 若不填写则默认设置为友盟主页 )
     * @return
     */
    private void addQQQZonePlatform() {
        String appId = "100424468";
        String appKey = "c7394704798a158208a74ab60104f0ba";
        // 添加QQ支持, 并且设置QQ分享内容的target url
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this,
                appId, appKey);
        qqSsoHandler.setTargetUrl(event.getShareURL());
        qqSsoHandler.addToSocialSDK();

        // 添加QZone平台
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this, appId, appKey);
        qZoneSsoHandler.addToSocialSDK();
    }

    /**
     * 根据不同的平台设置不同的分享内容</br>
     */
    private void setShareContent() {

        mController.setShareContent(event.getDetail());


        UMImage urlImage = new UMImage(this,event.getImgs().get(0)
                );
        // UMImage resImage = new UMImage(getActivity(), R.drawable.icon);


        // UMEmoji emoji = new UMEmoji(getActivity(),
        // "http://www.pc6.com/uploadimages/2010214917283624.gif");
        // UMEmoji emoji = new UMEmoji(getActivity(),
        // "/storage/sdcard0/emoji.gif");

        WeiXinShareContent weixinContent = new WeiXinShareContent();
        weixinContent
                .setShareContent(event.getDetail());
        weixinContent.setTitle(event.getTitle());
        weixinContent.setTargetUrl(event.getShareURL());
        weixinContent.setShareMedia(urlImage);
        mController.setShareMedia(weixinContent);

        // 设置朋友圈分享的内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia
                .setShareContent(event.getDetail());
        circleMedia.setTitle(event.getTitle());
        circleMedia.setShareMedia(urlImage);
        // circleMedia.setShareMedia(uMusic);
        // circleMedia.setShareMedia(video);
        circleMedia.setTargetUrl(event.getShareURL());
        mController.setShareMedia(circleMedia);

        UMImage qzoneImage = new UMImage(this,
                event.getImgs().get(0));
        qzoneImage
                .setTargetUrl(event.getShareURL());

        // 设置QQ空间分享内容
        QZoneShareContent qzone = new QZoneShareContent();
        qzone.setShareContent(event.getDetail());
        qzone.setTargetUrl(event.getShareURL());
        qzone.setTitle(event.getTitle());
        qzone.setShareMedia(urlImage);
        // qzone.setShareMedia(uMusic);
        mController.setShareMedia(qzone);


        QQShareContent qqShareContent = new QQShareContent();
        qqShareContent.setShareContent(event.getDetail());
        qqShareContent.setTitle(event.getTitle());
        qqShareContent.setShareMedia(urlImage);
        qqShareContent.setTargetUrl(event.getShareURL());
        mController.setShareMedia(qqShareContent);



    }
}
