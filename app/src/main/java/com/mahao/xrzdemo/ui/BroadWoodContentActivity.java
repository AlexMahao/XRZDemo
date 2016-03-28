package com.mahao.xrzdemo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.bean.Event;
import com.mahao.xrzdemo.net.HttpAccess;
import com.mahao.xrzdemo.utils.ShareUtils;
import com.mahao.xrzdemo.utils.WebParser;
import com.mahao.xrzdemo.widget.TitleBar;

/**
 * Created by mdw on 2015/11/25.
 */
public class BroadWoodContentActivity extends BaseActivity {

    private LinearLayout webView;

    private int eid;

    private Event event;

    private ShareUtils shareUtils;


    private Handler hanler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case HttpAccess.SUCCESS:
                    event = (Event) msg.obj;
                   // Log.i("info",event.getMobileURL());
                    shareUtils = new ShareUtils(BroadWoodContentActivity.this,event,null);
                    WebParser.parserWeb(webView, BroadWoodContentActivity.this, event.getMobileURL());
                    break;
                case HttpAccess.FAIL:
                    Toast.makeText(getApplicationContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_experience_content);
        initTitleView();
        eid = getIntent().getIntExtra("eid",0);
        webView = (LinearLayout) findViewById(R.id.webview_experience);
        HttpAccess.getExperienceEvent(hanler,eid);

    }



    /**
     * 初始化标题
     */
    private void initTitleView() {
        ((TitleBar) findViewById(R.id.titleBar)).setTitleBarClickListener(new TitleBar.TitleBarClickListener() {
            @Override
            public void onLeftClick() {
                onBackPressed();
            }

            @Override
            public void onRightClick() {
               if(shareUtils!=null){
                   shareUtils.share();
               }
            }
        });

    }

}


