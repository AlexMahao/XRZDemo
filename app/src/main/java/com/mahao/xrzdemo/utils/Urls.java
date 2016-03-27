package com.mahao.xrzdemo.utils;

/**
 * Created by mdw on 2015/11/10.
 */
public class Urls {
    //北京101
    public static final String EXPLORE_SHOP = "http://api.xiaorizi.me/api/homemixapi/?date=&cityid=%s&offset=15&page=%s&token_time=%s&app_token=%s&version=2.3.7&channel=c_zhy&uuid=c0c775f4-521c-3073-afdf-a4e4f236a5c5";

    //主题的web详情
    public static String THEME_WEB = "http://xiaorizi.me/themedetail/?themeid=%s";

    //主题下事件
    public static String EVENT_LIST = "http://api.xiaorizi.me/api/eventapi/?catid=%s&cityid=%s&offset=20&page=1&token_time=%s&app_token=%s&version=2.3.7&channel=c_zhy&uuid=c0c775f4-521c-3073-afdf-a4e4f236a5c5 ";

    //主题列表
    public static String EXPLORE_SPLICAL_LIST = "http://api.xiaorizi.me/api/catapi/?lastdate=0&cityid=%s&offset=20&page=%s&token_time=%s&app_token=%s&version=2.3.7&channel=c_zhy&uuid=c0c775f4-521c-3073-afdf-a4e4f236a5c5";

    //体验
    public static String BROAD_WOOD = "http://api.xiaorizi.me/api/eventapi/?price_type=1&offset=6&page=%s&cityid=%s&token_time=%s&app_token=%s&version=2.3.7&channel=c_zhy&uuid=c0c775f4-521c-3073-afdf-a4e4f236a5c5";

    //体验详情
    public static String EXPERINENCE_EVENT = "http://api.xiaorizi.me/api/eventinfoapi/?eventid=%s&token_time=%s&app_token=%s&version=2.3.7&channel=c_zhy&uuid=c0c775f4-521c-3073-afdf-a4e4f236a5c5 ";


    public static String CLASS_LIST = "http://api.xiaorizi.me/api/taghomeapi/?cityid=%s&token_time=%s&app_token=%s&version=2.3.7&channel=c_zhy&uuid=c0c775f4-521c-3073-afdf-a4e4f236a5c5 ";

    public static String CLASS_DES = "http://api.xiaorizi.me/api/tagidsearchapi/?tagid=%s&offset=20&page=1&cityid=%s&token_time=%s&app_token=%s&version=2.3.7&channel=c_zhy&uuid=c0c775f4-521c-3073-afdf-a4e4f236a5c5";

    /**
     * 城市列表
     */
    public static final String CITY_LIST = "http://apiv2.xiaorizi.me/city/list/?&token_time=%s&app_token=%s&version=2.8.0&channel=c_qq&uuid=3c59f436-40f5-38aa-9a92-fc7f42e316c3&city_id=%s&v=2";
}
