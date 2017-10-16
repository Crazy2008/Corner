package com.jiao.luo.Response;

/**
 * Created by Administrator on 2017/8/14.
 */

public class CommentDetail {





    public static final   int COMMENT_ONE=0;
    public static final  int COMMENT_NORMAL=1;
    public static  final int COMMENT_HORZONTAL=2;
    public boolean isClickable;
    /**
     * returnCode : 1
     * data : {"id":3,"title":"我是新的大操场内容","banner":"http://119.23.242.248/phpcms/package/uploadfile/2017/0802/20170802055239597.jpg","thumb":"http://119.23.242.248/phpcms/package/uploadfile/2017/0802/20170802055239597.jpg","commentCount":"0","description":"我是新的大操场内容","contents":null,"createtime":1501667671}
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
         * id : 3
         * title : 我是新的大操场内容
         * banner : http://119.23.242.248/phpcms/package/uploadfile/2017/0802/20170802055239597.jpg
         * thumb : http://119.23.242.248/phpcms/package/uploadfile/2017/0802/20170802055239597.jpg
         * commentCount : 0
         * description : 我是新的大操场内容
         * contents : null
         * createtime : 1501667671
         */

        private int id;
        private String title;
        private String banner;
        private String thumb;
        private String commentCount;
        private String description;
        private Object contents;
        private int createtime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getContents() {
            return contents;
        }

        public void setContents(Object contents) {
            this.contents = contents;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }
    }
}
