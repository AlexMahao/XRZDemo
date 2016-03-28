package com.mahao.xrzdemo.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by mdw on 2016/3/28.
 */
public class FeedBack extends BmobObject {

    private String phone;
    private String content;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FeedBack(){
        setTableName("feedback");
    }
}
