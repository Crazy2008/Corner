package com.jiao.luo.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiao.luo.Decoration.RvDividerItemDecoration;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResPub;
import com.jiao.luo.Response.ResPubComment;
import com.jiao.luo.adapter.PublicCommentAdapter;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.ToastUtils;
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

public class PublicCommentActivity extends ActivityCommBase implements  OnLoadmoreListener, OnRefreshListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.can_content_view)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.et_comment)
    EditText etComment;

    @BindView(R.id.btn_send)
    Button btn_send;

    private int page = 1;
    private int limit = 10;
    private String newsid;
    private PublicCommentAdapter adapter;
    private List<ResPubComment.DataBean.ListBean> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_comment);
        ButterKnife.bind(this);
        newsid = getIntent().getStringExtra(Constant.NEWID);
        initView();
        initData();
    }
    private void initData() {
        OkGo.post(Constant.COMMENTLIST)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                //TODO
                .params("newsid", newsid)
                .params("page", page)
                .params("limit", limit)
                .execute(new SimpleCommonCallback<ResPubComment>(this) {
                    @Override
                    public void onSuccess(ResPubComment resPubComment, Call call, Response response) {
                        if (resPubComment.getReturnCode() == 1) {
                            List<ResPubComment.DataBean.ListBean> list = resPubComment.getData().getList();
                            if (list != null && list.size() != 0) {
                                dataList.addAll(list);
                                adapter.setNewData(dataList);
                            }
                        }else{
                            ToastUtils.showToast(PublicCommentActivity.this,resPubComment.getMessage());
                        }

                    }
                });


    }

    private void initView() {
        etComment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.ACTION_DOWN)) {
                    return true;
                }
                return false;
            }
        });
        refresh.setOnLoadmoreListener(this);
        refresh.setOnRefreshListener(this);



        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        //分割线
        rv.addItemDecoration(new RvDividerItemDecoration(this, GridLayoutManager.VERTICAL));
        rv.setLayoutManager(gridLayoutManager);

        adapter = new PublicCommentAdapter(R.layout.activity_comment_horizontal_2 , this, null);
        rv.setAdapter(adapter);


    }




    @OnClick({R.id.iv_back, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_send:
                commentAdd();
                break;
        }
    }

    /**
     * 添加评论
     */
    private void commentAdd() {
        if(!Constant.ISLOGIN){
            ToastUtils.showToast(this,"请先登录");
            return;
        }
        String content= etComment.getText().toString().trim();
        OkGo.post(Constant.COMMENTADD)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("newsid", newsid)
                .params("content", content)
                .execute(new SimpleCommonCallback<ResPub>(this) {
                    @Override
                    public void onSuccess(ResPub resPub, Call call, Response response) {
                        if(resPub.getReturnCode()==1){
                            ToastUtils.showToast(PublicCommentActivity.this,"评论成功");
                            etComment.setText("");
                            dataList.clear();
                            setResult(RESULT_OK);
                            initData();
                        }else{
                            ToastUtils.showToast(PublicCommentActivity.this,resPub.getMessage());
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
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(1000);
        page=1;
        dataList.clear();
        initData();
    }
}
