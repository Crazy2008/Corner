package com.jiao.luo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.jiao.luo.R;
import com.jiao.luo.Response.ResCate;
import com.jiao.luo.interf.MyOnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */

public class CateAdapter extends BaseQuickAdapter<ResCate.DataBean, BaseViewHolder> {
    private final Context context;
    private final int layoutResId;
    private final List<ResCate.DataBean> data;
    private MyOnItemClickListener listener;

    public CateAdapter(Context context, int layoutResId, List<ResCate.DataBean> data, MyOnItemClickListener listener) {
        super(layoutResId, data);
        this.context = context;
        this.layoutResId = layoutResId;
        this.data = data;
        this.listener = listener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, ResCate.DataBean item) {
        helper.setIsRecyclable(false);

        final TextView tv_catname = helper.getView(R.id.tv_catname);
//        View view = helper.getView(R.id.tv_catname);
        if(item.isChecked()){
            tv_catname.setBackgroundResource(R.drawable.shape_oval_checked);
            Drawable drawable = context.getResources().getDrawable(R.drawable.icon_selected);
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_catname.setCompoundDrawables(null, null, drawable, null);
            tv_catname.setTextColor(Color.parseColor("#02CEFF"));
            tv_catname.setText(item.getCatname());
        }else{
            helper.setText(R.id.tv_catname, item.getCatname());
        }

        tv_catname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItem(helper.getPosition());
            }
        });


    }


}
