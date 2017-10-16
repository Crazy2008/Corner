package com.jiao.luo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiao.luo.R;
import com.jiao.luo.Response.Follow;
import com.jiao.luo.utils.GlideUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */

public class FollowAdapter extends BaseQuickAdapter<Follow.DataBean,BaseViewHolder> {

    private final int layoutResId;
    private final List<Follow.DataBean> data;
    private final Context context;
    private WytListener listener;
    int i= R.layout.activity_follow_item;

    public FollowAdapter(int layoutResId, List<Follow.DataBean> data, Context context,WytListener listener) {
        super(layoutResId, data);
        this.layoutResId = layoutResId;
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, Follow.DataBean item) {
        final int position = helper.getPosition();
        ImageView iv_icon = helper.getView(R.id.iv_icon);



        ImageView iv_follow = helper.getView(R.id.iv_follow);


        GlideUtils.load(context,item.getImgs(),iv_icon);

        helper.setText(R.id.tv_desc,item.getTitle());

        String is_notice = item.getIs_notice();
        if(is_notice.equals("-1")){
//            Drawable drawable = context.getResources().getDrawable(R.drawable.icon_cancel);
            iv_follow.setImageResource(R.drawable.icon_cancel);
        }else{
            iv_follow.setImageResource(R.drawable.icon_g_cancel);
        }
        helper.setText(R.id.tv_time,"最近更新时间"+"4小时前");
        iv_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItem(position);
            }
        });



    }
    public interface   WytListener{
        void onItem(int postion);
    }
}
