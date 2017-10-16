package com.jiao.luo.Response;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */

public class ResMenu {


    /**
     * returnCode : 1
     * data : {"page":"1","limit":"10","catid":"168","subdata":[{"typeid":"278","name":"大胃王朵一又开吃啦","desc":"大胃王朵一，自称吃什么东西都大快朵颐的朵一，微博美食视频博主。","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094943131.jpg","follow_num":"1","updatetime":null,"is_follow":"已关注"},{"typeid":"277","name":"大胃王密子君又拉仇恨","desc":"一个快乐的吃货，最关键是吃那么多还不胖的密子君，简直是拉仇恨。","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094955757.jpg","follow_num":"1","updatetime":null,"is_follow":"已关注"},{"typeid":"276","name":"办公室小野又开吃了","desc":"办公室不止眼前的KPI，还有吃和远方。","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095008345.jpg","follow_num":"1","updatetime":null,"is_follow":"已关注"},{"typeid":"275","name":"小法的烘焙灵感集","desc":"烘焙，是每个女生的梦想之一，一起分享烘焙灵感和干货吧！","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095024471.jpg","follow_num":"1","updatetime":null,"is_follow":"已关注"},{"typeid":"274","name":"韩国美食大网罗","desc":"生活在韩国的吃货，分享一般人找不到的韩国美食！","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095127951.jpg","follow_num":"0","updatetime":null,"is_follow":"未关注"},{"typeid":"273","name":"零食品鉴会","desc":"还有什么零食是朕没有吃过的？吃起来给朕瞧瞧！","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095152314.jpg","follow_num":"0","updatetime":null,"is_follow":"未关注"},{"typeid":"272","name":"「烤鸡厨房」有更新","desc":"美食界当红男神\u2014\u2014\u201c香喷喷的小烤鸡\u201d，明明可以靠颜值吃饭，却偏偏要靠筷子。","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095207781.jpg","follow_num":"0","updatetime":null,"is_follow":"未关注"},{"typeid":"271","name":"火锅世界","desc":"火锅，是一种中国独创的美食，相信没有人不爱吃火锅的。不多说了，请自己看吧，口水要流出来了，我先擦擦...","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095222278.jpg","follow_num":"0","updatetime":null,"is_follow":"未关注"},{"typeid":"270","name":"好好吃面","desc":"山西刀削面、河南烩面、陕西油泼面、上海葱油拌面、福建沙茶面、北京炸酱面、重庆小面、安徽板面、湖北热干面...总有一种面是你爱吃的。","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095719886.jpeg","follow_num":"0","updatetime":null,"is_follow":"未关注"},{"typeid":"269","name":"「手工酱的厨房」有新菜单","desc":"你长得这么好看，不如跟我学做菜吧。","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095734154.jpg","follow_num":"0","updatetime":null,"is_follow":"未关注"}]}
     * jumpUrl : null
     * code : null
     * type : null
     * message : 接口请求成功
     * status : null
     */

    private int returnCode;
    private DataBean data;
    private Object jumpUrl;
    private Object code;
    private Object type;
    private String message;
    private Object status;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(Object jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * page : 1
         * limit : 10
         * catid : 168
         * subdata : [{"typeid":"278","name":"大胃王朵一又开吃啦","desc":"大胃王朵一，自称吃什么东西都大快朵颐的朵一，微博美食视频博主。","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094943131.jpg","follow_num":"1","updatetime":null,"is_follow":"已关注"},{"typeid":"277","name":"大胃王密子君又拉仇恨","desc":"一个快乐的吃货，最关键是吃那么多还不胖的密子君，简直是拉仇恨。","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094955757.jpg","follow_num":"1","updatetime":null,"is_follow":"已关注"},{"typeid":"276","name":"办公室小野又开吃了","desc":"办公室不止眼前的KPI，还有吃和远方。","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095008345.jpg","follow_num":"1","updatetime":null,"is_follow":"已关注"},{"typeid":"275","name":"小法的烘焙灵感集","desc":"烘焙，是每个女生的梦想之一，一起分享烘焙灵感和干货吧！","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095024471.jpg","follow_num":"1","updatetime":null,"is_follow":"已关注"},{"typeid":"274","name":"韩国美食大网罗","desc":"生活在韩国的吃货，分享一般人找不到的韩国美食！","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095127951.jpg","follow_num":"0","updatetime":null,"is_follow":"未关注"},{"typeid":"273","name":"零食品鉴会","desc":"还有什么零食是朕没有吃过的？吃起来给朕瞧瞧！","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095152314.jpg","follow_num":"0","updatetime":null,"is_follow":"未关注"},{"typeid":"272","name":"「烤鸡厨房」有更新","desc":"美食界当红男神\u2014\u2014\u201c香喷喷的小烤鸡\u201d，明明可以靠颜值吃饭，却偏偏要靠筷子。","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095207781.jpg","follow_num":"0","updatetime":null,"is_follow":"未关注"},{"typeid":"271","name":"火锅世界","desc":"火锅，是一种中国独创的美食，相信没有人不爱吃火锅的。不多说了，请自己看吧，口水要流出来了，我先擦擦...","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095222278.jpg","follow_num":"0","updatetime":null,"is_follow":"未关注"},{"typeid":"270","name":"好好吃面","desc":"山西刀削面、河南烩面、陕西油泼面、上海葱油拌面、福建沙茶面、北京炸酱面、重庆小面、安徽板面、湖北热干面...总有一种面是你爱吃的。","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095719886.jpeg","follow_num":"0","updatetime":null,"is_follow":"未关注"},{"typeid":"269","name":"「手工酱的厨房」有新菜单","desc":"你长得这么好看，不如跟我学做菜吧。","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095734154.jpg","follow_num":"0","updatetime":null,"is_follow":"未关注"}]
         */

        private String page;
        private String limit;
        private String catid;
        private List<SubdataBean> subdata;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getLimit() {
            return limit;
        }

        public void setLimit(String limit) {
            this.limit = limit;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public List<SubdataBean> getSubdata() {
            return subdata;
        }

        public void setSubdata(List<SubdataBean> subdata) {
            this.subdata = subdata;
        }

        public static class SubdataBean {
            /**
             * typeid : 278
             * name : 大胃王朵一又开吃啦
             * desc : 大胃王朵一，自称吃什么东西都大快朵颐的朵一，微博美食视频博主。
             * imgs : http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094943131.jpg
             * follow_num : 1
             * updatetime : null
             * is_follow : 已关注
             */

            private String typeid;
            private String name;
            private String desc;
            private String imgs;
            private String follow_num;
            private Object updatetime;
            private String is_follow;

            public String getTypeid() {
                return typeid;
            }

            public void setTypeid(String typeid) {
                this.typeid = typeid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getImgs() {
                return imgs;
            }

            public void setImgs(String imgs) {
                this.imgs = imgs;
            }

            public String getFollow_num() {
                return follow_num;
            }

            public void setFollow_num(String follow_num) {
                this.follow_num = follow_num;
            }

            public Object getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(Object updatetime) {
                this.updatetime = updatetime;
            }

            public String getIs_follow() {
                return is_follow;
            }

            public void setIs_follow(String is_follow) {
                this.is_follow = is_follow;
            }
        }
    }
}
