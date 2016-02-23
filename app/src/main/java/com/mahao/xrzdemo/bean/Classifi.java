package com.mahao.xrzdemo.bean;

import java.util.List;

/**
 * Created by mdw on 2015/11/29.
 */
public class Classifi {


    /**
     * title : 闲时光 · 发现 · 惊喜
     * id : 234
     * tags : [{"ev_count":52,"id":7278,"img":"http://pic.huodongjia.com/event/2015-10-30/event148122.jpg","name":"咖啡馆"},{"ev_count":45,"id":7236,"img":"http://pic.huodongjia.com/event/2015-11-23/event152484.jpg","name":"餐吧"},{"ev_count":22,"id":7182,"img":"http://pic.huodongjia.com/event/2015-11-22/event152266.JPG","name":"书吧"},{"ev_count":17,"id":7281,"img":"http://pic.huodongjia.com/event/2015-11-21/event152143.JPG","name":"生活馆"},{"ev_count":17,"id":7283,"img":"http://pic.huodongjia.com/event/2015-10-21/event145817.jpg","name":"小酒馆"},{"ev_count":17,"id":7303,"img":"http://pic.huodongjia.com/event/2015-10-24/event146991.JPG","name":"艺术空间"},{"ev_count":11,"id":7368,"img":"http://pic.huodongjia.com/event/2015-11-23/event152423.png","name":"工作室"},{"ev_count":9,"id":7379,"img":"http://pic.huodongjia.com/event/2015-11-22/event152301.jpg","name":"手作坊"},{"ev_count":8,"id":7305,"img":"http://pic.huodongjia.com/event/2015-10-24/event146988.jpg","name":"家饰馆"},{"ev_count":8,"id":7310,"img":"http://pic.huodongjia.com/event/2015-10-28/event147793.jpg","name":"杂货铺"},{"ev_count":7,"id":7294,"img":"http://pic.huodongjia.com/event/2015-10-17/event144578.jpg","name":"衣饰馆"},{"ev_count":6,"id":7304,"img":"http://pic.huodongjia.com/event/2015-11-17/event151161.jpg","name":"茶坊"},{"ev_count":5,"id":7282,"img":"http://pic.huodongjia.com/event/2015-10-12/event142596.jpg","name":"旅舍"},{"ev_count":4,"id":7279,"img":"http://pic.huodongjia.com/event/2015-11-24/event152856.jpg","name":"花房"},{"ev_count":4,"id":7328,"img":"http://pic.huodongjia.com/event/2015-11-10/event149418.jpg","name":"面包房"},{"ev_count":2,"id":7291,"img":"http://pic.huodongjia.com/event/2015-10-19/event145241.jpg","name":"甜品铺"},{"ev_count":2,"id":7402,"img":"http://pic.huodongjia.com/event/2015-11-22/event152266.JPG","name":"文化空间"},{"ev_count":2,"id":7556,"img":"http://pic.huodongjia.com/event/2015-11-23/event152368.jpg","name":"画室"}]
     */

    private String title;
    private int id;
    /**
     * ev_count : 52
     * id : 7278
     * img : http://pic.huodongjia.com/event/2015-10-30/event148122.jpg
     * name : 咖啡馆
     */

    private List<TagsEntity> tags;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTags(List<TagsEntity> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public List<TagsEntity> getTags() {
        return tags;
    }

    public static class TagsEntity {
        private int ev_count;
        private int id;
        private String img;
        private String name;

        public void setEv_count(int ev_count) {
            this.ev_count = ev_count;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getEv_count() {
            return ev_count;
        }

        public int getId() {
            return id;
        }

        public String getImg() {
            return img;
        }

        public String getName() {
            return name;
        }
    }
}
