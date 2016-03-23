package com.mahao.xrzdemo.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mahao.xrzdemo.R;
import com.mahao.xrzdemo.adapter.EventListAdapter;
import com.mahao.xrzdemo.bean.Event;
import com.mahao.xrzdemo.db.EventDAO;
import com.mahao.xrzdemo.widget.TitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Alex
 * Date: 2015-10-09
 * Time: 20:28
 */
public class CollectActivity extends BaseActivity {


    private ListView lv;
    private TitleView title;
    private EventListAdapter adapter;
    private List<Event> events;

    private ProgressDialog pd;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==-1){
                Toast.makeText(getApplicationContext(), "暂无收藏", Toast.LENGTH_SHORT).show();
            }else if(msg.what==1){
                adapter.notifyDataSetChanged();
            }
            pd.hide();

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        initTitle();
        initLV();
        loadData();
    }

    private void loadData() {
        pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.show();
        new Thread(){
            @Override
            public void run() {

                Message msg = Message.obtain();
                List<String> jsons = EventDAO.getInstance(CollectActivity.this).queryAll();
                if(jsons.size()==0){
                    msg.what=-1;
                }else {

                    for (int i=0;i<jsons.size();i++){
                        events.add(JSON.parseObject(jsons.get(i),Event.class));
                    }
                    msg.what=1;
                }

                handler.sendMessage(msg);


            }
        }.start();
    }

    private void initLV() {
        lv = (ListView) findViewById(R.id.collect_lv);
        events = new ArrayList<Event>();
        adapter = new EventListAdapter(events,R.layout.item_beauty_day,this);
        lv.setAdapter(adapter);
    }

    private void initTitle() {
        title = (TitleView) findViewById(R.id.title);
       title.setRightListener(View.GONE,null,null);
    }
}
