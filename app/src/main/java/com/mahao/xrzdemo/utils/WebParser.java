package com.mahao.xrzdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Xml;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mahao.xrzdemo.MyApp;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.HashMap;

/**
 * Created by mdw on 2015/11/29.
 */
public class WebParser {

    /**
     * 解析html
     */

    public static void parserWeb(LinearLayout ll, Context context, String web) {
        try {
            XmlPullParser parser = Xml.newPullParser();

            parser.setInput(new StringReader(web));

            int eventType = parser.getEventType();

            String tagName = "";
            boolean isgo = true;
            Log.i("info", web);
            HashMap<String, String> map = new HashMap<>();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                tagName = parser.getName();
                isgo = true;
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("p".equals(tagName)) {
                            map = new HashMap<>();
                            map.put("type", "p");
                            try {
                                isgo = false;
                                String ss = parser.nextText();
                                map.put("text", ss);
                                Log.i("info",ss+"sss");
                                addView(ll, context, map);
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                        } else if ("img".equals(tagName)) {
                            Log.i("info",parser.getAttributeValue(null, "src"));
                            map = new HashMap<>();
                            map.put("type", "img");
                            map.put("url", parser.getAttributeValue(null, "src"));
                            map.put("width", parser.getAttributeValue(null, "width"));
                            map.put("height", parser.getAttributeValue(null, "height"));
                            addView(ll, context, map);
                            eventType = parser.next();
                        }
                        break;
                }
                if(isgo) eventType = parser.next();

                eventType = parser.getEventType();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addView(LinearLayout ll, Context context, HashMap<String, String> map) {
        if (map.get("type").equals("p")) {
            TextView tv = new TextView(context);
            tv.setText(map.get("text"));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(params);
            ll.addView(tv);
            tv.setPadding(0,MyApp.dip2px(15.0f),0,MyApp.dip2px(15.0f));

        } else if (map.get("type").equals("img")) {
            String url = map.get("url");
            ImageView imgView = new ImageView(context);
            DisplayMetrics metric = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
            int width = metric.widthPixels;     // 屏幕宽度（像素）
            imgView.setPadding(0, MyApp.dip2px(5.0f), 0, MyApp.dip2px(5.0f));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            imgView.setLayoutParams(params);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.getInstance().displayImage(url, imgView, MyApp.getApp().getDisplayImageOptions());
            ll.addView(imgView);
        }
    }


}
