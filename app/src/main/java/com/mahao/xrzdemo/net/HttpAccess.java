package com.mahao.xrzdemo.net;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mahao.xrzdemo.MyApp;
import com.mahao.xrzdemo.bean.BroadWood;
import com.mahao.xrzdemo.bean.City;
import com.mahao.xrzdemo.bean.Classifi;
import com.mahao.xrzdemo.bean.Event;
import com.mahao.xrzdemo.bean.ExploreShop;
import com.mahao.xrzdemo.bean.Theme;
import com.mahao.xrzdemo.utils.Urls;
import com.mahao.xrzdemo.utils.VolleyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 网络连接,请求数据
 * Created by mdw on 2015/11/12.
 */
public class HttpAccess {

    public static final  int SUCCESS = 1111;
    public static  final int FAIL = 1110;


    /**
     * 探店数据的获取，美天
     * @param city
     * @param page
     * @param handler
     */
    public static void exploreShopePost(int city,int page,final Handler handler){
        HashMap<String,String> map = getTimeToken();
        String time = map.get("time");
        String token = map.get("token");
        String url = String.format(Urls.EXPLORE_SHOP,  city+"", page+"", time,token);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = Message.obtain();
                try {
                    JSONObject object = new JSONObject(response);
                    Log.i("info",response);
                    List<ExploreShop> exploreShops = JSON.parseArray(object.getJSONArray("list").toString(), ExploreShop.class);

                    message.what = SUCCESS;
                    message.obj  = exploreShops;

                } catch (JSONException e) {
                    message.what = FAIL;
                    e.printStackTrace();
                }finally {
                    handler.sendMessage(message);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Message msg = Message.obtain();
                msg.what = FAIL;
                handler.sendMessage(msg);
            }
        });

        VolleyUtils.getInstance().add(request);
    }


    /**
     * 通过主题，获取主题下事件列表
     */

    public static void getEventList(String id, final Handler handler){
        HashMap<String,String> map = getTimeToken();
        String time = map.get("time");
        String token = map.get("token");

        String url = String.format(Urls.EVENT_LIST, id, "" + time, token);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = Message.obtain();
                try {
                    JSONObject object = new JSONObject(response);
                    Log.i("info",response);
                    List<Event> events = JSON.parseArray(new JSONObject(response).getJSONArray("list").toString(), Event.class);


                    message.what = SUCCESS;
                    message.obj = events;

                } catch (JSONException e) {
                    message.what = FAIL;
                    e.printStackTrace();
                }finally {
                    handler.sendMessage(message);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Message msg = Message.obtain();
                msg.what = FAIL;
                handler.sendMessage(msg);
            }
        });

        VolleyUtils.getInstance().add(request);

    }


    /**
     * 返回美辑的主题列表
     */
    public static void getExploreSpecial(int page, final Handler handler){
        HashMap<String,String> map = getTimeToken();
        String time = map.get("time");
        String token = map.get("token");
        String url = String.format(Urls.EXPLORE_SPLICAL_LIST,page+"",""+time,token);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = Message.obtain();
                try {
                    JSONObject object = new JSONObject(response);
                    Log.i("info",response);
                    List<Theme> events = JSON.parseArray(new JSONObject(response).getJSONArray("list").toString(), Theme.class);


                    message.what = SUCCESS;
                    message.obj = events;

                } catch (JSONException e) {
                    message.what = FAIL;
                    e.printStackTrace();
                }finally {
                    handler.sendMessage(message);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Message msg = Message.obtain();
                msg.what = FAIL;
                handler.sendMessage(msg);
            }
        });

        VolleyUtils.getInstance().add(request);



    }


    /**
     * 返回主题的事件
     * @param handler
     */
    public static void getBroadWool( final Handler handler, final int more,int page) {

        HashMap<String,String> map = getTimeToken();
        String time = map.get("time");
        String token = map.get("token");
        String url = String.format(Urls.BROAD_WOOD,page, "" + time, token);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = Message.obtain();
                try {

                    BroadWood object = JSON.parseObject(new JSONObject(response).toString(), BroadWood.class);


                    message.what = SUCCESS+more;
                    message.obj = object;

                } catch (JSONException e) {
                    message.what = FAIL+more;
                    e.printStackTrace();
                }finally {
                    handler.sendMessage(message);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Message msg = Message.obtain();
                msg.what = FAIL+more;
                handler.sendMessage(msg);
            }
        });

        VolleyUtils.getInstance().add(request);
    }


    /**
     * 返回体验详情
     * @param handler
     */
    public static void getExperienceEvent( final Handler handler,int eid) {

        HashMap<String,String> map = getTimeToken();
        String time = map.get("time");
        String token = map.get("token");
        String url = String.format(Urls.EXPERINENCE_EVENT,eid+"", "" + time, token);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = Message.obtain();
                try {

                    Event object = JSON.parseObject(new JSONObject(response).getJSONObject("data").toString(), Event.class);


                    message.what = SUCCESS;
                    message.obj = object;

                } catch (JSONException e) {
                    message.what = FAIL;
                    e.printStackTrace();
                }finally {
                    handler.sendMessage(message);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Message msg = Message.obtain();
                msg.what = FAIL;
                handler.sendMessage(msg);
            }
        });

        VolleyUtils.getInstance().add(request);
    }


    /**
     * 获取分类列表
     * @param handler
     */
    public static void getClassList( final Handler handler) {
        int id = MyApp.getApp().getCityId();
        HashMap<String,String> map = getTimeToken();
        String time = map.get("time");
        String token = map.get("token");
        String url = String.format(Urls.CLASS_LIST,id+"", "" + time, token);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = Message.obtain();
                try {

                    List<Classifi> object = JSON.parseArray(new JSONObject(response).getJSONArray("list").toString(), Classifi.class);


                    message.what = SUCCESS;
                    message.obj = object;

                } catch (JSONException e) {
                    message.what = FAIL;
                    e.printStackTrace();
                }finally {
                    handler.sendMessage(message);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Message msg = Message.obtain();
                msg.what = FAIL;
                handler.sendMessage(msg);
            }
        });

        VolleyUtils.getInstance().add(request);
    }

    public static void getEventListFromClass(String id, final Handler handler){
        int cityId = MyApp.getApp().getCityId();
        HashMap<String,String> map = getTimeToken();
        String time = map.get("time");
        String token = map.get("token");

        String url = String.format(Urls.CLASS_DES, id+"", cityId+"", time, token);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = Message.obtain();
                try {
                    JSONObject object = new JSONObject(response);
                    Log.i("info",response);
                    List<Event> events = JSON.parseArray(new JSONObject(response).getJSONArray("list").toString(), Event.class);


                    message.what = SUCCESS;
                    message.obj = events;

                } catch (JSONException e) {
                    message.what = FAIL;
                    e.printStackTrace();
                }finally {
                    handler.sendMessage(message);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Message msg = Message.obtain();
                msg.what = FAIL;
                handler.sendMessage(msg);
            }
        });

        VolleyUtils.getInstance().add(request);

    }


    /**
     * 获取城市列表
     * @param handler
     */
    public static void getCityList(final Handler handler){
        int cityId = MyApp.getApp().getCityId();
        HashMap<String,String> map = getTimeToken();
        String time = map.get("time");
        String token = map.get("token");

        String url = String.format(Urls.CITY_LIST, time, token,cityId+"");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Message message = Message.obtain();
                try {
                    JSONObject object = new JSONObject(response);
                    Log.i("info",response);
                    List<City> citys = JSON.parseArray(new JSONObject(response).getJSONArray("list").toString(), City.class);
                    message.what = SUCCESS;
                    message.obj = citys;

                } catch (JSONException e) {
                    message.what = FAIL;
                    e.printStackTrace();
                }finally {
                    handler.sendMessage(message);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Message msg = Message.obtain();
                msg.what = FAIL;
                handler.sendMessage(msg);
            }
        });

        VolleyUtils.getInstance().add(request);

    }






    /**
     * 获取当前时间以及对应当前时间的token
     * @return
     */
    public static HashMap<String,String> getTimeToken(){
        long time = new Date().getTime();
        String str1 = MD5(time + "cqdeveloper");
        String str2 = "";
        char[] arrayOfChar = str1.toCharArray();
        for (int i = 0; i < str1.length(); i++)
            if (i % 2 == 1)
                str2 = str2 + arrayOfChar[i];

        HashMap<String,String> map = new HashMap<>();
        map.put("time",""+time);
        map.put("token",str2);
        return map;
    }

    /**
     * 路径加密
     * @param paramString
     * @return
     */
    public static final String MD5(String paramString)
    {
        char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
        try
        {
            byte[] arrayOfByte1 = paramString.getBytes();
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(arrayOfByte1);
            byte[] arrayOfByte2 = localMessageDigest.digest();
            int i = arrayOfByte2.length;
            char[] arrayOfChar2 = new char[i * 2];
            int j = 0;
            int k = 0;
            while (j < i)
            {
                int m = arrayOfByte2[j];
                int n = k + 1;
                arrayOfChar2[k] = arrayOfChar1[(0xF & m >>> 4)];
                k = n + 1;
                arrayOfChar2[n] = arrayOfChar1[(m & 0xF)];
                j++;
            }
            String str = new String(arrayOfChar2);
            return str;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return null;
    }

}
