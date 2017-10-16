package com.jiao.luo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiao.luo.R;
import com.jiao.luo.Response.ResLogin;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.SPTools;
import com.jiao.luo.utils.ToastUtils;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.common.base.ActivityCommBase;
import cn.common.http2.callback.SimpleCommonCallback;
import okhttp3.Call;
import okhttp3.Response;


public class LoginActivity extends ActivityCommBase {

    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_password)
    EditText etPassword;
    //下次直接登录
    @BindView(R.id.cb_rember)
    CheckBox cbRember;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.forgot_pwd)
    TextView forgotPwd;
    @BindView(R.id.ll_weixin)
    LinearLayout llWeixin;
    @BindView(R.id.ll_weibo)
    LinearLayout llWeibo;
    @BindView(R.id.ll_QQ)
    LinearLayout llQQ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        //手机号码
        String mobile = intent.getStringExtra(Constant.MOBILE);
        String password = intent.getStringExtra(Constant.PASSWORD);

        if (!TextUtils.isEmpty(mobile)) {
            etMobile.setText(mobile);
        }
        if (!TextUtils.isEmpty(password)) {
            etPassword.setText(password);
        }


    }

    @OnClick({R.id.login, R.id.register, R.id.forgot_pwd, R.id.ll_weixin, R.id.ll_weibo, R.id.ll_QQ})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        switch (view.getId()) {
            case R.id.login:
                login();
                break;
            //注册
            case R.id.register:
                Constant.ISREGISTER = true;
                startActivity(intent);
                break;
            //忘记密码
            case R.id.forgot_pwd:
                Constant.ISLOSE = true;
                startActivity(intent);
                break;
            case R.id.ll_weixin:
                break;
            case R.id.ll_weibo:
                break;
            case R.id.ll_QQ:
                break;
        }
    }

    //登录请求
    private void login() {

        String mobile = getEditValue(etMobile);
        String password = getEditValue(etPassword);
        boolean checked = cbRember.isChecked();
        String remember = null;
        //是否 下次直接登录
        if (checked) {
            remember = "remember";
        }
        OkGo.post(Constant.LOGIN)
                .params("mobile", mobile)
                .params("password", password)
                .params("eqid_type", 2)
                .params("eqid", Constant.eqid)
                .params("remember", remember).execute(new SimpleCommonCallback<ResLogin>(this) {
            @Override
            public void onSuccess(ResLogin data, Call call, Response response) {
                if (data.getReturnCode() == 1) {
                    toast("登录成功");
                    ResLogin.DataBean bean = data.getData();
                    String token = bean.getToken();
                    int userid = bean.getUserid();
                    String catesid = bean.getCatesid();
                    String headPic = bean.getHeadPic();
                    String nickname = bean.getNickname();

                    Constant.ISLOGIN = true;
                    Constant.HEADPIC = headPic;
                    Constant.NICKNAME = nickname;
                    Constant.TOKEN = token;
                    Constant.USERID = userid;
                    Constant.CATEIDSID = catesid;


                    SPTools.saveString(LoginActivity.this, Constant.T, token);
                    SPTools.saveInt(LoginActivity.this, Constant.U, userid);
                    SPTools.saveBoolean(LoginActivity.this, Constant.I, true);
                    SPTools.saveString(LoginActivity.this, Constant.H, headPic);
                    SPTools.saveString(LoginActivity.this, Constant.N, nickname);

                    if (catesid != null && !catesid.equals("")) {
                        String[] split = catesid.split(",");
                        ArrayList<Integer> list = new ArrayList<>();
                        for (int i = 0; i < split.length; i++) {
                            list.add(Integer.parseInt(split[i]));
                        }
                        Constant.CATEIDSID_LIST.clear();
                        Constant.CATEIDSID_LIST.addAll(list);

                    }

                    //是否跳转到选择板块的界面
                    if (!bean.isIs_choise_cate()) {
                        startActivity(new Intent(LoginActivity.this, CateActivity.class));
                    } else {
                        //首页
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                } else {
                    ToastUtils.showToast(LoginActivity.this, "登录失败");
                }


            }
        });
    }
}
