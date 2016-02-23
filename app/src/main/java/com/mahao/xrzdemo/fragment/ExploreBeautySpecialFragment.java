package com.mahao.xrzdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.adapter.ThemeListAdapter;
import com.mahao.xrzdemo.bean.Theme;
import com.mahao.xrzdemo.net.HttpAccess;
import com.mahao.xrzdemo.ui.ThemeDescActivity;
import com.mahao.xrzdemo.utils.DateUtils;
import com.mahao.xrzdemo.utils.PTRListViewUtil;

import java.util.ArrayList;
import java.util.List;



/**
 * @author: Alex
 * Date: 2015-09-28
 * Time: 21:05
 */
public class ExploreBeautySpecialFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private PullToRefreshListView lv;
    private List<Theme> themes;
    private ThemeListAdapter adapter;
    private int page  =1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HttpAccess.SUCCESS:

                    lv.onRefreshComplete();
                    themes.addAll((List<Theme>) msg.obj);
                    Log.i("info", "success"+themes.size());
                    adapter.notifyDataSetChanged();
                    break;
                case HttpAccess.FAIL:
                    lv.onRefreshComplete();
                    Toast.makeText(getActivity().getApplicationContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themes = new ArrayList<Theme>();
        adapter = new ThemeListAdapter(themes,R.layout.item_beauty_day,getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore_special,null);
        lv = (PullToRefreshListView) view.findViewById(R.id.explore_special_lv);
        initListView();
        return view;
    }

    private void initListView() {
        PTRListViewUtil.initListView(lv);

        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新
                page = 1;
                themes.clear();
                ILoadingLayout proxy = lv.getLoadingLayoutProxy(true, false);
                proxy.setLastUpdatedLabel("最后更新时间:" + DateUtils.getDate());

                progress.show();
                HttpAccess.getExploreSpecial(page, handler);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                progress.show();
                //上拉加载
                page++;
                ILoadingLayout proxy = lv.getLoadingLayoutProxy(false, true);
                proxy.setLastUpdatedLabel("最后更新时间:" + DateUtils.getDate());
                HttpAccess.getExploreSpecial(page, handler);

            }
        });
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progress.show();
        HttpAccess.getExploreSpecial(page, handler);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ThemeDescActivity.class);
        intent.putExtra("id", themes.get(position).getId()+"");
        getActivity().startActivity(intent);
    }
}
