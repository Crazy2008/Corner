package com.jiao.luo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jiao.luo.model.Channel;

import java.util.List;


/**
 * 频道
 */
public class ChannelPagerAdapter extends FragmentStatePagerAdapter {

    private final FragmentManager mFm;
    private List<Fragment> fragments;
    private List<Channel> mChannels;


    public ChannelPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<Channel> channels) {
        super(fm);
        mFm = fm;
        this.fragments = fragments;
        this.mChannels = channels;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return mChannels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return mChannels == null ? "" : mChannels.get(position).Title;
        return "ttttttt";
    }

    @Override
    public int getItemPosition(Object object) {

            return POSITION_NONE;

    }

}
