package com.jiao.luo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiao.luo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MotifyPwdActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.send_code)
    Button sendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        tvTitle.setText("修改密码");


    }

    @OnClick({R.id.iv_back, R.id.send_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.send_code:
                break;
        }
    }
}
