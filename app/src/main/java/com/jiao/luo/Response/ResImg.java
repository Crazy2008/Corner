package com.jiao.luo.Response;

/**
 * Created by Administrator on 2017/8/24.
 */

public class ResImg {

    /**
     * data : {"image_url":"http://119.23.242.248/phpcms/package/uploadfile/2017/08/24/20170824121055_183_58U1MTZN.jpg","name":"20170824121055_183_58U1MTZN.jpg","originalName":"crop1503547849888.jpg","size":41355,"state":"SUCCESS","thumb_url":"","type":"image/jpeg","url":"/2017/08/24/20170824121055_183_58U1MTZN.jpg"}
     * message : 接口请求成功
     * returnCode : 1
     */

    private DataBean data;
    private String message;
    private int returnCode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * image_url : http://119.23.242.248/phpcms/package/uploadfile/2017/08/24/20170824121055_183_58U1MTZN.jpg
         * name : 20170824121055_183_58U1MTZN.jpg
         * originalName : crop1503547849888.jpg
         * size : 41355
         * state : SUCCESS
         * thumb_url :
         * type : image/jpeg
         * url : /2017/08/24/20170824121055_183_58U1MTZN.jpg
         */

        private String image_url;
        private String name;
        private String originalName;
        private int size;
        private String state;
        private String thumb_url;
        private String type;
        private String url;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginalName() {
            return originalName;
        }

        public void setOriginalName(String originalName) {
            this.originalName = originalName;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getThumb_url() {
            return thumb_url;
        }

        public void setThumb_url(String thumb_url) {
            this.thumb_url = thumb_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
