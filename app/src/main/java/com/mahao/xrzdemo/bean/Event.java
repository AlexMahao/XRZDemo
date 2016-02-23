package com.mahao.xrzdemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * 事件
 * Created by mdw on 2015/11/12.
 */
@Table(name = "Events")
public class Event  implements Parcelable {


    public Event() {
    }

    /**
     * feel : 香艳指数
     * shareURL : http://api.xiaorizi.me/app/share-131568.html
     * telephone : 010-57125458
     * tag : 花艺,幸福,生活馆
     * sponsor : {}
     * id : 131568
     * adurl :
     * startdate : 0
     * title : 最好的生活，与花草为邻
     * eventtype : 1
     * feelnum : 0
     * questionURL :
     * detail : 整个环境都被植物围绕着，橘黄的针织披肩随意地搭在摇椅上，壁炉里的火似乎才刚刚熄灭，随处可见的小物件具有浓厚的生活气息。这样的气氛会让你有一种到塔莎奶奶家做客的错觉。
     * note :
     * feeltitle : 和、敬、清、寂
     * events : []
     * more : [{"title":"食堂里食物以外的艺术","price":{"price_currency":"￥","list":[],"currency_token":"RMB","type":2},"address":"东城区杨梅竹斜街10-14号","imgs":["http://pic.huodongjia.com/event/2015-08-04/event133386.jpg"],"position":"116.398873,39.906651","id":131159},{"title":"物喜 【喜物由心 融入生活】","price":{"price_currency":"￥","list":[],"currency_token":"RMB","type":2},"address":"东城区板桥胡同乙3号","imgs":["http://pic.huodongjia.com/event/2015-07-07/event130346.png"],"position":"116.423996,39.945646","id":130997}]
     * city : 北京
     * price : {"price_currency":"￥","list":[],"currency_token":"RMB","type":2}
     * allshops : [{"position_gcj02":",","position":"116.495304878,39.9836812794","title":"花间花草茶","address":"朝阳区 798艺术区706号北三街12号","city":"北京"}]
     * address : 朝阳区 798艺术区706号北三街12号
     * imgs : ["http://pic.huodongjia.com/event/2015-09-23/event139911.jpg"]
     * islongtime : 1
     * remark : 花间花草茶
     * enddate : 3019791547
     * mobileURL : <body><html><head></head><body><p>花间是一家隐藏在798艺术区里的小花店。它的特别之处在于，本身是一家有名的花艺工作室，而店主则是一位励志要成为塔莎奶奶那样的姑娘。</p><p><img alt="" data-cke-saved-src="http://pic.huodongjia.com/event/2015-09-23/event139899.jpg" height="525" src="http://pic.huodongjia.com/event/2015-09-23/event139899.jpg" width="700"></img></p><p></p><p>进入到花间，你立刻就可以感受到店主的用心。梦幻花园般的环境，像走进了19世纪乡村风格的油画里一样。</p><p><img alt="" data-cke-saved-src="http://pic.huodongjia.com/event/2015-09-23/event139902.jpg" height="526" src="http://pic.huodongjia.com/event/2015-09-23/event139902.jpg" width="700"></img></p><p><img alt="" data-cke-saved-src="http://pic.huodongjia.com/event/2015-09-23/event139901.jpg" height="595" src="http://pic.huodongjia.com/event/2015-09-23/event139901.jpg" width="525"></img></p><p></p><p>所有美好的词汇用在这里都不为过。关上门，瞬间与世隔绝，喧嚣嘈杂的外界仿佛都与你无关了。</p><p><img alt="" data-cke-saved-src="http://pic.huodongjia.com/event/2015-09-23/event139904.jpg" height="525" src="http://pic.huodongjia.com/event/2015-09-23/event139904.jpg" width="700"></img></p><p><img alt="" data-cke-saved-src="http://pic.huodongjia.com/event/2015-09-23/event139905.jpg" height="700" src="http://pic.huodongjia.com/event/2015-09-23/event139905.jpg" width="525"></img></p><p>整个环境都被植物围绕着，橘黄的针织披肩随意地搭在摇椅上，壁炉里的火似乎才刚刚熄灭，随处可见的小物件具有浓厚的生活气息。这样的气氛会让你有一种到塔莎奶奶家做客的错觉。</p><p><img alt="" data-cke-saved-src="http://pic.huodongjia.com/event/2015-09-23/event139898.jpg" height="400" src="http://pic.huodongjia.com/event/2015-09-23/event139898.jpg" width="600"></img></p><p><img alt="" data-cke-saved-src="http://pic.huodongjia.com/event/2015-09-23/event139900.jpg" height="400" src="http://pic.huodongjia.com/event/2015-09-23/event139900.jpg" width="640"></img></p><p></p><p>花间的下午茶需要提前预约。在繁忙的工作之余，到这里来坐坐，以鲜花为伴，心中一种细小而温暖的满足感会油然而生。</p><p><img alt="" data-cke-saved-src="http://pic.huodongjia.com/event/2015-09-23/event139910.jpg" height="700" src="http://pic.huodongjia.com/event/2015-09-23/event139910.jpg" width="700"></img>​</p><p><strong>地址：</strong>朝阳区 798艺术区706号北三街12号</p><p><strong>营业时间：</strong>10:00 - 18:00</p><p><strong>联系：</strong>010-57125458（需要提前预约）</p><p></p></body></html></body>
     * cat : {"name":"","img":"","id":""}
     * eid : 131568
     * position : 116.495304878,39.9836812794
     */

