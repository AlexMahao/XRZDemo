package com.mahao.xrzdemo.bean;

import java.util.List;

/**
 * 探店的实体类
 * Created by mdw on 2015/11/12.
 */
public class ExploreShop {
    private String date;
    private List<Theme> themes;
    private List<Event> events;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
