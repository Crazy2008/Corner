package com.jiao.luo.adapter;

import android.content.Context;

import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.NineGridViewAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */

public class PicNineAdapter extends NineGridViewAdapter {
    public PicNineAdapter(Context context, List<ImageInfo> imageInfo) {
        super(context, imageInfo);
    }

    @Override
    protected void onImageItemClick(Context context, NineGridView nineGridView, int index, List<ImageInfo> imageInfo) {

    }
}
