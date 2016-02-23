package com.mahao.xrzdemo.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.adapter.EventListAdapter;
import com.mahao.xrzdemo.bean.Event;
import com.mahao.xrzdemo.net.HttpAccess;
import com.mahao.xrzdemo.ui.ClassDesActivity;
import com.mahao.xrzdemo.widget.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题列表展示
 */
public class ThemeListFragment extends Fragment {


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HttpAccess.SUCCESS:
                    events.addAll((List<Event>)msg.obj);
                    adapter.notifyDataSetChanged();
                    break;
                case HttpAccess.FAIL:
                    Toast.makeText(getActivity().getApplicationContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private String id;
    private MyListView lv;
    private EventListAdapter adapter;
    private List<Event> events;
    private boolean isClass;

    public static ThemeListFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id",id);
        ThemeListFragment fragment = new ThemeListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id=getArguments().getString("id");
        if (getActivity() instanceof ClassDesActivity) {
            isClass = true;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theme_list, container, false);
        initLV(view);
        return view;
    }

    private void initLV(View view) {
        lv = (MyListView)view. findViewById(R.id.theme_list);
        events = new ArrayList<Event>();

        adapter = new EventListAdapter(events,R.layout.item_beauty_day,getActivity());

        lv.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(isClass ==false) {
            HttpAccess.getEventList(id, handler);
        }else{
            HttpAccess.getEventListFromClass(id,handler);
        }
    }
}
