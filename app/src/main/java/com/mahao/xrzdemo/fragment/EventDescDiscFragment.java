package com.mahao.xrzdemo.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.bean.Event;
import com.mahao.xrzdemo.ui.EventDescActivity;
import com.mahao.xrzdemo.utils.WebParser;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * 店·发现
 */
public class EventDescDiscFragment extends Fragment {

    private Event event;
    private LinearLayout web;
    private ListView lv;
    private MyAdapter adapter;
    private Handler handler = new Handler();
    private int y;

    public static EventDescDiscFragment newInstance(Event event) {

        Bundle args = new Bundle();
        args.putParcelable("event",event);
        EventDescDiscFragment fragment = new EventDescDiscFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        event = getArguments().getParcelable("event");
        adapter = new MyAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_event_desc_disc, container, false);
        initView(view);

        return  view;
    }

    private void initView(View view) {

        web = (LinearLayout) view.findViewById(R.id.event_desc_webview);
        WebParser.parserWeb(web, getActivity(), event.getMobileURL());

        TextView title = (TextView) view.findViewById(R.id.event_desc_disc_title);
        title.setText(event.getTitle());
       /* lv = (MyListView) view.findViewById(R.id.event_desc_disc_lv);


        lv.setAdapter(adapter);*/
    /*    lv.setFocusable(false);*/


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                  //  ((EventDescActivity) getActivity()).scrollView.smoothScrollBy(0, y);
                }
            }, 300);

        }else{
            y = ((EventDescActivity) getActivity()).scrollView.getScrollY();
        }
        super.onHiddenChanged(hidden);
    }


    public void setWeb(WebView paramWebView)
    {
        paramWebView.getSettings().setJavaScriptEnabled(true);
        paramWebView.getSettings().setDatabaseEnabled(true);
        paramWebView.getSettings().setDomStorageEnabled(true);
        paramWebView.getSettings().setSupportZoom(false);
        paramWebView.getSettings().setBuiltInZoomControls(false);
        paramWebView.getSettings().setUseWideViewPort(true);
    }



    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return event.getMore().size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_event_disc,null);
            ImageView img = (ImageView) view.findViewById(R.id.event_disc_more_img);

            Event.MoreEntity b = event.getMore().get(position);
            ImageLoader.getInstance().displayImage(b.getImgs().get(0),img);

            TextView address = (TextView) view.findViewById(R.id.event_disc_more_address);
            address.setText(b.getAddress());
            TextView title = (TextView) view.findViewById(R.id.event_disc_more_title);
            title.setText(b.getTitle());
            return view;
        }
    }
}
