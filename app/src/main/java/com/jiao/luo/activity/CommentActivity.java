package com.jiao.luo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiao.luo.Decoration.SpacesItemDecoration;
import com.jiao.luo.R;
import com.jiao.luo.Response.CommentDetail;
import com.jiao.luo.Response.ResComment;
import com.jiao.luo.Response.ResPub;
import com.jiao.luo.adapter.CommentOneAdapter;
import com.jiao.luo.adapter.CommentTwoAdapter;
import com.jiao.luo.interf.PubListener;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.GlideUtils;
import com.jiao.luo.utils.PopupUtils;
import com.jiao.luo.utils.ZF_PopupWindow;
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
 * 评论界面
 */
public class CommentActivity extends ActivityCommBase implements OnRefreshListener, OnLoadmoreListener, PubListener {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refresh;
    private CommentOneAdapter oneAdapter;
    private boolean isLoadMore = false;


    private String sid;
    private int page = 1;
    private int limit = 10;
    private ImageView iv_banner;
    private TextView tv_title;
    private TextView tv_desc;
    private List<ResComment.DataBean.ListBean> oneList = new ArrayList<>();
    private List<ResComment.DataBean.ListBean> twoList = new ArrayList<>();
    private CommentDetail.DataBean data;
    private GridLayoutManager layoutManager;
    private CommentTwoAdapter twoAdapter;
    private View header_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        sid = getIntent().getStringExtra(Constant.ID);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    //大操场的详情
    private void initData() {
        OkGo.post(Constant.SPECIAL_DETAIL)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("sid", Integer.parseInt(sid))
                .execute(new SimpleCommonCallback<CommentDetail>(this) {
                    @Override
                    public void onSuccess(CommentDetail resSportDetail, Call call, Response response) {
                        if (resSportDetail.getData() != null) {
                            data = resSportDetail.getData();
                            setHeaderData();
                        }
                        //请求评论的接口
                        getCommentDataForNet();
                    }
                });


    }

    //设置头部数据
    private void setHeaderData() {
        tv_title.setText(data.getTitle());
        tv_desc.setText(data.getDescription());
        GlideUtils.load(this, data.getBanner(), iv_banner);
    }

    //请求评论的接口
    private void getCommentDataForNet() {
        OkGo.post(Constant.COMMENTS)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("sid", Integer.parseInt(sid))
                .params("page", page)
                .params("limit", limit)
                .execute(new SimpleCommonCallback<ResComment>(this) {
                    @Override
                    public void onSuccess(ResComment resComment, Call call, Response response) {
                        if (resComment.getReturnCode() == 1) {
                            ResComment.DataBean data = resComment.getData();
                            if (data != null) {
                                List<ResComment.DataBean.ListBean> list = data.getList();
                                if (isLoadMore) {
                                    twoList.addAll(list);
                                    twoAdapter.setNewData(twoList);
                                } else {
                                    handleData(list);
                                    oneAdapter.setNewData(oneList);
                                    twoAdapter.setNewData(twoList);
                                }
                            } else {
                                if (!isLoadMore) {
                                    oneList.add(new ResComment.DataBean.ListBean());
                                    oneAdapter.setNewData(oneList);
                                    toast("暂无其他评论");
                                } else {
                                    toast("暂无其他评论");
                                }
                            }
                        }
                            isLoadMore=false;

                    }
                });
    }

    private void handleData(List<ResComment.DataBean.ListBean> commentList) {
        //第一条是空数据
        ResComment.DataBean.ListBean listBean = new ResComment.DataBean.ListBean();
        oneList.add(listBean);
        for (int i = 0; i < commentList.size(); i++) {
            if (i < 7) {
                oneList.add(commentList.get(i));
            } else {
                twoList.add(commentList.get(i));
            }
        }
    }

