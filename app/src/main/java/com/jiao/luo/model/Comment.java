package com.jiao.luo.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 评论内容
 */

public class Comment  implements MultiItemEntity {





    public String name;
    public String content;
    //赞
    public int fabulous;
    public String photoImage;
    public boolean isClickable;

    public Comment(String name, String content, int fabulous, String photoImage, boolean isClickable) {
        this.name = name;
        this.content = content;
        this.fabulous = fabulous;
        this.photoImage = photoImage;
        this.isClickable = isClickable;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
