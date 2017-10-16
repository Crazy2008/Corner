package com.jiao.luo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiao.luo.R;
import com.jiao.luo.activity.AccountSettingActivity;
import com.jiao.luo.activity.FavActivity;
import com.jiao.luo.activity.FollowActivity;
import com.jiao.luo.activity.LoginActivity;
import com.jiao.luo.activity.SettingSecondActivity;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.common.base.FragmentBase;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 设置界面
 * 设置界面
 */
public class SettingFragment extends FragmentBase {


    @BindView(R.id.ll_user)
    LinearLayout llUser;
    @BindView(R.id.rl_my_follow)
    RelativeLayout rlMyFollow;
    @BindView(R.id.rl_my_like)
    RelativeLayout rlMyLike;
    Unbinder unbinder;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.iv_icon)
    CircleImageView iv_icon;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;


    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (Constant.ISLOGIN) {
            if (!("".equals(Constant.NICKNAME))) {
                tv_name.setText(Constant.NICKNAME);
            }
            if (!"".equals(Constant.HEADPIC)&&Constant.HEADPIC!=null) {
                GlideUtils.load(this,Constant.HEADPIC,iv_icon);
            }
        } else {
            tv_name.setText("点击登录账号");
            iv_icon.setImageResource(R.drawable.headportrait);

        }
    }

    private void initData() {

    }

    private void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_user, R.id.rl_my_follow, R.id.rl_my_like,R.id.iv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_user:
                if (Constant.ISLOGIN) {
                    mContext.startActivity(new Intent(mContext, AccountSettingActivity.class));
                } else {
                    mContext.startActivity(new Intent(mContext, LoginActivity.class));

                }
                break;
            //我关注的
            case R.id.rl_my_follow:
                mContext.startActivity(new Intent(mContext, FollowActivity.class));

                break;
            //我喜欢的
            case R.id.rl_my_like:
                mContext.startActivity(new Intent(mContext, FavActivity.class));
                break;
            case R.id.iv_setting:
                FragmentActivity activity = SettingFragment.this.getActivity();
                activity.startActivity(new Intent(activity, SettingSecondActivity.class));

                break;
        }
    }
}
