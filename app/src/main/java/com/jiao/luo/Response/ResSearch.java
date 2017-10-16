package com.jiao.luo.Response;

import java.util.List;

/**
 * Created by Administrator on 2017/8/27.
 */

public class ResSearch {

    /**
     * data : [{"desc":"暴雪游戏，必属精品！一起来看暴雪有什么最新动态。","follow_num":"1","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807111648356.jpg","is_follow":"未关注","name":"暴雪游戏动态","typeid":"124","updatetime":"1502693617"},{"desc":"","follow_num":"1","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807104300675.jpg","is_follow":"未关注","name":"新出游戏","typeid":"126","updatetime":"1502680521"},{"desc":"App Store 中国游戏畅销榜，你爱玩的是哪一款游戏？","follow_num":"1","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807104327206.jpg","is_follow":"未关注","name":"App Store 中国游戏畅销榜","typeid":"125"}]
     * message : 接口请求成功
     * returnCode : 1
     */

    private String message;
    private int returnCode;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * desc : 暴雪游戏，必属精品！一起来看暴雪有什么最新动态。
         * follow_num : 1
         * imgs : http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807111648356.jpg
         * is_follow : 未关注
         * name : 暴雪游戏动态
         * typeid : 124
         * updatetime : 1502693617
         */



       /* private String id;
        private String typeid;
        private String userid;
        private String title;
        private String is_notice;
        private String adddate;
        private String imgs;
        private Object updatetime;*/




        private String desc;
        private String follow_num;
        private String imgs;
        private String is_follow;
        private String name;
        private String typeid;
        private String updatetime;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getFollow_num() {
            return follow_num;
        }

        public void setFollow_num(String follow_num) {
            this.follow_num = follow_num;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
