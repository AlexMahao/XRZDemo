package com.mahao.xrzdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.fragment.ThemeListFragment;
import com.mahao.xrzdemo.widget.TitleView;

/**
 * Created by mdw on 2015/11/29.
 */
public class ClassDesActivity extends BaseActivity {
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("id");
        setContentView(R.layout.activity_class_des);
        initTitle();
        initFragment();

    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_class_des,ThemeListFragment.newInstance(id)).commit();
    }

    private void initTitle() {
        TitleView title = (TitleView) findViewById(R.id.title);
        title.setLeftListener(TitleView.BACK_TILTE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        title.setRightListener(View.GONE,null,null);
    }
}
