package com.jiao.luo.Response;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */

public class Follow {

    /**
     * returnCode : 1
     * data : [{"id":"332","typeid":"165","userid":"17","title":"特产研究站","is_notice":"-1","adddate":"1502750547","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103040393.jpg","updatetime":null},{"id":"331","typeid":"158","userid":"17","title":"旅行新势力","is_notice":"-1","adddate":"1502750545","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103226946.jpg","updatetime":"1502680560"},{"id":"323","typeid":"296","userid":"17","title":"最新电影定档资讯","is_notice":"-1","adddate":"1502700143","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094259166.jpg","updatetime":"1502680595"},{"id":"313","typeid":"276","userid":"17","title":"办公室小野又开吃了","is_notice":"-1","adddate":"1502700058","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095008345.jpg","updatetime":null},{"id":"311","typeid":"338","userid":"17","title":"值得一看的长文章","is_notice":"-1","adddate":"1502700015","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807090537341.jpg","updatetime":null},{"id":"284","typeid":"342","userid":"17","title":"假新闻终结者","is_notice":"-1","adddate":"1502424880","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807090452258.jpg","updatetime":null},{"id":"279","typeid":"278","userid":"17","title":"大胃王朵一又开吃啦","is_notice":"-1","adddate":"1502422659","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094943131.jpg","updatetime":null},{"id":"265","typeid":"277","userid":"17","title":"大胃王密子君又拉仇恨","is_notice":"-1","adddate":"1502422414","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094955757.jpg","updatetime":null},{"id":"241","typeid":"275","userid":"17","title":"小法的烘焙灵感集","is_notice":"-1","adddate":"1502404285","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807095024471.jpg","updatetime":null},{"id":"234","typeid":"259","userid":"17","title":"大学城新闻","is_notice":"-1","adddate":"1502358685","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807100044985.jpg","updatetime":"1502680187"}]
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
         * id : 332
         * typeid : 165
         * userid : 17
         * title : 特产研究站
         * is_notice : -1
         * adddate : 1502750547
         * imgs : http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103040393.jpg
         * updatetime : null
         */

        private String id;
        private String typeid;
        private String userid;
        private String title;
        private String is_notice;
        private String adddate;
        private String imgs;
        private Object updatetime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
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

        public String getAdddate() {
            return adddate;
        }

        public void setAdddate(String adddate) {
            this.adddate = adddate;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public Object getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(Object updatetime) {
            this.updatetime = updatetime;
        }
    }
}
