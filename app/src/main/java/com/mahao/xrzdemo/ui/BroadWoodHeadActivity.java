package com.mahao.xrzdemo.ui;

import android.os.Bundle;
import android.webkit.WebView;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.utils.ShareUtils;
import com.mahao.xrzdemo.widget.TitleBar;

/**
 * Created by mdw on 2015/11/25.
 */
public class BroadWoodHeadActivity extends BaseActivity {

    private WebView webView;

    private ShareUtils shareUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_experience_head);
        webView = ((WebView) findViewById(R.id.webview_experience));
        webView.loadUrl(getIntent().getStringExtra("mobileURL"));

        initTitleView();
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
                //T.show(getApplicationContext(),"分享");
            }
        });

    }
}
