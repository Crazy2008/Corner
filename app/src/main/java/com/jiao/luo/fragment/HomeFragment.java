package com.jiao.luo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jiao.luo.R;
import com.jiao.luo.activity.MenuActivity;
import com.jiao.luo.activity.SearchActivity;
import com.jiao.luo.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.common.base.FragmentBase;


/**
 * 首页的fragment
 */
public class HomeFragment extends FragmentBase {

    Unbinder unbinder;
    @BindView(R.id.fragment_viewpager)
    ViewPager viewpager;
    @BindView(R.id.rb_project_mange)
     RadioGroup radioGroup;
    @BindView(R.id.find)
     RadioButton rb_find;
    @BindView(R.id.subscrible)
     RadioButton rb_subscrible;
    @BindView(R.id.iv_menu)
    ImageView iv_menu;
    @BindView(R.id.iv_search)
    ImageView iv_search;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("HomeFragment:onCreateView");
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        //设置按钮的选中事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.find:
                        // 发现
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.subscrible:
                        //订阅
                        viewpager.setCurrentItem(1);
                        break;
                }
            }
        });

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FindFragment());
        fragmentList.add(new SubscribleFragment());
        adapter.addFragmentList(fragmentList);
        viewpager.setAdapter(adapter);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("position = [" + position + "], positionOffset = [" + positionOffset + "], positionOffsetPixels = [" + positionOffsetPixels + "]");
            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("position = [" + position + "]");
                if (position == 0) {
                    rb_find.setChecked(true);
                } else {
                    rb_subscrible.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("state = [" + state + "]");
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("HomeFragment:onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("HomeFragment:onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_menu, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_menu:
                //菜单界面
                mContext.startActivity(new Intent(mContext, MenuActivity.class));
                HomeFragment.this.getActivity().overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
                break;
            case R.id.iv_search:
                System.out.println("view  iv_search = [" + view + "]");
                //搜索界面
                mContext.startActivity(new Intent(mContext, SearchActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
