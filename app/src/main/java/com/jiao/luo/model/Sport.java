package com.jiao.luo.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class Sport {

    /**
     * returnCode : 1
     * data : [{"id":"5","title":"扒一扒迎新工作中学长学姐不可告人的秘密","banner":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807105555736.jpg","description":"扒一扒迎新工作中学长学姐不可告人的秘密","listorder":"0","voteid":"","createtime":"1502087747"},{"id":"4","title":"新的专题推荐","banner":"http://119.23.242.248/phpcms/package/uploadfile/2017/0802/20170802055239597.jpg","description":"专题导读导读","listorder":"0","voteid":"","createtime":"1501749628"},{"id":"3","title":"我是新的大操场内容","banner":"http://119.23.242.248/phpcms/package/uploadfile/2017/0802/20170802055239597.jpg","description":"我是新的大操场内容","listorder":"0","voteid":"","createtime":"1501667671"},{"id":"2","title":"社交行为无法理解么？？","banner":"http://119.23.242.248/phpcms/package/uploadfile/2017/0731/20170731035856906.jpg","description":"无法理解的结束发撒旦撒旦十大大叔阿萨德阿打算十大阿萨德十大撒爱上撒DSA发十大DSA阿打算","listorder":"0","voteid":"vote|1|单选投票选项","createtime":"1501487997"},{"id":"1","title":"大操场","banner":"http://119.23.242.248/phpcms/package/uploadfile/2017/0629/20170629035603768.jpg","description":"大操场的简介简介","listorder":"0","voteid":"vote|2|多选投票","createtime":"1498723093"}]
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
         * id : 5
         * title : 扒一扒迎新工作中学长学姐不可告人的秘密
         * banner : http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807105555736.jpg
         * description : 扒一扒迎新工作中学长学姐不可告人的秘密
         * listorder : 0
         * voteid :
         * createtime : 1502087747
         */

        private String id;
        private String title;
        private String banner;
        private String description;
        private String listorder;
        private String voteid;
        private String createtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getListorder() {
            return listorder;
        }

        public void setListorder(String listorder) {
            this.listorder = listorder;
        }

        public String getVoteid() {
            return voteid;
        }

        public void setVoteid(String voteid) {
            this.voteid = voteid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}
