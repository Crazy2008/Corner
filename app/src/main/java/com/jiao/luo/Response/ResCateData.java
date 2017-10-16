package com.jiao.luo.Response;

import java.util.List;

/**
 * 上拉加载更多的数据
 */

public class ResCateData {


    /**
     * returnCode : 1
     * data : [{"typeid":"151","name":"潮男最前线","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103500926.jpg","follow_num":"0","updatetime":"1502680554","is_follow":"未关注"},{"typeid":"152","name":"「鞋」门正道","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103421429.jpg","follow_num":"0","updatetime":"1502680553","is_follow":"未关注"},{"typeid":"148","name":"祛痘小秘籍","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103548145.jpg","follow_num":"0","updatetime":"1502680548","is_follow":"未关注"},{"typeid":"146","name":"女神美发日志","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103620796.jpg","follow_num":"0","updatetime":"1502680547","is_follow":"未关注"},{"typeid":"143","name":"必胜客新品上线和折扣提醒","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103709947.png","follow_num":"0","updatetime":"1502680543","is_follow":"未关注"},{"typeid":"142","name":"麦当劳新品上线和折扣提醒","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103722851.jpg","follow_num":"0","updatetime":"1502680542","is_follow":"未关注"},{"typeid":"141","name":"肯德基新品上线和折扣提醒","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103733598.jpg","follow_num":"0","updatetime":"1502680541","is_follow":"未关注"},{"typeid":"131","name":"我就看看我不吃","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807104056408.jpg","follow_num":"0","updatetime":"1502680538","is_follow":"未关注"},{"typeid":"135","name":"全球美食汇","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103924995.jpg","follow_num":"0","updatetime":"1502680536","is_follow":"未关注"},{"typeid":"162","name":"成都旅游秘籍","desc":"","imgs":"http://119.23.242.248/phpcms/package/uploadfile/2017/0807/20170807103119465.jpg","follow_num":"0","updatetime":"1502680529","is_follow":"未关注"}]
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
    private List<ResTopic.DataBean.FollowTypeBean> data;

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

    public List<ResTopic.DataBean.FollowTypeBean> getData() {
        return data;
    }

    public void setData(List<ResTopic.DataBean.FollowTypeBean> data) {
        this.data = data;
    }
}
