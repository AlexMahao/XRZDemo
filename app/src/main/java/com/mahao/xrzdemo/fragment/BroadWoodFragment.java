package com.mahao.xrzdemo.fragment;

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
import com.mahao.xrzdemo.net.HttpAccess;
import com.mahao.xrzdemo.utils.PTRListViewUtil;
import com.mahao.xrzdemo.utils.T;
import com.mahao.xrzdemo.widget.TitleView;


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

    int current;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            broadWood = (BroadWood) msg.obj;
            switch (msg.what) {

                case HttpAccess.SUCCESS:

                    if(isFirstDate) {

                        initView();
                        isFirstDate = false;
                    }else{
                        contentAdapter.addDate(broadWood.getList(),true);
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

    /**
     * 数据加载完成，初始化viewpager
     */
    private void initView() {
        headAdapter = new HeadAdapter(getActivity(), broadWood.getHead());
        headVP.setAdapter(headAdapter);
        current = Integer.MAX_VALUE / 2;
        headVP.setCurrentItem(current);

        headVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //启动图片轮播线程
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            headVP.setCurrentItem(current,true);
                            current++;
                        }
                    });
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();


        contentAdapter = new ContentAdapter(getActivity(),broadWood.getList(),1,headView);

        contentLv.setAdapter(contentAdapter);
    }


    private BroadWood broadWood;
    private TitleView title;
    private ViewPager headVP;
    private PullToRefreshListView contentLv;
    private LinearLayout indicator;
    private HeadAdapter headAdapter;
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
     * @param view
     */
    private void initListView(View view) {

        contentLv = ((PullToRefreshListView) view.findViewById(R.id.lv_broadWood_event));
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.head_broad, null);
        headVP = ((ViewPager) headView.findViewById(R.id.wood_head_vp));
        indicator = (LinearLayout) headView.findViewById(R.id.head_vp_indection);
        PTRListViewUtil.initListView(contentLv);

        contentLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新
                page = 1;
                progress.show();
                HttpAccess.getBroadWool(handler,0,page);



            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                progress.show();
                HttpAccess.getBroadWool(handler,more,page);
            }
        });
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progress.show();
        HttpAccess.getBroadWool(handler,0,page);
        headVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

               changerIndicator(position % 2);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    /**
     * 改变导航点的位置
     *
     * @param position
     */
    private void changerIndicator(int position) {
        int number = indicator.getChildCount();
        for (int i = 0; i < number; i++) {
            ImageView img = (ImageView) indicator.getChildAt(i);
            if (i == position) {
                img.setImageResource(R.mipmap.more_listitem_point);
            } else {
                img.setImageResource(R.mipmap.specialoffer_head_point_n);
            }
        }
    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        title.setLeftListener(TitleView.CITY_TITLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.show(getActivity(), "选择城市");
            }
        });

        title.setRightListener(View.GONE, null, null);
    }


}
