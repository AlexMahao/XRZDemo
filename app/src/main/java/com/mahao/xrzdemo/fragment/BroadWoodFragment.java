package com.mahao.xrzdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.adapter.ContentAdapter;
import com.mahao.xrzdemo.adapter.HeadAdapter;
import com.mahao.xrzdemo.bean.BroadWood;
import com.mahao.xrzdemo.bean.Event;
import com.mahao.xrzdemo.net.HttpAccess;
import com.mahao.xrzdemo.shufViewPager.ShufBanner;
import com.mahao.xrzdemo.shufViewPager.ShufBannerClickListener;
import com.mahao.xrzdemo.ui.BroadWoodHeadActivity;
import com.mahao.xrzdemo.ui.CityListActivity;
import com.mahao.xrzdemo.utils.PTRListViewUtil;
import com.mahao.xrzdemo.utils.T;
import com.mahao.xrzdemo.widget.TitleView;

import java.util.ArrayList;
import java.util.List;


/**
 * 体验页面
 *
 * @author: Alex
 * Date: 2015-09-28
 * Time: 15:07
 */
public class BroadWoodFragment extends BaseFragment {

    private boolean isFirstDate = true;

    private int more = 2;

    private int page = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            broadWood = (BroadWood) msg.obj;
            switch (msg.what) {

                case HttpAccess.SUCCESS:

                    if (isFirstDate) {

                        initView();
                        isFirstDate = false;
                    } else {
                        contentAdapter.addDate(broadWood.getList(), true);
                    }
                    break;
                case HttpAccess.FAIL:
                    Toast.makeText(getActivity().getApplicationContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
                    break;
                case 1113:
                    contentAdapter.addDate(broadWood.getList(), false);

                    break;
                case 1112:
                    Toast.makeText(getActivity().getApplicationContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
                    break;


            }
            contentLv.onRefreshComplete();
        }
    };
    private ShufBanner mShufBanner;

    /**
     * 数据加载完成，初始化viewpager
     */
    private void initView() {
        List<String> url = new ArrayList<>();

        for (Event event : broadWood.getHead()) {
            url.add(event.getAdurl());
        }

        if(url.size()== 0){
            contentAdapter = new ContentAdapter(getActivity(), broadWood.getList(), 0, headView);

            contentLv.setAdapter(contentAdapter);
        }else{

            contentAdapter = new ContentAdapter(getActivity(), broadWood.getList(), 1, headView);

            contentLv.setAdapter(contentAdapter);

            mShufBanner.startShuf(url, true);
            mShufBanner.setItemClcikListener(new ShufBannerClickListener() {
                @Override
                public void onClick(int position) {
                    Intent intent = new Intent(getActivity(), BroadWoodHeadActivity.class);
                    intent.putExtra("mobileURL", broadWood.getHead().get(position).getMobileURL());
                    startActivity(intent);
                }
            });
        }




    }


    private BroadWood broadWood;
    private TitleView title;
    private PullToRefreshListView contentLv;
    private ContentAdapter contentAdapter;
    private View headView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_broad_wood, null);
        title = (TitleView) view.findViewById(R.id.title);
        initTitle();
        initListView(view);
        return view;
    }

    /**
     * 初始化ListVIew
     *
     * @param view
     */
    private void initListView(View view) {

        contentLv = ((PullToRefreshListView) view.findViewById(R.id.lv_broadWood_event));
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.head_broad, null);
        mShufBanner = ((ShufBanner) headView.findViewById(R.id.wood_head_vp));
        PTRListViewUtil.initListView(contentLv);

        contentLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新
                page = 1;
                progress.show();
                HttpAccess.getBroadWool(handler, 0, page);


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                progress.show();
                HttpAccess.getBroadWool(handler, more, page);
            }
        });
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progress.show();
        HttpAccess.getBroadWool(handler, 0, page);

    }


    /**
     * 初始化标题
     */
    private void initTitle() {
        title.setLeftListener(TitleView.CITY_TITLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2Activity( CityListActivity.class);
            }
        });

        title.setRightListener(View.GONE, null, null);
    }


}
