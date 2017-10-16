package com.jiao.luo.interf;

public interface MyOnItemClickListener {

    void onItem(int pos);

    /**
     * 收藏
     *
     * @param pos
     */
    void onFavItem(int pos);

    /**
     * 评论
     *
     * @param pos
     */
    void onCommentItem(int pos);

    /**
     * 转发
     *
     * @param pos
     */
    void onRelayItem(int pos);

}