package com.jiao.luo.Response;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */

public class ResCate {

    /**
     * returnCode : 1
     * data : [{"catid":170,"catname":"旅游"},{"catid":166,"catname":"教育"},{"catid":177,"catname":"阅读"},{"catid":169,"catname":"美丽"},{"catid":168,"catname":"美食"},{"catid":179,"catname":"职场"},{"catid":212,"catname":"健身"},{"catid":220,"catname":"影视"},{"catid":246,"catname":"身边"},{"catid":176,"catname":"科技"},{"catid":175,"catname":"趣味"},{"catid":174,"catname":"体育"},{"catid":173,"catname":"音乐"},{"catid":167,"catname":"游戏"}]
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
         * catid : 170
         * catname : 旅游
         */
        private boolean checked;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        private int catid;
        private String catname;

        public int getCatid() {
            return catid;
        }

        public void setCatid(int catid) {
            this.catid = catid;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }
    }
}
