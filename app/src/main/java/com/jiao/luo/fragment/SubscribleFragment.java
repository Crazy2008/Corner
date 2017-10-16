package com.jiao.luo.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResPub;
import com.jiao.luo.Response.ResSubcrible;
import com.jiao.luo.adapter.SubcribleAdapter;
import com.jiao.luo.interf.WytOnItemClickListener;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.GlideUtils;
import com.jiao.luo.utils.IntentUtils;
import com.jiao.luo.utils.PopupUtils;
import com.jiao.luo.utils.ToastUtils;
import com.jiao.luo.utils.ZF_PopupWindow;
import com.lzy.okgo.OkGo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.common.base.FragmentBase;
import cn.common.http2.callback.SimpleCommonCallback;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 订阅
 */
public class SubscribleFragment extends FragmentBase implements WytOnItemClickListener, BaseQuickAdapter.OnItemClickListener, OnRefreshListener, OnLoadmoreListener {


    private RecyclerView rv;
    private int page = 1;
    private int limit = 10;
    private SubcribleAdapter subcribleAdapter;
    private TextView tv_name;
    private TextView tv_desc;
    private ImageView iv_img;
    private List<ResSubcrible.DataBean.SubdataBean> datas = new ArrayList<>();


    private SmartRefreshLayout refresh;
    //是否是第一次加载  是第一次更新头布局   不是第一次 不更新头布局
    private LinearLayout ll_content;

    public SubscribleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subscrible, container, false);
        refresh = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        rv = (RecyclerView) view.findViewById(R.id.can_content_view);

        initView();
        initData();

        return view;

    }



    private void initData() {
        OkGo.post(Constant.SUBSCRIBE)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("page", page)
                .params("limit", limit)
                .execute(new SimpleCommonCallback<ResSubcrible>(this) {
                    @Override
                    public void onSuccess(ResSubcrible resSubcrible, Call call, Response response) {
                        if (resSubcrible.getReturnCode() == 1) {
                            ResSubcrible.DataBean.IndexTypeBean index_type = resSubcrible.getData().getIndex_type();
                            List<ResSubcrible.DataBean.SubdataBean> subdata = resSubcrible.getData().getSubdata();
                            setHeaderData(index_type);
                            if (subdata != null && subdata.size() > 0) {
                                datas.addAll(subdata);
                                subcribleAdapter.setNewData(datas);
                            }else{
                               /* TextView textView = new TextView(SubscribleFragment.this.getActivity());
                                textView.setText("没有登录");*/
//                                subcribleAdapter.setEmptyView(R.layout.activity_account_setting);
                            }
                            //设置头布局数据
                        } else {
                            ToastUtils.showToast(SubscribleFragment.this.getActivity(),resSubcrible.getMessage());
                            if(resSubcrible.getMessage().equals("请先登录")){
                                IntentUtils.intentLogin(SubscribleFragment.this.getActivity());
                            }
                        }

                    }
                });


    }

    //设置顶部布局的数据
    private void setHeaderData(ResSubcrible.DataBean.IndexTypeBean bean) {
        if (bean == null) return;
        tv_name.setText(bean.getName());
        tv_desc.setText(bean.getDesc());
        GlideUtils.load(this, bean.getImgs(), iv_img);

        final String typeid = bean.getTypeid();

        ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.intentDetail(mContext, typeid);
            }
        });

    }

    private void initView() {

        refresh.setOnRefreshListener(this);
        refresh.setOnLoadmoreListener(this);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1, GridLayoutManager.VERTICAL, false);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(mContext);
        //分割线
//        rv.addItemDecoration(new RvDividerItemDecoration(mContext, GridLayoutManager.VERTICAL));
        rv.setLayoutManager(gridLayoutManager);
        subcribleAdapter = new SubcribleAdapter(mContext, datas, this);
//        头布局
        View headerView = View.inflate(mContext, R.layout.activity_subscrible_header, null);
        initHeaderView(headerView);

        subcribleAdapter.addHeaderView(headerView);

        subcribleAdapter.setOnItemClickListener(this);
        subcribleAdapter.bindToRecyclerView(rv);


    }

    //初始化headerView控件
    private void initHeaderView(View headerView) {
        ll_content = (LinearLayout) headerView.findViewById(R.id.ll_content);
        tv_name = (TextView) headerView.findViewById(R.id.tv_name);
        tv_desc = (TextView) headerView.findViewById(R.id.tv_desc);
        iv_img = (ImageView) headerView.findViewById(R.id.iv_img);
    }

    @Override
    public void onItem(int pos) {
        List<ResSubcrible.DataBean.SubdataBean> data = subcribleAdapter.getData();
        ResSubcrible.DataBean.SubdataBean subdataBean = data.get(pos);
        String typeid = subdataBean.getTypeid();
        IntentUtils.intentDetail(mContext, typeid);
//        goWebView(pos);



    }

    @Override
    public void onFavItem(int position) {


        if(!Constant.ISLOGIN){
            ToastUtils.showToast(SubscribleFragment.this.getActivity(),"请先登录");
            return;
        }
        List<ResSubcrible.DataBean.SubdataBean> datas = subcribleAdapter.getData();
        String id = datas.get(position).getNews_id();
        ResSubcrible.DataBean.SubdataBean bean = null;
        for (int i = 0; i < datas.size(); i++) {
            if (position == i) {
                bean = datas.get(position);
                int is_fav = bean.getIs_fav();
                if (is_fav == -1) {
                    //收藏数量+1
                    bean.setFav_count(bean.getFav_count() + 1);
                    bean.setIs_fav(1);
                } else {
                    //如果大于0  收藏数量-1
                    if (bean.getFav_count() > 0) {
                        bean.setFav_count(bean.getFav_count() - 1);
                    }
                    bean.setIs_fav(-1);
                }
            }
        }
        subcribleAdapter.setData(position, bean);

        int value = Integer.parseInt(id);
        OkGo.post(Constant.NEWSFAV).headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("newsid", value).execute(new SimpleCommonCallback<ResPub>(this) {
            @Override
            public void onSuccess(ResPub resPub, Call call, Response response) {
                if (resPub.getReturnCode() == 1) {
                    ToastUtils.showToast(mContext, resPub.getMessage());
                }
            }
        });

    }

    @Override
    public void onCommentItem(int pos) {
        List<ResSubcrible.DataBean.SubdataBean> data = subcribleAdapter.getData();
        String news_id = data.get(pos).getNews_id();
        IntentUtils.intentComment(this.getActivity(), news_id);
    }

    //转发
    @Override
    public void onRelayItem(int pos) {


        ZF_PopupWindow window = new ZF_PopupWindow(this.getActivity());
        window.show(rv);
        PopupUtils.setBg(this.getActivity(), window);
    }

    @Override
    public void onOriginItem(int position) {
        goWebView(position);
    }

    //adapter条目点击事件
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        goWebView(position);


    }

    private void goWebView(int position) {
        List<ResSubcrible.DataBean.SubdataBean> data = subcribleAdapter.getData();
        ResSubcrible.DataBean.SubdataBean subdataBean = data.get(position);
        String url = subdataBean.getUrl();
        int fav_count = subdataBean.getFav_count();
        int comment_count = subdataBean.getComment_count();
        String news_id = subdataBean.getNews_id();
        //跳转到原文
        IntentUtils.intentWeb(mContext, url, fav_count, comment_count, Integer.parseInt(news_id));
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(1000);
        page = 1;
        initData();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        //从新加载数据
        initData();
        refreshlayout.finishLoadmore(1000);
    }
}
