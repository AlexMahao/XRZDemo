package com.mahao.xrzdemo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.fragment.BroadWoodFragment;
import com.mahao.xrzdemo.fragment.ClassificitionFragment;
import com.mahao.xrzdemo.fragment.ExploreShopFragment;
import com.mahao.xrzdemo.fragment.MoreFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    private ArrayList<Fragment> fragments;
    private RadioGroup rgMenu;
    private FragmentManager fragmentMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_main);
        fragmentMgr = getSupportFragmentManager();
        rgMenu = (RadioGroup) findViewById(R.id.rg_main_menu);
        initFragment();
        rgMenu.setOnCheckedChangeListener(this);
        ((RadioButton) rgMenu.getChildAt(0)).setChecked(true);

    }

    /**
     * 初始化碎片
     */
    private void initFragment() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new ExploreShopFragment());
        fragments.add(new BroadWoodFragment());
        fragments.add(new ClassificitionFragment());
        fragments.add(new MoreFragment());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for(int i=0;i<group.getChildCount();i++){
            if (group.getChildAt(i).getId()==checkedId) {

                if (!fragments.get(i).isAdded()) {
                    fragmentMgr.beginTransaction().add(R.id.fragment_main,fragments.get(i)).commit();
                }
                selectFragment(i);
            }
        }
    }

    /**
     * 切换碎片
     * @param position
     */
    private void selectFragment(int position) {
        for(int i=0;i<fragments.size();i++){
            if(i==position){
                fragmentMgr.beginTransaction().show(fragments.get(i)).commit();
            }else{
                fragmentMgr.beginTransaction().hide(fragments.get(i)).commit();
            }
        }
    }
}
