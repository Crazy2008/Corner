package com.jiao.luo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiao.luo.R;
import com.jiao.luo.Response.ResSmsCode;
import com.jiao.luo.utils.CheckFormUtils;
import com.jiao.luo.utils.Constant;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.common.base.ActivityCommBase;
import cn.common.http2.callback.SimpleCommonCallback;
import okhttp3.Call;
import okhttp3.Response;


public class RegisterActivity extends ActivityCommBase {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.send_code)
    Button sendCode;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        //如果是true表示是注册页面    false表示是修改密码界面
        if(Constant.ISREGISTER){
            tv_title.setText("注册");
        }else if(Constant.ISLOSE){
            tv_title.setText("找回密码");
        }
        else{
            tv_title.setText("修改密码");
        }


    }

    @OnClick({R.id.iv_back, R.id.send_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.send_code:
                final String mobile = etNumber.getText().toString().trim();
                //验证电话号码
                boolean is_phone = CheckFormUtils.isPhone(mobile);
                if(!is_phone){
                    toast("电话号码不规范");
                    return;
                }

                OkGo.post(Constant.GET_SMSCODE).params("mobile",mobile).execute(new SimpleCommonCallback<ResSmsCode>(this) {
                    @Override
                    public void onSuccess(ResSmsCode data, Call call, Response response) {
                        //请求成功
                      if(data.getReturnCode()==1){
                                //跳转到填写资料界面
                          int smsCode = data.getData().getSMSCode();
                          Intent intent = new Intent(RegisterActivity.this, WriteDataActivity.class);
                          //验证码和手机号
                          intent.putExtra(Constant.MOBILE,mobile);
                          intent.putExtra(Constant.SMSCODE,smsCode);
                          startActivity(intent);
                          RegisterActivity.this.finish();
                      }else{
                          toast(data.getMessage());
                      }
                    }
                });
                break;
        }
    }
}
