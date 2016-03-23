package com.mahao.xrzdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.fragment.ThemeListFragment;
import com.mahao.xrzdemo.fragment.ThemeWebFragment;
import com.mahao.xrzdemo.widget.TitleView;


/**
 * 主题详情展示页面
 * @author: Alex
 * Date: 2015-10-10
 * Time: 10:31
 */
public class ThemeDescActivity extends BaseActivity {

    private String id;
    private ImageView menu;
    private boolean isDetail = true;
    private ThemeWebFragment webF;
    private ThemeListFragment listF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        id= getIntent().getStringExtra("id");
        initTitle();
        initFragment();
        initMenu();
    }

    //初始化碎片
    private void initFragment() {
        webF= ThemeWebFragment.newInstance(id);
        listF = ThemeListFragment.newInstance(id);
        getSupportFragmentManager().beginTransaction().add(R.id.theme_fragment,webF).add(R.id.theme_fragment,listF).commit();
        selectFragment(isDetail);
    }

    //跳转碎片
    private void selectFragment(boolean isDetail) {
        if(isDetail){
            //跳转web
            getSupportFragmentManager().beginTransaction().show(webF).hide(listF).commit();
        }else{
            getSupportFragmentManager().beginTransaction().show(listF).hide(webF).commit();

        }
    }

    private void initMenu() {
        menu = (ImageView) findViewById(R.id.theme_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDetail){
                    menu.setImageResource(R.mipmap.web_mj);
                    isDetail=false;
                }else{
                    menu.setImageResource(R.mipmap.list_mj);
                    isDetail=true;
                }

                selectFragment(isDetail);
            }
        });

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
