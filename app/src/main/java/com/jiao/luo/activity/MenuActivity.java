package com.jiao.luo.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.jiao.luo.R;
import com.jiao.luo.Response.ResCate;
import com.jiao.luo.adapter.ChannelPagerAdapter;
import com.jiao.luo.fragment.NewsListFragmentControl;
import com.jiao.luo.model.Channel;
import com.jiao.luo.utils.Constant;
import com.lzy.okgo.OkGo;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WytTriangularPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.common.base.ActivityCommBase;
import cn.common.http2.callback.SimpleCommonCallback;
import okhttp3.Call;
import okhttp3.Response;

public class MenuActivity extends ActivityCommBase {

    @BindView(R.id.magic_indicator)
    MagicIndicator indicator;
    @BindView(R.id.menu_view_pager)
    ViewPager viewPager;
    @BindView(R.id.head_view)
    View head_view;
    public List<Channel> mSelectedDatas = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
    private List<ResCate.DataBean> dataList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {

        OkGo.post(Constant.CATELIST).execute(new SimpleCommonCallback<ResCate>(this) {
            @Override
            public void onSuccess(ResCate resCate, Call call, Response response) {
                if (resCate.getReturnCode() == 1) {
                    List<ResCate.DataBean> data = resCate.getData();
                    if (data != null && data.size() > 0) {
                        dataList.addAll(data);
                        initView();
                    } else {
                        toast("获取菜单数据为空");
                    }
                } else {
                    toast("获取菜单数据为空");
                }

            }
        });
    }

    private void initView() {


//        head_view.setBackgroundColor(Color.parseColor("#99ff4866"));
//        head_view.setAlpha(0.6f);

        final String[] titleStr = getResources().getStringArray(R.array.home_title);
//        final String[] titlesCode = getResources().getStringArray(R.array.home_title_code);//
        /*final String[] titleStr = getResources().getStringArray(R.array.home_title);
        final String[] titlesCode = getResources().getStringArray(R.array.home_title_code);*/

        CommonNavigator commonNavigator = new CommonNavigator(this);

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return dataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(dataList.get(index).getCatname());
                clipPagerTitleView.setTextColor(Color.WHITE);
                clipPagerTitleView.setClipColor(Color.RED);

                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                WytTriangularPagerIndicator indicator = new WytTriangularPagerIndicator(context);
                indicator.setLineColor(Color.WHITE);
                return indicator;
            }
        });
        indicator.setNavigator(commonNavigator);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                indicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                indicator.onPageScrollStateChanged(state);
            }
        });


        //默认添加了全部频道
        for (int i = 0; i < dataList.size(); i++) {
            String t = dataList.get(i).getCatname();
            String code = String.valueOf(dataList.get(i).getCatid());
            mSelectedDatas.add(new Channel(t, code));
            fragmentList.add(NewsListFragmentControl.newInstance(code));
        }


        ChannelPagerAdapter channelPagerAdapter = new ChannelPagerAdapter(getSupportFragmentManager(), fragmentList, mSelectedDatas);
        viewPager.setAdapter(channelPagerAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("NewsListFragment", "MenuActivityDestroy");


    }
}
