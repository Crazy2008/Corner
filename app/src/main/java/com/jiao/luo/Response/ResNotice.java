package com.jiao.luo.Response;

/**
 * Created by Administrator on 2017/8/15.
 * 推送
 */

public class ResNotice {

    /**
     * returnCode : 1
     * data : {"id":331,"typeid":158,"userid":17,"title":"旅行新势力","is_notice":"1","adddate":1502750545}
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
         * id : 331
         * typeid : 158
         * userid : 17
         * title : 旅行新势力
         * is_notice : 1
         * adddate : 1502750545
         */

        private int id;
        private int typeid;
        private int userid;
        private String title;
        private String is_notice;
        private int adddate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIs_notice() {
            return is_notice;
        }

        public void setIs_notice(String is_notice) {
            this.is_notice = is_notice;
        }

        public int getAdddate() {
            return adddate;
        }

        public void setAdddate(int adddate) {
            this.adddate = adddate;
        }
    }
}
