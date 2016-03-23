package com.mahao.xrzdemo.bean;

/**
 * 城市列表
 * Created by Alex_MaHao on 2016/3/22.
 */
public class City {

    /**
     * "parent": {
     "parent_name": "华东华南",
     "parent_id": 104,
     "parent_pinyin": "huadonghuanan"
     },
     "id": 510,
     "pinyin": "huizhou",
     "name": "徽州"
     },

     */

    private String id ;
    private String pinyin;
    private String name;
    private CityParents parent;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityParents getParent() {
        return parent;
    }

    public void setParent(CityParents parent) {
        this.parent = parent;
    }

    /**
     * 所属地区
     */
    public class CityParents{


        private String parent_name;
        private String parent_id;
        private String parent_pinyin;

        public String getParent_name() {
            return parent_name;
        }

        public void setParent_name(String parent_name) {
            this.parent_name = parent_name;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getParent_pinyin() {
            return parent_pinyin;
        }

        public void setParent_pinyin(String parent_pinyin) {
            this.parent_pinyin = parent_pinyin;
        }
    }


}