    @Column(name = "feel")
    private String feel;

    @Column(name = "shareURL")
    private String shareURL;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "tag")
    private String tag;
    @Column(name = "id")
    private int id;
    @Column(name = "adurl")
    private String adurl;
    @Column(name = "startdate")
    private int startdate;
    @Column(name = "title")
    private String title;
    @Column(name = "eventtype")
    private int eventtype;
    @Column(name = "feelnum")
    private int feelnum;
    @Column(name = "questionURL")
    private String questionURL;
    @Column(name = "detail")
    private String detail;
    @Column(name = "note")
    private String note;
    @Column(name = "feeltitle")
    private String feeltitle;
    @Column(name = "city")
    private String city;
    /**
     * price_currency : ￥
     * list : []
     * currency_token : RMB
     * type : 2
     */
    @Column(name = "price")
    private PriceEntity price;
    @Column(name = "address")
    private String address;
    @Column(name = "islongtime")
    private int islongtime;
    @Column(name = "remark")
    private String remark;
    @Column(name = "enddate")
    private long enddate;
    @Column(name = "mobileURL")
    private String mobileURL;
    /**
     * name :
     * img :
     * id :
     */
    @Column(name = "cat")
    private CatEntity cat;
    @Column(name = "eid")
    private int eid;
    @Column(name = "position")
    private String position;
    /**
     * title : 食堂里食物以外的艺术
     * price : {"price_currency":"￥","list":[],"currency_token":"RMB","type":2}
     * address : 东城区杨梅竹斜街10-14号
     * imgs : ["http://pic.huodongjia.com/event/2015-08-04/event133386.jpg"]
     * position : 116.398873,39.906651
     * id : 131159
     */

    private List<MoreEntity> more;
    /**
     * position_gcj02 : ,
     * position : 116.495304878,39.9836812794
     * title : 花间花草茶
     * address : 朝阳区 798艺术区706号北三街12号
     * city : 北京
     */

    private List<AllshopsEntity> allshops;
    private List<String> imgs;

    protected Event(Parcel in) {
        feel = in.readString();
        shareURL = in.readString();
        telephone = in.readString();
        tag = in.readString();
        id = in.readInt();
        adurl = in.readString();
        startdate = in.readInt();
        title = in.readString();
        eventtype = in.readInt();
        feelnum = in.readInt();
        questionURL = in.readString();
        detail = in.readString();
        note = in.readString();
        feeltitle = in.readString();
        city = in.readString();
        address = in.readString();
        islongtime = in.readInt();
        remark = in.readString();
        enddate = in.readLong();
        mobileURL = in.readString();
        eid = in.readInt();
        position = in.readString();
        imgs = in.createStringArrayList();
        more = in.readArrayList(MoreEntity.class.getClassLoader());
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public void setFeel(String feel) {
        this.feel = feel;
    }

    public void setShareURL(String shareURL) {
        this.shareURL = shareURL;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdurl(String adurl) {
        this.adurl = adurl;
    }

    public void setStartdate(int startdate) {
        this.startdate = startdate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEventtype(int eventtype) {
        this.eventtype = eventtype;
    }

    public void setFeelnum(int feelnum) {
        this.feelnum = feelnum;
    }

    public void setQuestionURL(String questionURL) {
        this.questionURL = questionURL;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setFeeltitle(String feeltitle) {
        this.feeltitle = feeltitle;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIslongtime(int islongtime) {
        this.islongtime = islongtime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setEnddate(long enddate) {
        this.enddate = enddate;
    }

    public void setMobileURL(String mobileURL) {
        this.mobileURL = mobileURL;
    }

    public void setCat(CatEntity cat) {
        this.cat = cat;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public void setMore(List<MoreEntity> more) {
        this.more = more;
    }

    public void setAllshops(List<AllshopsEntity> allshops) {
        this.allshops = allshops;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getFeel() {
        return feel;
    }

    public String getShareURL() {
        return shareURL;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getTag() {
        return tag;
    }

    public int getId() {
        return id;
    }

    public String getAdurl() {
        return adurl;
    }

    public int getStartdate() {
        return startdate;
    }

    public String getTitle() {
        return title;
    }

    public int getEventtype() {
        return eventtype;
    }

    public int getFeelnum() {
        return feelnum;
    }

    public String getQuestionURL() {
        return questionURL;
    }

    public String getDetail() {
        return detail;
    }

    public String getNote() {
        return note;
    }

    public String getFeeltitle() {
        return feeltitle;
    }

    public String getCity() {
        return city;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public int getIslongtime() {
        return islongtime;
    }

    public String getRemark() {
        return remark;
    }

    public long getEnddate() {
        return enddate;
    }

    public String getMobileURL() {
        return mobileURL;
    }

    public CatEntity getCat() {
        return cat;
    }

    public int getEid() {
        return eid;
    }

    public String getPosition() {
        return position;
    }


    public List<MoreEntity> getMore() {
        return more;
    }

    public List<AllshopsEntity> getAllshops() {
        return allshops;
    }

    public List<String> getImgs() {
        return imgs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(feel);
        dest.writeString(shareURL);
        dest.writeString(telephone);
        dest.writeString(tag);
        dest.writeInt(id);
        dest.writeString(adurl);
        dest.writeInt(startdate);
        dest.writeString(title);
        dest.writeInt(eventtype);
        dest.writeInt(feelnum);
        dest.writeString(questionURL);
        dest.writeString(detail);
        dest.writeString(note);
        dest.writeString(feeltitle);
        dest.writeString(city);
        dest.writeString(address);
        dest.writeInt(islongtime);
        dest.writeString(remark);
        dest.writeLong(enddate);
        dest.writeString(mobileURL);
        dest.writeInt(eid);
        dest.writeString(position);
        dest.writeStringList(imgs);
        dest.writeList(more);

    }

    public static class PriceEntity implements Parcelable {
        private String price_currency;
        private String currency_token;
        private int type;
        private List<?> list;

        public PriceEntity() {
        }

        protected PriceEntity(Parcel in) {
            price_currency = in.readString();
            currency_token = in.readString();
            type = in.readInt();
        }

        public static final Creator<PriceEntity> CREATOR = new Creator<PriceEntity>() {
            @Override
            public PriceEntity createFromParcel(Parcel in) {
                return new PriceEntity(in);
            }

            @Override
            public PriceEntity[] newArray(int size) {
                return new PriceEntity[size];
            }
        };

        public void setPrice_currency(String price_currency) {
            this.price_currency = price_currency;
        }

        public void setCurrency_token(String currency_token) {
            this.currency_token = currency_token;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setList(List<?> list) {
            this.list = list;
        }

        public String getPrice_currency() {
            return price_currency;
        }

        public String getCurrency_token() {
            return currency_token;
        }

        public int getType() {
            return type;
        }

        public List<?> getList() {
            return list;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(price_currency);
            dest.writeString(currency_token);
            dest.writeInt(type);
        }
    }

    public static class CatEntity implements Parcelable {
        private String name;
        private String img;
        private String id;

        public CatEntity() {
        }

        protected CatEntity(Parcel in) {
            name = in.readString();
            img = in.readString();
            id = in.readString();
        }

        public static final Creator<CatEntity> CREATOR = new Creator<CatEntity>() {
            @Override
            public CatEntity createFromParcel(Parcel in) {
                return new CatEntity(in);
            }

            @Override
            public CatEntity[] newArray(int size) {
                return new CatEntity[size];
            }
        };

        public void setName(String name) {
            this.name = name;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }

        public String getId() {
            return id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(img);
            dest.writeString(id);
        }
    }

    public static class MoreEntity implements Parcelable {
        private String title;
        /**
         * price_currency : ￥
         * list : []
         * currency_token : RMB
         * type : 2
         */

        private PriceEntity price;
        private String address;
        private String position;
        private int id;
        private List<String> imgs;

        public MoreEntity() {
        }

        protected MoreEntity(Parcel in) {
            title = in.readString();
            address = in.readString();
            position = in.readString();
            id = in.readInt();
            imgs = in.createStringArrayList();
        }

        public static final Creator<MoreEntity> CREATOR = new Creator<MoreEntity>() {
            @Override
            public MoreEntity createFromParcel(Parcel in) {
                return new MoreEntity(in);
            }

            @Override
            public MoreEntity[] newArray(int size) {
                return new MoreEntity[size];
            }
        };

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPrice(PriceEntity price) {
            this.price = price;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }

        public String getTitle() {
            return title;
        }

        public PriceEntity getPrice() {
            return price;
        }

        public String getAddress() {
            return address;
        }

        public String getPosition() {
            return position;
        }

        public int getId() {
            return id;
        }

        public List<String> getImgs() {
            return imgs;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(address);
            dest.writeString(position);
            dest.writeInt(id);
            dest.writeStringList(imgs);
        }

        public static class PriceEntity implements Parcelable {


            public PriceEntity() {
            }

            private String price_currency;
            private String currency_token;
            private int type;
            private List<?> list;

            protected PriceEntity(Parcel in) {
                price_currency = in.readString();
                currency_token = in.readString();
                type = in.readInt();
            }

            public static final Creator<PriceEntity> CREATOR = new Creator<PriceEntity>() {
                @Override
                public PriceEntity createFromParcel(Parcel in) {
                    return new PriceEntity(in);
                }

                @Override
                public PriceEntity[] newArray(int size) {
                    return new PriceEntity[size];
                }
            };

            public void setPrice_currency(String price_currency) {
                this.price_currency = price_currency;
            }

            public void setCurrency_token(String currency_token) {
                this.currency_token = currency_token;
            }

            public void setType(int type) {
                this.type = type;
            }

            public void setList(List<?> list) {
                this.list = list;
            }

            public String getPrice_currency() {
                return price_currency;
            }

            public String getCurrency_token() {
                return currency_token;
            }

            public int getType() {
                return type;
            }

            public List<?> getList() {
                return list;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(price_currency);
                dest.writeString(currency_token);
                dest.writeInt(type);
            }
        }
    }

    public static class AllshopsEntity implements Parcelable {
        private String position_gcj02;
        private String position;
        private String title;
        private String address;
        private String city;

        public AllshopsEntity() {
        }

        protected AllshopsEntity(Parcel in) {
            position_gcj02 = in.readString();
            position = in.readString();
            title = in.readString();
            address = in.readString();
            city = in.readString();
        }

        public static final Creator<AllshopsEntity> CREATOR = new Creator<AllshopsEntity>() {
            @Override
            public AllshopsEntity createFromParcel(Parcel in) {
                return new AllshopsEntity(in);
            }

            @Override
            public AllshopsEntity[] newArray(int size) {
                return new AllshopsEntity[size];
            }
        };

        public void setPosition_gcj02(String position_gcj02) {
            this.position_gcj02 = position_gcj02;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPosition_gcj02() {
            return position_gcj02;
        }

        public String getPosition() {
            return position;
        }

        public String getTitle() {
            return title;
        }

        public String getAddress() {
            return address;
        }

        public String getCity() {
            return city;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(position_gcj02);
            dest.writeString(position);
            dest.writeString(title);
            dest.writeString(address);
            dest.writeString(city);
        }
    }
}