   /* //设置数据类型
    private List<ResComment.DataBean.ListBean> handleData(List<ResComment.DataBean.ListBean> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                ResComment.DataBean.ListBean listBean = list.get(i);
                listBean.setIsWhat(Constant.ONE);
                continue;
            }
            if (i <= 8) {
                ResComment.DataBean.ListBean listBean = list.get(i);
                listBean.setIsWhat(Constant.NORMAL);
                continue;
            }
            ResComment.DataBean.ListBean listBean = list.get(i);
            listBean.setIsWhat(Constant.HORIZONTAL);
        }
        return list;
    }*/

    private void initView() {

        refresh.setOnRefreshListener(this);
        refresh.setOnLoadmoreListener(this);

        //中间的rv
        header_view = View.inflate(this, R.layout.comment_header, null);
        RecyclerView rv_header = (RecyclerView) header_view.findViewById(R.id.rv_header);
        rv_header.setLayoutManager(new GridLayoutManager(this, 2));
        rv_header.addItemDecoration(new SpacesItemDecoration(10));
        oneAdapter = new CommentOneAdapter(R.layout.activity_comment_item, null, this, this, Integer.parseInt(sid));
        iv_banner = (ImageView) header_view.findViewById(R.id.iv_banner);
        tv_title = (TextView) header_view.findViewById(R.id.tv_title);
        tv_desc = (TextView) header_view.findViewById(R.id.tv_desc);


        //下侧的rv
        layoutManager = new GridLayoutManager(this, 1);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new SpacesItemDecoration(10));
        rv_header.setAdapter(oneAdapter);
        twoAdapter = new CommentTwoAdapter(R.layout.activity_comment_horizontal, null, this, this);
        twoAdapter.addHeaderView(header_view);
        rv.setAdapter(twoAdapter);

    }


    @OnClick({R.id.iv_back, R.id.iv_comment, R.id.iv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_comment:
                Intent intent = new Intent(this, WriteActivity.class);
                intent.putExtra(Constant.SID, Integer.parseInt(sid));
                this.startActivityForResult(intent,Constant.RequestCode);

                break;
            case R.id.iv_share:
                ZF_PopupWindow window = new ZF_PopupWindow(this);
                window.show(rv);
                PopupUtils.setBg(this, window);
                break;
        }
    }


    //点赞传过来的角标
    @Override
    public void onItem(int postion, int type) {
        List<ResComment.DataBean.ListBean> data = null;
        if (type == 1) {
            data = oneAdapter.getData();
        } else {
            data = twoAdapter.getData();
        }
        ResComment.DataBean.ListBean listBean = null;
        int method = -1;
        for (int i = 0; i < data.size(); i++) {
            if (postion == i) {
                listBean = data.get(i);
                if (!listBean.isSup()) {
                    listBean.setSup(true);
                    listBean.setSupport(String.valueOf(Integer.parseInt(listBean.getSupport()) + 1));
                    method = 1;
                } else {
                    listBean.setSup(false);
                    method = 2;
                    String support = listBean.getSupport();
                    if (Integer.parseInt(support) > 0) {
                        listBean.setSupport(String.valueOf(Integer.parseInt(support) - 1));
                    } else {
                        listBean.setSupport("0");
                    }
                }
            }
        }
        if (type == 1) {
            oneAdapter.setData(postion, listBean);
        } else {
            twoAdapter.setData(postion, listBean);
        }

        addSupport(listBean.getId(), method);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constant.RequestCode&&resultCode==RESULT_OK){
            page=1;
            oneList.clear();
            twoList.clear();
           getCommentDataForNet();
        }
    }

    //点赞或者取消点赞
    private void addSupport(String cid, int method) {

        OkGo.post(Constant.ADDSUPPORT)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("sid", sid)
                .params("cid", Integer.parseInt(cid))
                .params("method", method)
                .execute(new SimpleCommonCallback<ResPub>(this) {
                    @Override
                    public void onSuccess(ResPub resPub, Call call, Response response) {
                        if (resPub.getReturnCode() == 1) {
                        } else {
                            toast(resPub.getMessage());
                        }
                    }
                });

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(1000);
        page=1;
        oneList.clear();
        twoList.clear();
        getCommentDataForNet();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshlayout.finishLoadmore(1000);
        page++;
        isLoadMore = true;
        getCommentDataForNet();
    }

}
