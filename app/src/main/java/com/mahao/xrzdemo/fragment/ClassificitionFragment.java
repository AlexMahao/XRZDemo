package com.mahao.xrzdemo.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.adapter.ClassAdapter;
import com.mahao.xrzdemo.bean.Classifi;
import com.mahao.xrzdemo.net.HttpAccess;
import com.mahao.xrzdemo.utils.T;
import com.mahao.xrzdemo.widget.MyGridView;
import com.mahao.xrzdemo.widget.TitleView;

import java.util.List;

/**
 *分类fragment
 */
public class ClassificitionFragment extends BaseFragment {


    private TextView classTitle1;
    private TextView classTitle2;
    private MyGridView gv1;
    private MyGridView gv2;
    private List<Classifi> classes;

    private ScrollView scrollView;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HttpAccess.SUCCESS:
                    classes = (List<Classifi>) msg.obj;
                    initData();
                    break;
                case HttpAccess.FAIL:
                    T.show(getActivity().getApplicationContext(),"获取数据失败");
                    break;


            }
        }
    };

    /**
     * 初始化数据
     */
    private void initData() {
        classTitle1.setText(classes.get(0).getTitle());
        gv1.setAdapter(new ClassAdapter(classes.get(0).getTags(), R.layout.item_gv, getActivity()));
        classTitle2.setText(classes.get(1).getTitle());
        gv2.setAdapter(new ClassAdapter(classes.get(1).getTags(), R.layout.item_gv, getActivity()));
        gv1.setFocusable(false);
        gv2.setFocusable(false);
        scrollView.smoothScrollBy(0,20);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classificition, container, false);
        initTitle(view);
        initView(view);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        return view;
    }

    private void initView(View view) {
        classTitle1 = ((TextView) view.findViewById(R.id.tv_class1));
        classTitle2 = ((TextView) view.findViewById(R.id.tv_class2));
        gv1 = ((MyGridView) view.findViewById(R.id.gv_class1));
        gv2 = ((MyGridView) view.findViewById(R.id.gv_class2));
    }

    /**
     * 初始化标题栏
     * @param view
     */
    private void initTitle(View view) {
        TitleView titleView = (TitleView) view.findViewById(R.id.title);
        titleView.setLeftListener(TitleView.ALL_HIDE_TITLE, null);
        titleView.setRightListener(View.VISIBLE,"搜索", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.show(getActivity(),"搜索");
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progress.show();
        HttpAccess.getClassList(handler);
    }
}
