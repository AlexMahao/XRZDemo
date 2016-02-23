package com.mahao.xrzdemo.bean;

/**
 * 主题
 * Created by mdw on 2015/11/12.
 */
public class Theme {

    /**
     * themeurl : http://api.xiaorizi.me/api/themedetail/?themeid=274
     * img : http://pic.huodongjia.com/event/2015-09-25/event140578.jpg
     * title : 一起国庆京城游
     * text : <body><p>国庆去哪里？当然首都最有氛围啦。出游必备四个字：衣、食、住、行。这次就来看看到了伟大祖国的心脏北京之后，我们关于穿衣吃饭睡觉的准备吧。国庆人多，小心被挤呦！</p></body>
     * hasweb : 1
     * height : 399
     * width : 640
     * begin_time : 2015-09-26
     * keywords : 国庆 出游 衣食住
     * id : 274
     */

    private String themeurl;
    private String img;
    private String title;
    private String text;
    private int hasweb;
    private int height;
    private int width;
    private String begin_time;
    private String keywords;
    private int id;

    public void setThemeurl(String themeurl) {
        this.themeurl = themeurl;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setHasweb(int hasweb) {
        this.hasweb = hasweb;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThemeurl() {
        return themeurl;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public int getHasweb() {
        return hasweb;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public String getKeywords() {
        return keywords;
    }

    public int getId() {
        return id;
    }
}
