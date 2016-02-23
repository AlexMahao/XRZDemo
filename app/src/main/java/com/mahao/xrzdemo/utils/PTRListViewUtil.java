package com.mahao.xrzdemo.utils;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * 下拉刷新和上拉加载的初始化工具类
 */
public class PTRListViewUtil {


    public static  void initListView(PullToRefreshListView lv){
        //设置上拉加载和下拉刷新的样式
        lv.setMode(PullToRefreshBase.Mode.BOTH);

        //下拉刷新
        ILoadingLayout proxy = lv.getLoadingLayoutProxy(true, false);

        proxy.setPullLabel("下拉刷新...");
        proxy.setReleaseLabel("放开刷新...");
        proxy.setRefreshingLabel("正在努力的刷新中...");
        proxy.setLastUpdatedLabel("最新更新时间：MM月dd日 hh:mm:ss");

        //上拉加载
        ILoadingLayout Upproxy = lv.getLoadingLayoutProxy(false, true);

        Upproxy.setPullLabel("上拉加载...");
        Upproxy.setReleaseLabel("放开加载...");
        Upproxy.setRefreshingLabel("正在努力的加载更多中...");
        Upproxy.setLastUpdatedLabel("最新加载时间：MM月dd日 hh:mm:ss");
    }




}
