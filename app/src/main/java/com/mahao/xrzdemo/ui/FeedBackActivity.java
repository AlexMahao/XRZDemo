package com.mahao.xrzdemo.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mahao.xrzdemo.MyApp;
import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.bean.FeedBack;
import com.mahao.xrzdemo.utils.T;
import com.mahao.xrzdemo.widget.TitleBar;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by mdw on 2016/3/28.
 */
public class FeedBackActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_content;
    private Button btn_commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ((TitleBar) findViewById(R.id.titleBar)).setTitleBarClickListener(new TitleBar.TitleBarClickListener() {
            @Override
            public void onLeftClick() {
                onBackPressed();
            }
        });

        et_content = ((EditText) findViewById(R.id.et_content));
        btn_commit = ((Button) findViewById(R.id.btn_commit));

        btn_commit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String content = et_content.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            T.show(getApplicationContext(),"不能为空哦");
        }else{
            FeedBack feedBack = new FeedBack();
            feedBack.setContent(content);
            feedBack.setPhone(MyApp.user.getMobilePhoneNumber());
            feedBack.save(this, new SaveListener() {
                @Override
                public void onSuccess() {
                    T.show(getApplicationContext(),"吐槽成功");
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    T.show(getApplicationContext(),s);
                }
            });
        }
    }
}
