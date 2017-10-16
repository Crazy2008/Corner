package com.jiao.luo.fragment;

import android.os.Bundle;

import com.jiao.luo.utils.Constant;

/**
 * Created by Administrator on 2017/7/5.
 */

public class NewsListFragmentControl {

    public static NewsListFragment newInstance(String code) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FRAGMENT_DATA, code);
        fragment.setArguments(bundle);
        return fragment;
    }

}
