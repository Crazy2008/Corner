package com.jiao.luo.Response;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */

public class ResTopic {


    /**
     * returnCode : 1
     * data : {"recommend_type":{"typeid":"336","name":"万能的大熊","desc":"万能的大熊 不错吧","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807090615605.jpg"},"follow_type":[{"category":"170","typeid":"285","name":"一块去旅行","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094726132.jpg","follow_num":"0","is_follow":"未关注"},{"category":"170","typeid":"284","name":"这里是美国","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094758621.jpg","follow_num":"0","is_follow":"未关注"},{"category":"170","typeid":"165","name":"特产研究站","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103040393.jpg","follow_num":"0","is_follow":"未关注"},{"category":"173","typeid":"292","name":"每晚一首粤语歌","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094358543.jpg","follow_num":"0","is_follow":"未关注"},{"category":"173","typeid":"291","name":"网易云音乐有新的MV推荐","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094417704.png","follow_num":"0","is_follow":"未关注"},{"category":"173","typeid":"290","name":"网易云村新专栏","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094417704.png","follow_num":"0","is_follow":"未关注"},{"category":"174","typeid":"306","name":"又有足球运动员要退役了","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807093520172.jpg","follow_num":"0","is_follow":"未关注"},{"category":"174","typeid":"305","name":"跟着肆客足球涨姿势","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807093712740.jpg","follow_num":"0","is_follow":"未关注"},{"category":"174","typeid":"304","name":"直击欧冠","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807093732472.jpg","follow_num":"0","is_follow":"未关注"}],"show_type":"285,284,165,292,291,290,306,305,304"}
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
         * recommend_type : {"typeid":"336","name":"万能的大熊","desc":"万能的大熊 不错吧","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807090615605.jpg"}
         * follow_type : [{"category":"170","typeid":"285","name":"一块去旅行","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094726132.jpg","follow_num":"0","is_follow":"未关注"},{"category":"170","typeid":"284","name":"这里是美国","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094758621.jpg","follow_num":"0","is_follow":"未关注"},{"category":"170","typeid":"165","name":"特产研究站","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103040393.jpg","follow_num":"0","is_follow":"未关注"},{"category":"173","typeid":"292","name":"每晚一首粤语歌","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094358543.jpg","follow_num":"0","is_follow":"未关注"},{"category":"173","typeid":"291","name":"网易云音乐有新的MV推荐","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094417704.png","follow_num":"0","is_follow":"未关注"},{"category":"173","typeid":"290","name":"网易云村新专栏","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094417704.png","follow_num":"0","is_follow":"未关注"},{"category":"174","typeid":"306","name":"又有足球运动员要退役了","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807093520172.jpg","follow_num":"0","is_follow":"未关注"},{"category":"174","typeid":"305","name":"跟着肆客足球涨姿势","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807093712740.jpg","follow_num":"0","is_follow":"未关注"},{"category":"174","typeid":"304","name":"直击欧冠","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807093732472.jpg","follow_num":"0","is_follow":"未关注"}]
         * show_type : 285,284,165,292,291,290,306,305,304
         */

        private RecommendTypeBean recommend_type;
        private String show_type;
        private List<FollowTypeBean> follow_type;

        public RecommendTypeBean getRecommend_type() {
            return recommend_type;
        }

        public void setRecommend_type(RecommendTypeBean recommend_type) {
            this.recommend_type = recommend_type;
        }

        public String getShow_type() {
            return show_type;
        }

        public void setShow_type(String show_type) {
            this.show_type = show_type;
        }

        public List<FollowTypeBean> getFollow_type() {
            return follow_type;
        }

        public void setFollow_type(List<FollowTypeBean> follow_type) {
            this.follow_type = follow_type;
        }

        public static class RecommendTypeBean {
            /**
             * typeid : 336
             * name : 万能的大熊
             * desc : 万能的大熊 不错吧
             * imgs : http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807090615605.jpg
             */

            private String typeid;
            private String name;
            private String desc;
            private String imgs;

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
        }

        public static class FollowTypeBean {
            /**
             * category : 170
             * typeid : 285
             * name : 一块去旅行
             * desc :
             * imgs : http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807094726132.jpg
             * follow_num : 0
             * is_follow : 未关注
             */

            private String category;
            private String typeid;
            private String name;
            private String desc;
            private String imgs;
            private String follow_num;
            private String is_follow;

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

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

            public String getIs_follow() {
                return is_follow;
            }

            public void setIs_follow(String is_follow) {
                this.is_follow = is_follow;
            }
        }
    }
}
