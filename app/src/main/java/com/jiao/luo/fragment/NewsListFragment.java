package com.jiao.luo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiao.luo.Decoration.RvDividerItemDecoration;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResFollow;
import com.jiao.luo.Response.ResMenu;
import com.jiao.luo.adapter.NewsListAdapter;
import com.jiao.luo.interf.MyOnItemClickListener;
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
 * 菜单新闻内容
 */
public class NewsListFragment extends FragmentBase implements MyOnItemClickListener, BaseQuickAdapter.OnItemClickListener, OnRefreshListener, OnLoadmoreListener {


    @BindView(R.id.can_content_view)
    RecyclerView rvNewIst;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refresh;
    Unbinder unbinder;
    private String catid;
    private int page = 1;
    private int limit = 10;
    private NewsListAdapter newsListAdapter;
    private List<ResMenu.DataBean.SubdataBean> datasList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("NewsListFragment", "NewsListFragmentonCreate");
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_newlist, container, false);
        unbinder = ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        catid = (String) bundle.get(Constant.FRAGMENT_DATA);
        initView();
        initData();
        return view;

    }

    private void initData() {

        OkGo.post(Constant.MENU_LIST)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("catid", catid)
                .params("page", page)
                .params("limit", limit)
                .execute(new SimpleCommonCallback<ResMenu>(this) {
                    @Override
                    public void onSuccess(ResMenu resMenu, Call call, Response response) {

                        if (resMenu.getReturnCode() == 1) {
                            ResMenu.DataBean data = resMenu.getData();
                            if (data == null) {
                                ToastUtils.showToast(NewsListFragment.this.getActivity(), "数据为空");
                                return;
                            }
                            List<ResMenu.DataBean.SubdataBean> subdata = data.getSubdata();
                            if (subdata == null || subdata.size() == 0) {
                                refresh.finishLoadmore();
                                return;
                            }
                            datasList.addAll(subdata);
                            newsListAdapter.setNewData(datasList);
                        } else {
                            ToastUtils.showToast(NewsListFragment.this.getActivity(),resMenu.getMessage());
                            if(resMenu.getMessage().equals("请先登录")){
                                IntentUtils.intentLogin(NewsListFragment.this.getActivity());
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
        rvNewIst.setLayoutManager(layoutManager);
        rvNewIst.addItemDecoration(new RvDividerItemDecoration(
                getActivity(), GridLayoutManager.VERTICAL));
        newsListAdapter = new NewsListAdapter(R.layout.fragment_home_item, null, mContext, this, this);

        newsListAdapter.setOnItemClickListener(this);
        rvNewIst.setAdapter(newsListAdapter);
    }

    @Override
    public void onItem(int pos) {
        List<ResMenu.DataBean.SubdataBean> data = newsListAdapter.getData();
        String typeid = "";
        for (int i = 0; i < data.size(); i++) {
            if (pos == i) {
                typeid = data.get(i).getTypeid();
                String is_follow = data.get(i).getIs_follow();
                if ("已关注".equals(is_follow)) {
                    data.get(i).setIs_follow("未关注");
                } else {
                    data.get(i).setIs_follow("已关注");
                }

            }
        }
        ResMenu.DataBean.SubdataBean subdataBean = data.get(pos);
        newsListAdapter.setData(pos, subdataBean);
        //关注/取消关注
        followForNet(typeid, false);
    }

    @Override
    public void onFavItem(int pos) {

    }

    @Override
    public void onCommentItem(int pos) {

    }

    @Override
    public void onRelayItem(int pos) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    //关注和取消关注
    private void followForNet(String typeid, final boolean isHeader) {
        if (!Constant.ISLOGIN) {
            ToastUtils.showToast(NewsListFragment.this.getActivity(), "请先登录");
            return;
        }
        OkGo.post(Constant.FOLLOWADD).headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("typeid", typeid).execute(new SimpleCommonCallback<ResFollow>(this) {
            @Override
            public void onSuccess(ResFollow resFollow, Call call, Response response) {
                if (resFollow.getReturnCode() == 1) {
                   /* if(isHeader){
                    findHeaderAdapter.notifyDataSetChanged();
                    }else{
                        findAdapter.notifyDataSetChanged();
                    }*/
                }

            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("NewsListFragment", "NewsListFragmentonDestroy");

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<ResMenu.DataBean.SubdataBean> data = newsListAdapter.getData();
        ResMenu.DataBean.SubdataBean subdataBean = data.get(position);
        String typeid = subdataBean.getTypeid();
        IntentUtils.intentDetail(mContext, typeid);


    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        initData();
        refreshlayout.finishLoadmore();
    }
}
