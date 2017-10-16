package com.jiao.luo.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiao.luo.Decoration.SpacesItemDecoration;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResComment;
import com.jiao.luo.Response.ResPub;
import com.jiao.luo.adapter.CommentTwoAdapter;
import com.jiao.luo.interf.PubListener;
import com.jiao.luo.utils.Constant;
import com.lzy.okgo.OkGo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.common.base.ActivityCommBase;
import cn.common.http2.callback.SimpleCommonCallback;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 大操场投票的评论
 */
public class VoteCommentActivity extends ActivityCommBase implements OnLoadmoreListener, OnRefreshListener, PubListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_mid_titlt)
    TextView tvMidTitlt;
    @BindView(R.id.can_content_view)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.btn_send)
    Button btnSend;
    private CommentTwoAdapter twoAdapter;
    private List<ResComment.DataBean.ListBean> datgList = new ArrayList<>();
    private String sid;
    private int page = 1;
    private int limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_comment);
        ButterKnife.bind(this);
        sid = getIntent().getStringExtra(Constant.SID);
        initView();
        initData();

    }

    private void initData() {
        //请求评论的接口
        OkGo.post(Constant.COMMENTS)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("sid", Integer.parseInt(sid))
                .params("page", page)
                .params("limit", limit)
                .execute(new SimpleCommonCallback<ResComment>(this) {
                    @Override
                    public void onSuccess(ResComment resComment, Call call, Response response) {
                        ResComment.DataBean data = resComment.getData();
                        if (data != null) {
                            List<ResComment.DataBean.ListBean> list = data.getList();
                            datgList.addAll(list);
                            twoAdapter.setNewData(datgList);
                        }
                    }
                });


    }

    private void initView() {

        refresh.setOnLoadmoreListener(this);
        refresh.setOnRefreshListener(this);


        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new SpacesItemDecoration(10));
        twoAdapter = new CommentTwoAdapter(R.layout.activity_comment_horizontal, null, this, this);
        rv.setAdapter(twoAdapter);
    }

    @OnClick({R.id.iv_back, R.id.refresh, R.id.et_comment, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.refresh:
                break;
            case R.id.et_comment:
                break;
            //大操场投票接口
            case R.id.btn_send:
                add_comment();
                break;
        }
    }

    //大操场投票接口
    private void add_comment() {
        String contents = etComment.getText().toString().trim();
        OkGo.post(Constant.ADD_COMMENTS)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("sid", sid)
                .params("contents", contents)
                .execute(new SimpleCommonCallback<ResPub>(this) {
                    @Override
                    public void onSuccess(ResPub resPub, Call call, Response response) {
                        if (resPub.getReturnCode() == 1) {
                            toast("评论成功");
                            etComment.setText("");
                            datgList.clear();
                            page = 1;
                            initData();
                        }

                    }
                });

    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshlayout.finishLoadmore(1000);
        page++;
        initData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(1000);
        datgList.clear();
        page = 1;
        initData();
    }


    @Override
    public void onItem(int postion, int type) {
        postion=postion+1;
        List<ResComment.DataBean.ListBean> data = twoAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            if (i == postion) {
                ResComment.DataBean.ListBean listBean = data.get(i);
                listBean.setSup(!listBean.isSup());
            }
        }
        twoAdapter.setData(postion,data.get(postion));
    }
}
