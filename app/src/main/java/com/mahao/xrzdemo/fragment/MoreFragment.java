package com.mahao.xrzdemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.ui.CollectActivity;
import com.mahao.xrzdemo.ui.RecommendActivity;
import com.mahao.xrzdemo.utils.T;
import com.mahao.xrzdemo.widget.TitleView;

/**
 * 我的
 */
public class MoreFragment extends Fragment {


    private View avatarImg;
    private int[] icons = {R.mipmap.pcenter, R.mipmap.orderform, R.mipmap.collect, R.mipmap.tuijianyy, R.mipmap.yaoyiyaoicon};

    private String[] titles = {"个人中心", "我的订单", "我的收藏", "应用推荐", "摇一摇    每天都有小惊喜"};
    private ListView lv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        initTitle(view);
        avatarImg = view.findViewById(R.id.more_avatar);
        initListView(view);
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
