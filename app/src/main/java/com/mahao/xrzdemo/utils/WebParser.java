package com.mahao.xrzdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mahao.xrzdemo.MyApp;
import com.mahao.xrzdemo.fragment.ExploreBeautySpecialFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

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
            HashMap<String, String> map = new HashMap<>();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                tagName = parser.getName();

                switch (eventType) {

                    case XmlPullParser.START_TAG:
                        Log.i("tag", "start_" + tagName);
                        if ("p".equals(tagName)) {
                            map = new HashMap<>();
                            map.put("type", "p");
                            try {

                                String ss = parser.nextText();
                                map.put("text", ss);
                                addView(ll, context, map);
                            } catch (Exception e) {
                                // e.printStackTrace();

                            }
                            eventType = parser.getEventType();
                        } else if ("img".equals(tagName)) {
                            map = new HashMap<>();
                            map.put("type", "img");
                            map.put("url", parser.getAttributeValue(null, "src"));
                            map.put("width", parser.getAttributeValue(null, "width"));
                            map.put("height", parser.getAttributeValue(null, "height"));
                            addView(ll, context, map);
                            eventType = parser.next();
                        } else if ("strong".equals(tagName)) {
                            try {
                                map.put("type", "p");
                                String ss = parser.nextText();
                                map.put("text", ss);
                                addView(ll, context, map);
                                Log.i("info", ss + "");
                            } catch (Exception e) {

                            }
                            eventType = parser.getEventType();
                            // eventType = parser.next();
                        } else{
                            eventType = parser.next();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                            eventType = parser.next();

                        break;
                    case XmlPullParser.TEXT:

                        map = new HashMap<>();
                        map.put("type","p");
                        map.put("text",parser.getText());
                        addView(ll, context, map);
                        eventType = parser.next();


                        break;
                    default:
                        eventType = parser.next();
                }

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
            tv.setTextSize(16);
            ll.addView(tv);
            tv.setPadding(0, MyApp.dip2px(5), 0, MyApp.dip2px(5));

        } else if (map.get("type").equals("img")) {
            String url = map.get("url");

            int width = Integer.parseInt(map.get("width"));
            int height = Integer.parseInt(map.get("height"));
            ImageView imgView = new ImageView(context);

            DisplayMetrics metric = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
            final int screenWidth = metric.widthPixels;     // 屏幕宽度（像素）

            imgView.setPadding(0, MyApp.dip2px(5.0f), 0, MyApp.dip2px(5.0f));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imgView.setLayoutParams(params);

            ImageLoader.getInstance().displayImage(url, imgView, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    ((ImageView) view).setImageBitmap(scaleBitmap(loadedImage, screenWidth));
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });
            ll.addView(imgView);
        }
    }


    /**
     * 将图片的宽度处理成与屏幕的宽度一样大小
     *
     * @param screenWidth
     * @return
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, int screenWidth) {

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        //得到图片宽度比
        float num = screenWidth / (float) bitmapWidth;

        Matrix matrix = new Matrix();
        matrix.postScale(num, num);
        // 产生缩放后的Bitmap对象
        return Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth,
                bitmapHeight, matrix, true);

    }


}
