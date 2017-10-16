package com.jiao.luo.Response;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public class ResTopicHeader {

    /**
     * returnCode : 1
     * data : [{"typeid":"343","name":"\u201c临时工\u201d又惹事了","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807090302264.jpg","follow_num":"0","updatetime":null,"is_follow":"未关注"},{"typeid":"342","name":"假新闻终结者","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807090452258.jpg","follow_num":"0","updatetime":null,"is_follow":"未关注"},{"typeid":"340","name":"突发新闻","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807090433993.jpg","follow_num":"0","updatetime":null,"is_follow":"未关注"}]
     * jumpUrl : null
     * code : null
     * type : null
     * message : 接口请求成功
     * status : null
     */

    private int returnCode;
    private Object jumpUrl;
    private Object code;
    private Object type;
    private String message;
    private Object status;
    private List<DataBean> data;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * typeid : 343
         * name : “临时工”又惹事了
         * desc :
         * imgs : http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807090302264.jpg
         * follow_num : 0
         * updatetime : null
         * is_follow : 未关注
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
