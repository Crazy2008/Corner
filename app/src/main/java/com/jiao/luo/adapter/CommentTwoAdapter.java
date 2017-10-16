package com.jiao.luo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResComment.DataBean.ListBean;
import com.jiao.luo.interf.PubListener;
import com.jiao.luo.utils.GlideUtils;
import com.jiao.luo.utils.TimeUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 大操场评论adapter
 */

public class CommentTwoAdapter extends BaseQuickAdapter<ListBean, BaseViewHolder> {


    private final int layoutResId;
    private final List<ListBean> data;
    private final Context context;
    private PubListener listener;


    public CommentTwoAdapter(int layoutResId, List<ListBean> data, Context context, PubListener listener) {
        super(layoutResId, data);
        this.layoutResId = layoutResId;
        this.data = data;
        this.context = context;
        this.listener = listener;
    }



    @Override
    protected void convert(final BaseViewHolder holder, final ListBean item) {
        final int position = holder.getPosition()-1;
        //设置头像
        CircleImageView iv_icon = holder.getView(R.id.iv_icon);
        GlideUtils.load(context,item.getUser().getHeadPic(),iv_icon);

        holder.setText(R.id.tv_name, item.getUser().getUsername());

        holder.setText(R.id.tv_time, TimeUtils.stmpToDate(item.getCreat_at()));

        holder.setText(R.id.tv_desc, item.getContent());

        ImageView iv_support = holder.getView(R.id.iv_support);



        holder.getView(R.id.ll_support).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItem(position,2);
            }
        });

        if (item.isSup()) {
            iv_support.setBackgroundResource(R.drawable.icon_a_fabulous);
        } else {
            iv_support.setBackgroundResource(R.drawable.icon_fabulous);
        }





    }




}
