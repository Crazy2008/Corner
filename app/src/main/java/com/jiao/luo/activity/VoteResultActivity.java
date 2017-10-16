package com.jiao.luo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jiao.luo.R;
import com.jiao.luo.Response.ResVoteResult;
import com.jiao.luo.adapter.VoteResultAdapter;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.PopupUtils;
import com.jiao.luo.utils.ZF_PopupWindow;
import com.lzy.okgo.OkGo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.common.base.ActivityCommBase;
import cn.common.http2.callback.SimpleCommonCallback;
import okhttp3.Call;
import okhttp3.Response;

import static com.jiao.luo.R.id.tv_title;

/**
 * 大操场投票结果
 */
public class VoteResultActivity extends ActivityCommBase {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_vote_count)
    TextView tvVoteCount;
    @BindView(R.id.lv)
    ListView lv;
    private int subjectid;
    private ResVoteResult.DataBean data;
    private VoteResultAdapter adapter;
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_result);
        ButterKnife.bind(this);
        subjectid = getIntent().getIntExtra(Constant.SUBJECTID, -1);
        title = getIntent().getStringExtra(Constant.TITLE);
        initView();
        initData();
    }

    private void initData() {
        OkGo.post(Constant.OPTION_RESULT)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("subjectid",subjectid)
                .execute(new SimpleCommonCallback<ResVoteResult>(this) {
                    @Override
                    public void onSuccess(ResVoteResult resVoteResult, Call call, Response response) {
                        if(resVoteResult.getReturnCode()==1){
                            ResVoteResult.DataBean data = resVoteResult.getData();
                            if(data!=null){
                            VoteResultActivity.this.data = data;
                            refreshUi();
                            }
                        }else{
                            toast(resVoteResult.getMessage());
                        }
                    }
                });

    }

    private void refreshUi() {
        //标题
        tvTitle.setText(title);
        //多少人参与投票
        tvVoteCount.setText("已参与人数:"+data.getVotes());
        List<ResVoteResult.DataBean.OptionsBean> options = data.getOptions();
        adapter.setDatas(options);
    }

    private void initView() {
        adapter = new VoteResultAdapter();
        lv.setAdapter(adapter);

    }

    @OnClick({R.id.iv_back, R.id.iv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                ZF_PopupWindow window = new ZF_PopupWindow(this);
                window.show(lv);
                PopupUtils.setBg(this,window);
                break;
        }
    }
}
