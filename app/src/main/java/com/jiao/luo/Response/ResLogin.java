package com.jiao.luo.Response;

/**
 * Created by Administrator on 2017/8/4.
 */

public class ResLogin {

    /**
     * returnCode : 1
     * data : {"userid":11,"nickname":"wyt_2","token":"F3376156DCF6BD5F5E6C21A475A0E63E","HeadPic":null,"catesid":"","is_choise_cate":false}
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
         * userid : 11
         * nickname : wyt_2
         * token : F3376156DCF6BD5F5E6C21A475A0E63E
         * HeadPic : null
         * catesid :
         * is_choise_cate : false
         */

        private int userid;
        private String nickname;
        private String token;
        private String HeadPic;
        private String catesid;
        private boolean is_choise_cate;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getHeadPic() {
            return HeadPic;
        }

        public void setHeadPic(String headPic) {
            HeadPic = headPic;
        }

        public boolean is_choise_cate() {
            return is_choise_cate;
        }

        public String getCatesid() {
            return catesid;
        }

        public void setCatesid(String catesid) {
            this.catesid = catesid;
        }

        public boolean isIs_choise_cate() {
            return is_choise_cate;
        }

        public void setIs_choise_cate(boolean is_choise_cate) {
            this.is_choise_cate = is_choise_cate;
        }
    }
}
