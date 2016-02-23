package com.mahao.xrzdemo.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.bean.Event;


/**
 * 店·详情
 */
public class EventDescDescFragment extends Fragment {

    private Event event;
    private TextView title,date,tel,address;


    public static EventDescDescFragment newInstance(Event event) {

        Bundle args = new Bundle();
        args.putParcelable("event",event);
        EventDescDescFragment fragment = new EventDescDescFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        event = getArguments().getParcelable("event");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_desc_desc, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        title = (TextView) view.findViewById(R.id.event_disc_title);
        title.setText(event.getTitle());
        tel = (TextView) view.findViewById(R.id.event_disc_tel);
        tel.setText(event.getTelephone());

        ImageView telImg = (ImageView) view.findViewById(R.id.event_disc_tel_img);
        telImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + event.getTelephone()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        address = (TextView) view.findViewById(R.id.event_disc_address);
        address.setText(event.getAddress());
        ImageView addressImg = (ImageView) view.findViewById(R.id.event_disc_address_img);

        addressImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //地图定位，待实现
                /*Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("position",event.getPosition());
                startActivity(intent);*/
            }
        });
    }


}
