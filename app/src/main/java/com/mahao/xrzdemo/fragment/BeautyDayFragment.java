package com.mahao.xrzdemo.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.mahao.xrzdemo.MyApp;
import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.adapter.BeautyDayAdapter;
import com.mahao.xrzdemo.bean.ExploreShop;
import com.mahao.xrzdemo.net.HttpAccess;
import com.mahao.xrzdemo.utils.DateUtils;
import com.mahao.xrzdemo.utils.PTRListViewUtil;
import com.mahao.xrzdemo.utils.T;

import java.util.ArrayList;
import java.util.List;

/**
 * 美天
 */
public class BeautyDayFragment extends BaseFragment  {


    private PullToRefreshListView lv;
    private List<ExploreShop> exploreShops;
    private BeautyDayAdapter adapter;
    private int page =1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HttpAccess.SUCCESS:
                    exploreShops.addAll((List<ExploreShop>) msg.obj);
                    adapter.notifyDataSetChanged();
                    lv.onRefreshComplete();

                    break;
                case HttpAccess.FAIL:
                    T.show(getActivity().getApplicationContext(),"获取数据失败");
                    break;


            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exploreShops = new ArrayList<>();
        adapter = new BeautyDayAdapter(exploreShops,R.layout.item_beauty_day,getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beauty_day, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        lv = (PullToRefreshListView) view.findViewById(R.id.lv_beauty_day);
        initListView();
        lv.setAdapter(adapter);
    }

    /**
     * 设置上拉加载和下拉刷新的加载样式
     */
    private void initListView() {
        PTRListViewUtil.initListView(lv);

        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新
                page = 1;
                exploreShops.clear();
                ILoadingLayout proxy = lv.getLoadingLayoutProxy(true, false);
                proxy.setLastUpdatedLabel("最后更新时间:"+ DateUtils.getDate());

                progress.show();
                HttpAccess.exploreShopePost(MyApp.getApp().getCityId(), page, handler);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //上拉加载
                page++;
                ILoadingLayout proxy = lv.getLoadingLayoutProxy(false, true);
                proxy.setLastUpdatedLabel("最后更新时间:"+ DateUtils.getDate());
                progress.show();
                HttpAccess.exploreShopePost(MyApp.getApp().getCityId(), page, handler);

            }
        });

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progress.show();
        HttpAccess.exploreShopePost(MyApp.getApp().getCityId(), page, handler);
    }


}
