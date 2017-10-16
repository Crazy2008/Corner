package com.jiao.luo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResTopicHeader;
import com.jiao.luo.interf.MyOnItemClickListener;
import com.jiao.luo.utils.GlideUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public class FindHeaderAdapter extends BaseQuickAdapter<ResTopicHeader.DataBean, BaseViewHolder> {
    private final int layoutResId;
    private final List<ResTopicHeader.DataBean> data;
    private final Context context;
    private MyOnItemClickListener listener;

    public FindHeaderAdapter(int layoutResId, List<ResTopicHeader.DataBean> data, Context context,MyOnItemClickListener listener) {
        super(layoutResId, data);
        this.layoutResId = layoutResId;
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ResTopicHeader.DataBean item) {
       final int position = helper.getPosition();

        String is_follow = item.getIs_follow();
        //设置关注按钮
        ImageView iv_follow = helper.getView(R.id.iv_follow);

        if ("未关注".equals(is_follow)) {
            iv_follow.setPressed(false);
        } else {
            iv_follow.setPressed(true);
        }
        iv_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    listener.onItem(position);
            }
        });


        //设置描述
        helper.setText(R.id.tv_desc, item.getName());
        //设置img fl_content
       /* final FrameLayout fl_content= helper.getView(R.id.fl_content);
        Glide.with(context)
                .load(item.getImgs())
                .asBitmap()
                .transform(new MyCornersTransform(context,20))
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                        //得到bitmap
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                        fl_content.setBackground(bitmapDrawable);
                    }
                });*/

       ImageView iv_icon = helper.getView(R.id.iv_icon);
        GlideUtils.load(context,item.getImgs(),iv_icon);


    }
}
