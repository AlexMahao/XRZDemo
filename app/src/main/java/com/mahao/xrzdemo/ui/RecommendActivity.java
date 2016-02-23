package com.mahao.xrzdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.widget.TitleView;

/**
 * @author: Alex
 * Date: 2015-10-09
 * Time: 20:28
 */
public class RecommendActivity extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        initTitle();

        web = ((WebView) findViewById(R.id.webview));
        web.loadUrl("http://api.xiaorizi.me/app/html");
        web.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void initTitle() {
        TitleView title = (TitleView) findViewById(R.id.title);
        title.setRightListener(View.GONE, null, null);
        title.setLeftListener(TitleView.BACK_TILTE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
