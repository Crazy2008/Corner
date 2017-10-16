package com.jiao.luo.Response;

/**
 * Created by Administrator on 2017/8/4.
 */

public class ResSmsCode {


    /**
     * returnCode : 1
     * data : {"SMSCode":3201,"ExpireTime":1501818636}
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
         * SMSCode : 3201
         * ExpireTime : 1501818636
         */

        private int SMSCode;
        private int ExpireTime;

        public int getSMSCode() {
            return SMSCode;
        }

        public void setSMSCode(int SMSCode) {
            this.SMSCode = SMSCode;
        }

        public int getExpireTime() {
            return ExpireTime;
        }

        public void setExpireTime(int ExpireTime) {
            this.ExpireTime = ExpireTime;
        }
    }
}
