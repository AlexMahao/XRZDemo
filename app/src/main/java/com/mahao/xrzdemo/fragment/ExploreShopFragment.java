package com.mahao.xrzdemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.ui.CityListActivity;
import com.mahao.xrzdemo.utils.T;
import com.mahao.xrzdemo.widget.TitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 探店activity
 */
public class ExploreShopFragment extends BaseFragment {


    private List<Fragment> fragments;
    private ViewPager vp;
    private TitleView titleView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments = new ArrayList<>();
        fragments.add(new BeautyDayFragment());
        fragments.add(new ExploreBeautySpecialFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore_shop, container, false);
        initTitle(view);
        vp = (ViewPager) view.findViewById(R.id.exploreshop_vp);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyAdapter adapter = new MyAdapter(getChildFragmentManager());
        vp.setAdapter(adapter);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    titleView.moveUnderLine(false);
                } else if (position == 1) {
                    titleView.moveUnderLine(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化标题栏
     *
     * @param view
     */
    private void initTitle(View view) {
        titleView = (TitleView) view.findViewById(R.id.title);
        titleView.setLeftListener(TitleView.CITY_TITLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2Activity( CityListActivity.class);

            }
        });
        titleView.setRightListener(View.VISIBLE, "附近", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.show(getActivity(), "附近");
            }
        });
        titleView.setCenterNavigation();
    }


    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
