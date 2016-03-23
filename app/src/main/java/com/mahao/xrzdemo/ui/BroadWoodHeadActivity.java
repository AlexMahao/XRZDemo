package com.mahao.xrzdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.widget.TitleView;

/**
 * Created by mdw on 2015/11/25.
 */
public class BroadWoodHeadActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_experience_head);
        webView = ((WebView) findViewById(R.id.webview_experience));
        webView.loadUrl(getIntent().getStringExtra("mobileURL"));
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

    }
}
