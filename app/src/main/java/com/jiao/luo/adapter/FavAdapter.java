package com.jiao.luo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResFav;
import com.jiao.luo.interf.MyOnItemClickListener;
import com.jiao.luo.utils.GlideUtils;
import com.jiao.luo.utils.TimeUtils;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */

public class FavAdapter extends BaseMultiItemQuickAdapter<ResFav.DataBean, BaseViewHolder> {


    private final List<ResFav.DataBean> data;
    private final Context context;
    private final MyOnItemClickListener listener;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public FavAdapter(List<ResFav.DataBean> data, Context context, MyOnItemClickListener listener) {
        super(data);
        this.data = data;
        this.context = context;
        this.listener = listener;
        addItemType(R.layout.activity_fav_item_pic, R.layout.activity_fav_item_pic);
        addItemType(R.layout.activity_fav_item_vidio, R.layout.activity_fav_item_vidio);
    }

    @Override
    protected void convert(BaseViewHolder helper, ResFav.DataBean item) {
        final int position = helper.getPosition() ;

        //ll_fav 点击事件收藏
        helper.getView(R.id.ll_fav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFavItem(position);
            }
        });
        //评论
        helper.getView(R.id.ll_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCommentItem(position);
            }
        });
        //转发
        helper.getView(R.id.relay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRelayItem(position);
            }
        });


        //        设置头像

        ImageView iv_icon = helper.getView(R.id.iv_icon);

        GlideUtils.load(context,item.getType_imgs(),iv_icon);

        //设置名称
        helper.setText(R.id.tv_title, item.getTitle());

        //时间
        TextView tv_time = helper.getView(R.id.tv_time);
        tv_time.setText(TimeUtils.stmpToDate(item.getAdddate()));
        //描述
        TextView tv_desc = helper.getView(R.id.tv_desc);
        tv_desc.setText(item.getNews().getDescription());




        //收藏按钮
        ImageView iv_fav = helper.getView(R.id.iv_fav);
            iv_fav.setBackgroundResource(R.drawable.content_set_like);

        //收藏数量


        TextView fav_count = helper.getView(R.id.fav_count);
        fav_count.setText(item.getNews().getFav_count() + "");

        //评论数量
        TextView comment_count = helper.getView(R.id.comment_count);
        comment_count.setText(item.getNews().getComment_count() + "");
        /*//原文
        helper.getView(R.id.tv_orginal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSingleItem(position);
            }
        });*/


        int itemViewType = helper.getItemViewType();
        switch (itemViewType) {
            case R.layout.activity_fav_item_pic:
                NineGridView ng_nine = helper.getView(R.id.ng_nine);
                ng_nine.setOnTouchListener(null);
                if (item.getNews().getImages()!= null) {
                    setNinePicData(ng_nine, item.getNews().getImages());
                }
                break;
            case R.layout.activity_fav_item_vidio:
                ImageView iv_pic = helper.getView(R.id.iv_pic);
                //加载视频圆角下的的图片
                GlideUtils.load(context,"https:"+item.getNews().getVideo_pic(),iv_pic);
                break;

        }
    }
    //设置九宫格数据
    private void setNinePicData(NineGridView ng_nine, List<ResFav.DataBean.NewsBean.ImagesBean> images) {
        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        for (ResFav.DataBean.NewsBean.ImagesBean bean : images) {
            ImageInfo info = new ImageInfo();
            info.setThumbnailUrl("https:"+bean.getUrl());
            info.setBigImageUrl("https:"+bean.getUrl());
            imageInfo.add(info);
        }
        ng_nine.setAdapter(new PicNineAdapter(context, imageInfo));

    }
}
