package com.mahao.xrzdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mahao.xrzdemo.R;

/**
 * 自定义标题栏
 * <p/>
 * 默认情况， 左侧返回按钮，显示返回
 * 中侧标题
 * 右侧不显示
 * <p/>
 * <p/>
 * 1,左侧：返回按钮    自定义按钮 ， 没有按钮
 * 2，中： 标题栏  无
 * 3，右侧：自定义按钮 无
 * Created by mdw on 2016/3/23.
 */
public class TitleBar extends RelativeLayout {

    public static final int TYPE_GONE = 0;
    public static final int TYPE_CUSTOM_MENU = 1;
    public static final int TYPE_BACK = 2;


    private int mLeftType = TYPE_BACK;
    private String mLeftText = "返回";
    private Drawable mLeftImage ;

    private TextView mLeftTextView;
    private ImageView mLeftImageView;
    private LinearLayout mLeftLinearLayout;


    private String mCenterText ="标题";
    private int mCenterType= TYPE_CUSTOM_MENU;

    private TextView mCenterTextView;
    private LinearLayout mCenterLinearLayout;


    private String mRightText = "右";
    private int mRightType = TYPE_GONE;

    private TextView mRightTextView;
    private LinearLayout mRightLinearLayout;


    /**
     * 点击事件的监听回掉
     */
    private TitleBarClickListener mTitleBarClickListener;


    public TitleBar(Context context) {
        super(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.widget_titlebar, this);

        initView();


        initAttrs(context, attrs);

        loadView();

        initClick();
    }

    private void initClick() {
        mLeftLinearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleBarClickListener.onLeftClick();
            }
        });

        mCenterLinearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleBarClickListener.onCenterClick();
            }
        });

        mRightLinearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleBarClickListener.onRightClick();
            }
        });
    }

    public void addLeftView(View view){
        mLeftLinearLayout.removeAllViews();
        mLeftLinearLayout.addView(view);
    }

    public void addCenterView(View view){
        mCenterLinearLayout.removeAllViews();
        mCenterLinearLayout.addView(view);
    }


    public void addRightView(View view){
        mRightLinearLayout.removeAllViews();
        mRightLinearLayout.addView(view);
    }

    /**
     *
     * @param mTitleBarClickListener
     */
    public void setTitleBarClickListener(TitleBarClickListener mTitleBarClickListener) {
        this.mTitleBarClickListener = mTitleBarClickListener;
    }

    /**
     * 加载默认视图
     */
    private void loadView() {
        if(mLeftType==TYPE_BACK){
            mLeftText = "返回";
            mLeftTextView.setVisibility(View.VISIBLE);
            mLeftTextView.setText(mLeftText);

            mLeftImageView.setImageResource(R.mipmap.back_1);
            mLeftImageView.setVisibility(View.VISIBLE);

        }else if(mLeftType==TYPE_CUSTOM_MENU){
            if(mLeftImage==null){
                mLeftImageView.setVisibility(View.GONE);

            }else{
                mLeftImageView.setVisibility(View.VISIBLE);
                mLeftImageView.setImageDrawable(mLeftImage);
            }

            if(!TextUtils.isEmpty(mLeftText)){
                mLeftTextView.setVisibility(View.VISIBLE);
                mLeftTextView.setText(mLeftText);
            }else{
                mLeftTextView.setVisibility(View.GONE);
            }

        }else if(mLeftType==TYPE_GONE){
            mLeftTextView.setVisibility(View.GONE);
            mLeftImageView.setVisibility(View.GONE);
        }


        if(mCenterType == TYPE_CUSTOM_MENU){
            if(!TextUtils.isEmpty(mCenterText)){
                mCenterTextView.setVisibility(View.VISIBLE);
                mCenterTextView.setText(mCenterText);
            }else{
                mCenterTextView.setVisibility(View.GONE);
            }
        }else if(mCenterType==TYPE_GONE){
            mCenterTextView.setVisibility(View.GONE);
        }


        if(mRightType == TYPE_CUSTOM_MENU){
            if(!TextUtils.isEmpty(mRightText)){
                mRightTextView.setVisibility(View.VISIBLE);
                mRightTextView.setText(mRightText);
            }else{
                mRightTextView.setVisibility(View.GONE);
            }
        }else if(mRightType==TYPE_GONE){
            mRightTextView.setVisibility(View.GONE);
        }

    }




    /**
     * 查找控件id
     */
    private void initView() {
        mLeftLinearLayout = (LinearLayout) findViewById(R.id.ll_titlebar_left);
        mLeftImageView = (ImageView) findViewById(R.id.iv_titlebar_left);
        mLeftTextView = (TextView) findViewById(R.id.tv_titlebar_left);

        mCenterTextView = (TextView) findViewById(R.id.tv_titlebar_center);
        mCenterLinearLayout = (LinearLayout) findViewById(R.id.ll_titlebar_center);

        mRightTextView = (TextView) findViewById(R.id.tv_titlebar_right);
        mRightLinearLayout = (LinearLayout) findViewById(R.id.ll_titlebar_right);
    }

    /**
     * 获取自定义属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);

            mLeftText = ta.getString(R.styleable.TitleBar_leftText);

            mLeftType = ta.getInteger(R.styleable.TitleBar_leftType,TYPE_BACK);

            mLeftImage = ta.getDrawable(R.styleable.TitleBar_leftImage);

            mCenterType = ta.getInteger(R.styleable.TitleBar_centerType,TYPE_CUSTOM_MENU);
            mCenterText = ta.getString(R.styleable.TitleBar_centerText);

            mRightType = ta.getInteger(R.styleable.TitleBar_rightType,TYPE_GONE);
            mRightText = ta.getString(R.styleable.TitleBar_rightText);

        }
    }


    /**
     * 监听回调事件
     */
    public static  abstract class TitleBarClickListener{
        public  abstract  void onLeftClick();
        public void onCenterClick(){}

        public void onRightClick(){}
    }
}
