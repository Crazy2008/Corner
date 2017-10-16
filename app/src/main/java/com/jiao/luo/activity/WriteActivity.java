package com.jiao.luo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.jiao.luo.R;
import com.jiao.luo.Response.ResPub;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.ToastUtils;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.common.base.ActivityCommBase;
import cn.common.http2.callback.SimpleCommonCallback;
import okhttp3.Call;
import okhttp3.Response;


/**
 * 发表意见的界面
 */
public class WriteActivity extends ActivityCommBase {

    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.publish)
    TextView publish;
    @BindView(R.id.et_write)
    EditText etWrite;
    private int sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        ButterKnife.bind(this);
        sid = getIntent().getIntExtra(Constant.SID,-1);
        etWrite.setFocusable(true);
        etWrite.setFocusableInTouchMode(true);
        etWrite.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @OnClick({R.id.tv_close,  R.id.publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_close:
                finish();
                break;

            //发表
            case R.id.publish:
                addComment();
                break;
        }
    }

    private void addComment() {
        if(!Constant.ISLOGIN){
            ToastUtils.showToast(this,"请先登录");
            return;
        }
        String contents = etWrite.getText().toString().trim();
        if(contents.equals("")){
            toast("请填写评论");
            return;
        }
        OkGo.post(Constant.ADD_COMMENTS)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("sid", sid )
                .params("contents", contents )
                .execute(new SimpleCommonCallback<ResPub>(this) {
                    @Override
                    public void onSuccess(ResPub resPub, Call call, Response response) {
                        if (resPub.getReturnCode()==1){
                            toast("评论成功");
                            WriteActivity.this.setResult(RESULT_OK);
                            WriteActivity.this.finish();
                        }else{
                            toast(resPub.getMessage());
                        }

                    }
                });
    }


}
