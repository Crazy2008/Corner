package com.jiao.luo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResMenu;
import com.jiao.luo.interf.MyOnItemClickListener;
import com.jiao.luo.utils.GlideUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Administrator on 2017/7/6.
 */

public class NewsListAdapter extends BaseQuickAdapter<ResMenu.DataBean.SubdataBean,BaseViewHolder> {
    private final int layoutResId;
    private final List<ResMenu.DataBean.SubdataBean> data;
    private final Context context;
    private MyOnItemClickListener listener;
    private Fragment fragment;
    private RoundedCornersTransformation cornersTransform;
    private Bitmap zipBitmap;
 /*  private Handler mHandler=new Handler(){
           @Override
           public void handleMessage(Message msg) {
               super.handleMessage(msg);
               switch (msg.what){
                   case  0:
                       iv_icon.setImageBitmap(zipBitmap);
               }
           }
       };*/
    private ImageView iv_icon;


    public NewsListAdapter(int layoutResId, List<ResMenu.DataBean.SubdataBean> data, Context context, MyOnItemClickListener listener, Fragment fragment) {
        super(layoutResId, data);
        this.layoutResId = layoutResId;
        this.data = data;
        this.context = context;
        this.listener = listener;
        this.fragment = fragment;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ResMenu.DataBean.SubdataBean item) {
        CheckedTextView tv_add = helper.getView(R.id.ct_add);

        final int position = helper.getPosition() ;

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
        iv_icon = helper.getView(R.id.iv_icon);

        GlideUtils.load(fragment,item.getImgs(),iv_icon);
    }


}
