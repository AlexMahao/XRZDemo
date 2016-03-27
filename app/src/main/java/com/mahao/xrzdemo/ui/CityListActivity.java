package com.mahao.xrzdemo.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.mahao.xrzdemo.MyApp;
import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.adapter.BaseAppAdapter;
import com.mahao.xrzdemo.adapter.ViewHolder;
import com.mahao.xrzdemo.bean.City;
import com.mahao.xrzdemo.net.HttpAccess;
import com.mahao.xrzdemo.utils.AppManager;
import com.mahao.xrzdemo.utils.T;
import com.mahao.xrzdemo.widget.TitleBar;
import com.mahao.xrzdemo.widget.TitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex_MaHao on 2016/3/22.
 */
public class CityListActivity extends  BaseActivity {


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case HttpAccess.FAIL:
                    T.show(getApplicationContext(),"网络异常");
                    break;
                case HttpAccess.SUCCESS:
                    List<City> datas = (List<City>) msg.obj;
                    Log.i("info",datas.toString());
                    for(City data:datas){
                        if(data.getParent().getParent_name().equals("国外热门")){
                            outCity.add(data);
                        }else{
                            inCity.add(data);
                        }
                    }
                    outAdapter.notifyDataSetChanged();
                    inAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private GridView gv_out;
    private GridView gv_in;

    private MyAdapter outAdapter;
    private MyAdapter inAdapter;

    private List<City> outCity;
    private List<City> inCity;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        initTitle();

        gv_out = ((GridView) findViewById(R.id.gv_city_list_out));
        gv_in = ((GridView) findViewById(R.id.gv_city_list_in));

        outCity = new ArrayList<>();
        inCity = new ArrayList<>();

        outAdapter = new MyAdapter(outCity,R.layout.item_city_list,getApplicationContext());
        inAdapter = new MyAdapter(inCity,R.layout.item_city_list,getApplicationContext());

        gv_out.setAdapter(outAdapter);
        gv_out.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyApp.getApp().setCityId(Integer.parseInt(outCity.get(position).getId()));
                MyApp.getApp().setCityName(outCity.get(position).getName());
                MyApp.getApp().write2sp("cityId",outCity.get(position).getId()+"");
                MyApp.getApp().write2sp("cityName",outCity.get(position).getName());

                AppManager.getAppManager().finishAllActivity();

                Intent intent = new Intent(CityListActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

        gv_in.setAdapter(inAdapter);

        gv_in.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyApp.getApp().setCityId(Integer.parseInt(inCity.get(position).getId()));
                MyApp.getApp().setCityName(inCity.get(position).getName());
                MyApp.getApp().write2sp("cityId",inCity.get(position).getId()+"");
                MyApp.getApp().write2sp("cityName",inCity.get(position).getName());

                AppManager.getAppManager().finishAllActivity();

                Intent intent = new Intent(CityListActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });


        HttpAccess.getCityList(handler);

    }

    private void initTitle() {
        TitleBar titleBar = (TitleBar) findViewById(R.id.title);
        titleBar.setTitleBarClickListener(new TitleBar.TitleBarClickListener(){

            @Override
            public void onLeftClick() {
                onBackPressed();
            }
        });
    }


    class MyAdapter extends BaseAppAdapter<City>{


        public MyAdapter(List<City> list, int itemLayoutId, Context context) {
            super(list, itemLayoutId, context);
        }

        @Override
        public void bindData(ViewHolder viewHolder, City entity, ViewGroup parent) {
            TextView itemView = (TextView) viewHolder.itemView;

            if(entity.getId().equals(MyApp.getApp().getCityId()+"")){
                //Log.i("info",entity.getId()+"````"+MyApp.getApp().getCityId());
                itemView.setTextColor(Color.RED);

            }else{
                itemView.setTextColor(Color.BLACK);
            }

            itemView.setText(entity.getName());
        }
    }
}
