package com.jiao.luo.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiao.luo.R;
import com.jiao.luo.Response.ResPub;
import com.jiao.luo.Response.ResRegister;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.ToastUtils;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.common.base.ActivityCommBase;
import cn.common.http2.callback.SimpleCommonCallback;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 填写资料的界面
 */
public class WriteDataActivity extends ActivityCommBase {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_sms)
    TextView tvSms;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_done)
    Button btnDone;

    @BindView(R.id.civ_icon)
    CircleImageView civ_icon;

    @BindView(R.id.et_smsCode)
    EditText et_smsCode;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.btn_sms)
    Button btn_sms;
    private String mobile;
    private String smsCode;

    private boolean runningThree = true;
    private CountDownTimer downTimer = new CountDownTimer(150 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            runningThree = true;
            btn_sms.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            btn_sms.setText((l / 1000) + "秒");

        }

        @Override
        public void onFinish() {
            runningThree = false;
            btn_sms.setText("重新发送");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_data);
        ButterKnife.bind(this);
        if (Constant.ISREGISTER) {
            tv_title.setText("填写资料");
            etNickname.setHint("请输入昵称");
            etPassword.setHint("请输入6--16位密码");

        } else if (Constant.ISLOSE) {
            tv_title.setText("重置密码");
            civ_icon.setVisibility(View.GONE);
            etNickname.setHint("请输入6--16位新密码");
            etNickname.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etPassword.setHint("请再次输入密码");

        } else {

            //密码格式

            tv_title.setText("修改密码");
            civ_icon.setVisibility(View.GONE);
            etNickname.setHint("请输入6--16位新密码");
            etNickname.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etPassword.setHint("请再次输入密码");
        }
        Intent intent = getIntent();
        smsCode = String.valueOf(intent.getIntExtra(Constant.SMSCODE, -1));
        mobile = intent.getStringExtra(Constant.MOBILE);

        String beginNum = mobile.substring(0, 3);
        String endNum = mobile.substring(7, mobile.length());
        tvSms.setText("验证码已发送至" + beginNum + "****" + endNum);


    }

    @OnClick({R.id.iv_back, R.id.btn_done, R.id.btn_sms})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_done:
                //请求接口
                if (Constant.ISREGISTER) {
                    registerData();
                } else if (Constant.ISLOSE) {
                    //重置密码
                    resetPwd();
                } else {
                    //修改密码
                    motifyPwd();
                }

                break;
            case R.id.btn_sms:
                if (runningThree) {
                } else {
                    downTimer.start();
                }
                break;
        }
    }

    /*重置密码*/
    private void resetPwd() {
        String code = et_smsCode.getText().toString().trim();
        String nickName = etNickname.getText().toString().trim();
        String pwd = etPassword.getText().toString().trim();


        if (nickName.length() < 6 || nickName.length() > 16) {
            toast("密码长度不正确");
            return;
        } else if (pwd.length() < 6 || pwd.length() > 16) {
            toast("密码长度不正确");
            return;
        }
        if (!nickName.equals(pwd)) {
            toast("两次输入密码不一致");
            etNickname.setText("");
            etPassword.setText("");
            return;
        }
        OkGo.post(Constant.RESETPWD)
                .params("mobile", mobile)
                .params("smsCode", code)
                .params("newPassword", pwd)
                .execute(new SimpleCommonCallback<ResPub>(this) {
                    @Override
                    public void onSuccess(ResPub resPub, Call call, Response response) {
                        if (resPub.getReturnCode() == 1) {
                            toast("重置密码成功");
                            WriteDataActivity.this.finish();
//                            startActivity(new Intent(WriteDataActivity.this,AccountSettingActivity.class));
                        } else {
                            toast(resPub.getMessage());

                        }
                    }
                });
    }

    //修改密码
    private void motifyPwd() {
        String nickName = etNickname.getText().toString().trim();
        String pwd = etPassword.getText().toString().trim();
        String code = et_smsCode.getText().toString().trim();
        if (nickName.length() < 6 || nickName.length() > 16) {
            toast("密码长度不正确");
            return;
        } else if (pwd.length() < 6 || pwd.length() > 16) {
            toast("密码长度不正确");
            return;
        }
        if (!nickName.equals(pwd)) {
            toast("两次输入密码不一致");
            etNickname.setText("");
            etPassword.setText("");
            return;
        }


        if (!Constant.ISREGISTER) {
            if (!nickName.equals(pwd)) {
                toast("两次输入密码不一致");
                etNickname.setText("");
                etPassword.setText("");
                return;
            }
        }

        OkGo.post(Constant.EDITPWD)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("mobile", mobile)
                .params("smsCode", code)
                .params("newPassword", pwd)
                .execute(new SimpleCommonCallback<ResPub>(this) {
                    @Override
                    public void onSuccess(ResPub resPub, Call call, Response response) {
                        if (resPub.getReturnCode() == 1) {
                            toast("修改密码成功");
                            WriteDataActivity.this.finish();
//                            startActivity(new Intent(WriteDataActivity.this,AccountSettingActivity.class));
                        } else {
                            toast(resPub.getMessage());

                        }
                    }
                });
    }

    //请求接口，注册
    private void registerData() {
        String nickname = etNickname.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        String code = et_smsCode.getText().toString().trim();
        if(nickname.equals("")){
            ToastUtils.showToast(this,"昵称不能为空");
            return;
        }
        if (password.length() < 6  ) {
            ToastUtils.showToast(this,"密码长度不能少于6位");
            return;
        }
        if (password.length() >16 ) {
            ToastUtils.showToast(this,"密码长度不能大于16位");
            return;
        }

/*
 HttpParams httpParams = new HttpParams();
        httpParams.put("mobile",mobile);
        httpParams.put("smsCode",smsCode);
        httpParams.put("nickname ",nickname);
        httpParams.put("password",password);*/
        OkGo.post(Constant.RIGSITER)
                .params("mobile", mobile)
                .params("smsCode", code)
                .params("nickname", nickname)
                .params("password", password)
                .params("eqid_type", 2)
                .params("eqid", Constant.eqid)
                .execute(new SimpleCommonCallback<ResRegister>(this) {
                    @Override
                    public void onSuccess(ResRegister data, Call call, Response response) {
                        //如果注册成功，跳转到登录页面
                        if (data.getReturnCode() == 1) {
                            //注册成功请登录
                            toast("注册成功请登录");
                            Intent intent = new Intent(WriteDataActivity.this, LoginActivity.class);
                            intent.putExtra(Constant.MOBILE, mobile);
                            intent.putExtra(Constant.PASSWORD, password);
                            startActivity(intent);

                        } else {
                            toast(data.getMessage());
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Constant.ISREGISTER = false;
        Constant.ISLOSE = false;
    }
}
