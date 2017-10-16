package com.jiao.luo.adapter;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiao.luo.R;
import com.jiao.luo.model.Sport;
import com.jiao.luo.utils.GlideUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class SportAdapter extends BaseQuickAdapter<Sport.DataBean,BaseViewHolder> {


    private final int layoutResId;
    private final List<Sport.DataBean> data;
    private final Fragment context;
    int i=R.layout.fragment_item_sport;

    public SportAdapter(int layoutResId, List<Sport.DataBean> data, Fragment context) {
        super(layoutResId, data);
        this.layoutResId = layoutResId;
        this.data = data;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Sport.DataBean item) {
        int position = helper.getPosition();
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_desc,item.getDescription());
        ImageView iv_banner= helper.getView(R.id.iv_banner);

        GlideUtils.load(context,item.getBanner(),iv_banner);
    }



}
