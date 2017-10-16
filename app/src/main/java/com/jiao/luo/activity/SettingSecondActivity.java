package com.jiao.luo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jiao.luo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingSecondActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.setting_new_version_Re)
    RelativeLayout settingNewVersionRe;
    @BindView(R.id.rl_clear)
    RelativeLayout rlClear;
    @BindView(R.id.rl_QQqun)
    RelativeLayout rlQQqun;
    @BindView(R.id.rl_service)
    RelativeLayout rlService;


    @BindView(R.id.rl_user_protocol)
    RelativeLayout rl_user_protocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_second);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.rl_clear, R.id.rl_QQqun, R.id.rl_service,R.id.rl_user_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_clear:
                break;
            case R.id.rl_QQqun:
                break;
            case R.id.rl_service:
                break;
            case R.id.rl_user_protocol:
                startActivity(new Intent(this,ProtocolActivity.class));
                break;
        }
    }
}
