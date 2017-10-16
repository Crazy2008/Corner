package com.jiao.luo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.canyinghao.canrefresh.CanRefreshLayout;
import com.canyinghao.canrefresh.classic.RotateRefreshView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiao.luo.Decoration.RvDividerItemDecoration;
import com.jiao.luo.R;
import com.jiao.luo.Response.Follow;
import com.jiao.luo.Response.ResNotice;
import com.jiao.luo.adapter.FollowAdapter;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.DensityUtil;
import com.jiao.luo.utils.PopupUtils;
import com.jiao.luo.utils.ToastUtils;
import com.jiao.luo.widget.SortPopupWindow;
import com.lzy.okgo.OkGo;

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
 * 我关注的提醒
 */
public class FollowActivity extends ActivityCommBase implements FollowAdapter.WytListener, CanRefreshLayout.OnLoadMoreListener, BaseQuickAdapter.OnItemClickListener, View.OnClickListener {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_sort)
    ImageView ivSort;
    @BindView(R.id.iv_search)
    ImageView ivSearch;

    @BindView(R.id.can_refresh_footer)
    RotateRefreshView canRefreshFooter;
    @BindView(R.id.can_content_view)
    RecyclerView rv;
    @BindView(R.id.refresh)
    CanRefreshLayout refresh;

    @BindView(R.id.rl_header)
    RelativeLayout rl_header;

    private int page = 1;
    private int limit = 10;
    //    排序 1：关注先后 2：更新时间
    private int sort = 1;
    private FollowAdapter followAdapter;
    private List<Follow.DataBean> dataList = new ArrayList<>();


    private boolean isOne = true;
    private SortPopupWindow popupWindow;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        OkGo.post(Constant.FOLLOWLIST)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("page", page)
                .params("limit", limit)
                .params("sort", sort)
                .execute(new SimpleCommonCallback<Follow>(this) {
                    @Override
                    public void onSuccess(Follow follow, Call call, Response response) {
                        if (follow.getReturnCode() == 1) {
                            List<Follow.DataBean> data = follow.getData();
                            if (data == null || data.size() == 0) {
                                refresh.loadMoreComplete();
                                return;
                            }
                            dataList.addAll(data);
                            followAdapter.setNewData(dataList);
                        }
                    }
                });


    }

    private void initView() {

        refresh.setStyle(CanRefreshLayout.LOWER, CanRefreshLayout.LOWER);
        refresh.setMaxFooterHeight(300);
        refresh.setOnLoadMoreListener(this);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        //分割线
        rv.addItemDecoration(new RvDividerItemDecoration(this, GridLayoutManager.VERTICAL));
        rv.setLayoutManager(gridLayoutManager);

        followAdapter = new FollowAdapter(R.layout.activity_follow_item, null, this, this);
        followAdapter.setOnItemClickListener(this);
        rv.setAdapter(followAdapter);
    }

    @OnClick({R.id.iv_back, R.id.iv_sort, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_sort:
                popupWindow = new SortPopupWindow(this, this, isOne);
                popupWindow.showAtLocation(refresh, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, DensityUtil.px2dip(this, 45));
                PopupUtils.setBg(this, popupWindow);

                break;
            case R.id.iv_search:

                startActivityForResult(new Intent(this, FavSearchActivity.class), Constant.RequestCode);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestCode && resultCode == RESULT_OK) {
            favSearchForNet();
        }
        if (requestCode == Constant.Code && resultCode == RESULT_OK) {
            String is_notice = data.getStringExtra(Constant.U);
            if (is_notice!=null&&!is_notice.equals("")) {
                dataList.get(mPosition).setIs_notice(is_notice);
                followAdapter.setNewData(dataList);
            }
            String is_follow = data.getStringExtra(Constant.IS_FOLLOW);
            if(is_follow!=null&&!is_follow.equals("")){
                if(is_follow.equals("未关注")){
                    dataList.remove(mPosition);
                    followAdapter.setNewData(dataList);
                }
            }
        }

    }


    //是否选择推送的
    @Override
    public void onItem(int pos) {
        List<Follow.DataBean> data = followAdapter.getData();
        String typeid = "";
        String is_notice = "";
        for (int i = 0; i < data.size(); i++) {
            if (pos == i) {
                Follow.DataBean dataBean = data.get(i);
                String is_follow = dataBean.getIs_notice();
                if ("-1".equals(is_follow)) {
                    dataBean.setIs_notice("1");
                } else {
                    dataBean.setIs_notice("-1");
                }
                typeid = dataBean.getTypeid();
                is_notice = dataBean.getIs_notice();
            }
        }

        followAdapter.setData(pos, data.get(pos));
        //关注话题是否选择推送
        noticeForNet(typeid, is_notice);
    }

    private void noticeForNet(String typeid, String is_notice) {

        OkGo.post(Constant.FOLLOWPUSH)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("catid", Integer.parseInt(typeid))
                .params("is_notice", Integer.parseInt(is_notice))
                .execute(new SimpleCommonCallback<ResNotice>(this) {
                    @Override
                    public void onSuccess(ResNotice resNotice, Call call, Response response) {
                        if (resNotice.getReturnCode() == 1) {
                            ResNotice.DataBean data = resNotice.getData();
                            if (data.getIs_notice().equals("1")) {
                                ToastUtils.showToast(FollowActivity.this, "推送开启");
                            } else {
                                ToastUtils.showToast(FollowActivity.this, "推送关闭");
                            }
                        } else {
                            toast("访问失败");
                        }
                    }
                });
    }

    //上拉加载
    @Override
    public void onLoadMore() {
        refresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                //从新加载数据
                initData();
                refresh.loadMoreComplete();
            }
        }, 1000);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPosition = position;
        List<Follow.DataBean> data = followAdapter.getData();
        Follow.DataBean dataBean = data.get(position);
        String typeid = dataBean.getTypeid();
        Intent intent = new Intent(this, CateDetailActivity.class);
        intent.putExtra(Constant.TYPEID, typeid);
        startActivityForResult(intent, Constant.Code);

    }


    //排序的popup传递过来的选项点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_1:
                page = 1;
                isOne = true;
                dataList.clear();
                sort = 1;
                closePopup();
                initData();
                break;
            case R.id.ll_2:
                page = 1;
                dataList.clear();
                sort = 2;
                isOne = false;
                closePopup();
                initData();
                break;


        }
    }

    public void closePopup() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    public void favSearchForNet() {
        OkGo.post(Constant.FOLLOWSEARCH)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("keyword", Constant.keyword)
                .params("page", page)
                .params("limit", limit)
                .params("sort", sort)
                .execute(new SimpleCommonCallback<Follow>(this) {
                    @Override
                    public void onSuccess(Follow follow, Call call, Response response) {
                        if (follow.getReturnCode() == 1) {
                            List<Follow.DataBean> data = follow.getData();
                            if (data != null && data.size() > 0) {
                                followAdapter.setNewData(data);
                            } else {
                                toast("未搜到相应内容");
                            }

                        } else {
                            toast(follow.getMessage());
                        }
                    }
                });
    }
}
