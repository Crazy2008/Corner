package com.jiao.luo.adapter;

import android.animation.ObjectAnimator;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResTopic;
import com.jiao.luo.interf.MyOnItemClickListener;
import com.jiao.luo.utils.GlideUtils;

import java.util.List;

/**
 * 发现页面的适配器
 */

public class FindAdapter extends BaseQuickAdapter<ResTopic.DataBean.FollowTypeBean, BaseViewHolder> {
    private final int layoutResId;
    private final List<ResTopic.DataBean.FollowTypeBean> data;
    private final Fragment context;
    private MyOnItemClickListener listener;

    public FindAdapter(int layoutResId, List<ResTopic.DataBean.FollowTypeBean> data, Fragment context, MyOnItemClickListener listener) {
        super(layoutResId, data);
        this.layoutResId = layoutResId;
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, ResTopic.DataBean.FollowTypeBean item) {
        CheckedTextView tv_add = helper.getView(R.id.ct_add);
        //-1是因为加了一个HeaderView
        final int position = helper.getPosition() - 1;

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItem(position);
            }
        });
        if ("未关注".equals(item.getIs_follow())) {
            tv_add.setBackgroundResource(R.drawable.bt_add);
        } else {
            tv_add.setBackgroundResource(R.drawable.bt_success);
        }


        //标题
        helper.setText(R.id.tv_title, item.getName());
        //描述
        helper.setText(R.id.tv_desc, item.getDesc());
        helper.setText(R.id.tv_type, item.getFollow_num());
        ImageView iv_icon = helper.getView(R.id.iv_icon);

        ViewPropertyAnimation.Animator animationObject = new ViewPropertyAnimation.Animator() {
            @Override
            public void animate(View view) {
                // if it's a custom view class, cast it here
                // then find subviews and do the animations
                // here, we just use the entire view for the fade animation
                view.setAlpha( 0f );
                ObjectAnimator fadeAnim = ObjectAnimator.ofFloat( view, "alpha", 0f, 1f );
                fadeAnim.setDuration( 2500 );
                fadeAnim.start();
            }
        };

        GlideUtils.load(context,item.getImgs(),iv_icon);

    }
}
