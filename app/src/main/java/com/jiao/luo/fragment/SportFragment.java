package com.jiao.luo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiao.luo.Decoration.RvDividerItemDecoration;
import com.jiao.luo.R;
import com.jiao.luo.activity.CommentActivity;
import com.jiao.luo.activity.VoteActivity;
import com.jiao.luo.adapter.SportAdapter;
import com.jiao.luo.model.Sport;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.IntentUtils;
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
import butterknife.Unbinder;
import cn.common.base.FragmentBase;
import cn.common.http2.callback.SimpleCommonCallback;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 大操场页面
 */
public class SportFragment extends FragmentBase implements OnRefreshListener, OnLoadmoreListener {


    @BindView(R.id.can_content_view)
    RecyclerView rv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refresh;
    Unbinder unbinder;
    private int page = 1;
    private int limit = 10;
    private int sort = 1;
    private List<Sport.DataBean> dataList = new ArrayList<>();
    private SportAdapter sportAdapter;

    public SportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sport, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }


    private void initData() {
        OkGo.post(Constant.SPECIAL_LIST)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("page", page)
                .params("limit", limit)
                .params("sort", sort)//排序 1：id 倒叙 2：后台排序倒叙
                .execute(new SimpleCommonCallback<Sport>(this) {
                    @Override
                    public void onSuccess(Sport sport, Call call, Response response) {
                        if (sport.getReturnCode() == 1) {
                            List<Sport.DataBean> data = sport.getData();
                            if (data != null && data.size() > 0) {
                                dataList.addAll(data);
                                sportAdapter.setNewData(dataList);
                            } else {
                                refresh.finishLoadmore();
                            }
                        } else {
                            ToastUtils.showToast(SportFragment.this.getActivity(),sport.getMessage());
                            if(sport.getMessage().equals("请先登录")){
                                IntentUtils.intentLogin(SportFragment.this.getActivity());
                            }
                        }
                    }
                });
    }


    private void initView() {
        //刷新初始化

        refresh.setOnRefreshListener(this);
        refresh.setOnLoadmoreListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);


        sportAdapter = new SportAdapter(R.layout.fragment_item_sport, null, this);
        rv.addItemDecoration(new RvDividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        rv.setAdapter(sportAdapter);


        sportAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                System.out.println("onItemClick = [" + adapter + "], view = [" + view + "], position = [" + position + "]");
                Sport.DataBean dataBean = dataList.get(position);
                //如果voteid为空字符串就是评论类型  不为空就是投票
                String voteid = dataBean.getVoteid();
                FragmentActivity activity = SportFragment.this.getActivity();

                if (voteid.equals("")) {
                    //大操场评论
                    Intent intent = new Intent(activity, CommentActivity.class);
                    intent.putExtra(Constant.ID, dataBean.getId());
                    activity.startActivity(intent);

                } else {
                    //大操场投票
                    Intent intent = new Intent(activity, VoteActivity.class);
//                    intent.putExtra(Constant.VOTE,voteid);
                    intent.putExtra(Constant.ID, dataBean.getId());
                    activity.startActivity(intent);
                }

            }
        });


    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page=1;
        dataList.clear();
        initData();
        refreshlayout.finishRefresh();

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        initData();
        refreshlayout.finishLoadmore(1000);
    }
}
