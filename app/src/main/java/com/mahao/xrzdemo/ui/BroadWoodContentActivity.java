package com.mahao.xrzdemo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.bean.Event;
import com.mahao.xrzdemo.net.HttpAccess;
import com.mahao.xrzdemo.utils.T;
import com.mahao.xrzdemo.utils.WebParser;
import com.mahao.xrzdemo.widget.TitleView;

/**
 * Created by mdw on 2015/11/25.
 */
public class BroadWoodContentActivity extends AppCompatActivity {

    private LinearLayout webView;

    private int eid;

    private Event event;


    private Handler hanler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case HttpAccess.SUCCESS:
                    event = (Event) msg.obj;
                    Log.i("info",event.getMobileURL());
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
        eid = getIntent().getIntExtra("eid",0);
        webView = (LinearLayout) findViewById(R.id.webview_experience);
        HttpAccess.getExperienceEvent(hanler,eid);

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

        titleView.setRightListener(View.VISIBLE, "分享", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.show(getApplicationContext(), "fenxiang");
            }
        });


    }

}


