package com.mahao.xrzdemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mahao.xrzdemo.MyApp;
import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.ui.CollectActivity;
import com.mahao.xrzdemo.ui.LoginActivity;
import com.mahao.xrzdemo.ui.RecommendActivity;
import com.mahao.xrzdemo.ui.ShowUserActivity;
import com.mahao.xrzdemo.utils.T;
import com.mahao.xrzdemo.widget.TitleView;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 我的
 */
public class MoreFragment extends BaseFragment implements View.OnClickListener {


    private ImageView avatarImg;
    private int[] icons = {R.mipmap.pcenter, R.mipmap.orderform, R.mipmap.collect, };

    private String[] titles = {"个人中心", "我要吐槽", "我的收藏"};
    private ListView lv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        initTitle(view);
        avatarImg = (ImageView) view.findViewById(R.id.more_avatar);
        initListView(view);

        if ( MyApp.user == null) {
            //未登录或注册
        } else {
            if (!TextUtils.isEmpty( MyApp.user.getUserIcon())) {
                ImageLoader.getInstance().displayImage( MyApp.user.getUserIcon(), avatarImg);
            }
        }

        avatarImg.setOnClickListener(this);
        return view;
    }

    //初始化listView
    private void initListView(View view) {
        lv = ((ListView) view.findViewById(R.id.more_lv));
        MyAdapter adapter = new MyAdapter();
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //个人信息
                        if ( MyApp.user == null) {
                          T.show(getActivity().getApplicationContext(),"请先登录");
                        }else{
                            intent2Activity(ShowUserActivity.class);
                        }

                        break;
                    case 1:


                        break;
                    case 2:
                        Intent intent = new Intent(getActivity(), CollectActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        Intent intent1 = new Intent(getActivity(), RecommendActivity.class);
                        startActivity(intent1);
                        break;
                }
            }
        });
    }

    /**
     * 初始化标题栏
     *
     * @param view
     */
    private void initTitle(View view) {
        TitleView titleView = (TitleView) view.findViewById(R.id.title);
        titleView.setLeftListener(TitleView.ALL_HIDE_TITLE, null);
        titleView.setRightListener(View.VISIBLE, "设置", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.show(getActivity(), "设置");
            }
        });
    }

    @Override
    public void onClick(View v) {
        if ( MyApp.user == null) {
            intent2Activity(LoginActivity.class);
        }else{
            intent2Activity(ShowUserActivity.class);
        }
    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return icons.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_more, null);

            ImageView icon = (ImageView) view.findViewById(R.id.more_item_icon);
            icon.setImageResource(icons[position]);
            ((TextView) view.findViewById(R.id.more_item_title)).setText(titles[position]);
            return view;
        }
    }


}
