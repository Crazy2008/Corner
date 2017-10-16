package com.jiao.luo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResCateDetail;
import com.jiao.luo.Response.ResCateDetail.DataBean.SubdataBean.ImagesBean;
import com.jiao.luo.interf.MyOnItemClickListener;
import com.jiao.luo.utils.GlideUtils;
import com.jiao.luo.utils.TimeUtils;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

/**
 * 话题详情的适配器
 */
public class CateDetailAdapter extends BaseMultiItemQuickAdapter<ResCateDetail.DataBean.SubdataBean, BaseViewHolder> {
    private final Context context;

    private final List<ResCateDetail.DataBean.SubdataBean> data;
    private final MyOnItemClickListener listener;


    public CateDetailAdapter(Context context, List<ResCateDetail.DataBean.SubdataBean> data, MyOnItemClickListener listener) {
        super(data);
        this.context = context;
        this.data = data;
        this.listener = listener;

        addItemType(R.layout.activity_catedetail_item_pic, R.layout.activity_catedetail_item_pic);
        addItemType(R.layout.activity_catedetail_item_vidio, R.layout.activity_catedetail_item_vidio);
    }


    @Override
    protected void convert(BaseViewHolder helper, ResCateDetail.DataBean.SubdataBean item) {

        final int position = helper.getPosition() - 1;

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


        //时间
        TextView tv_time = helper.getView(R.id.tv_time);
        tv_time.setText(TimeUtils.stmpToDate(item.getUpdatetime()));
        //描述
        TextView tv_desc = helper.getView(R.id.tv_desc);
        tv_desc.setText(item.getDescription());


        //收藏按钮
        ImageView iv_fav = helper.getView(R.id.iv_fav);
        if (item.getIs_fav() == -1) {
            iv_fav.setBackgroundResource(R.drawable.content_like);
        } else {
            iv_fav.setBackgroundResource(R.drawable.content_set_like);
        }
        //收藏数量
        TextView fav_count = helper.getView(R.id.fav_count);
        fav_count.setText(item.getFav_count() + "");

        //评论数量
        TextView comment_count = helper.getView(R.id.comment_count);
        comment_count.setText(item.getComment_count() + "");
        //原文
     /*    helper.getView(R.id.tv_orginal).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 listener.onSingleItem(position);
             }
         });*/

        //九宫格
        int type = helper.getItemViewType();

        switch (type) {
            //图片
            case R.layout.activity_catedetail_item_pic:
                NineGridView ng_nine = helper.getView(R.id.ng_nine);
                if (item.getImages() != null) {
                    setNinePicData(ng_nine, item.getImages());
                }
                break;
            case R.layout.activity_catedetail_item_vidio:
                //播放视频的背景图片
                ImageView iv_pic = helper.getView(R.id.iv_pic);
                GlideUtils.load(context,"http:" + item.getVideo_pic(),iv_pic);
                break;
        }


    }

    //设置九宫格数据
    private void setNinePicData(NineGridView ng_nine, List<ImagesBean> images) {
        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        for (ImagesBean bean : images) {
            ImageInfo info = new ImageInfo();
            info.setThumbnailUrl("https:" + bean.getUrl());
            info.setBigImageUrl("https:" + bean.getUrl());
            imageInfo.add(info);
        }
        ng_nine.setAdapter(new PicNineAdapter(context, imageInfo));

    }


}
