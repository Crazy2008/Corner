package com.jiao.luo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiao.luo.Decoration.RvDividerItemDecoration;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResCateData;
import com.jiao.luo.Response.ResFollow;
import com.jiao.luo.Response.ResTopic;
import com.jiao.luo.Response.ResTopicHeader;
import com.jiao.luo.activity.CateDetailActivity;
import com.jiao.luo.adapter.FindAdapter;
import com.jiao.luo.adapter.FindHeaderAdapter;
import com.jiao.luo.interf.MyOnItemClickListener;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.GlideUtils;
import com.jiao.luo.utils.IntentUtils;
import com.jiao.luo.utils.ToastUtils;
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
 * 发现
 */
public class FindFragment extends FragmentBase implements MyOnItemClickListener, BaseQuickAdapter.OnItemClickListener, OnRefreshListener, OnLoadmoreListener {
    private SmartRefreshLayout refresh;
    RecyclerView rv;
    private FragmentActivity context;

    private final long DELAY_MILLIS = 1000;
    private TextView tv_name;
    private TextView tv_desc;
    private ImageView iv_img;
    private FindAdapter findAdapter;
    private FindHeaderAdapter findHeaderAdapter;
    private List<ResTopic.DataBean.FollowTypeBean> follow_typeList = new ArrayList<>();//下侧rv的数据集合


    private int page = 0;
    private int limit = 10;
    private String show_type = "";
    private LinearLayout ll_content;
    private int mPosition;

    public FindFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("FindFragment:onCreateView");
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        refresh = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        rv = (RecyclerView) view.findViewById(R.id.can_content_view);
        context = this.getActivity();

        initView();
        initData();
        return view;
    }

    //请求数据
    private void initData() {
        findFixed();
        //中间三个
        findMiddle();


    }

    //中间三个话题的请求数据
    private void findMiddle() {
        OkGo.post(Constant.FINDMIDDLE).headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID).execute(new SimpleCommonCallback<ResTopicHeader>(this) {
            @Override
            public void onSuccess(ResTopicHeader resTopicHeader, Call call, Response response) {
                if (resTopicHeader.getReturnCode() == 1) {
                    List<ResTopicHeader.DataBean> data = resTopicHeader.getData();
                    refreshMiddleUi(data);
                }else{
                    ToastUtils.showToast(FindFragment.this.getActivity(),resTopicHeader.getMessage());
                    if(resTopicHeader.getMessage().equals("请先登录")){
                        IntentUtils.intentLogin(FindFragment.this.getActivity());
                    }
                }
            }
        });
    }

    //置顶和下侧请求数据
    private void findFixed() {
        OkGo.post(Constant.FINDFIXED).headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID).execute(new SimpleCommonCallback<ResTopic>(this) {
            @Override
            public void onSuccess(ResTopic resTopic, Call call, Response response) {
                if (resTopic.getReturnCode() == 1) {
                    show_type = resTopic.getData().getShow_type();
                    ResTopic.DataBean.RecommendTypeBean recommend_type = resTopic.getData().getRecommend_type();
                    follow_typeList = resTopic.getData().getFollow_type();
                    refreshUi(recommend_type, follow_typeList);
                }else{
                    ToastUtils.showToast(FindFragment.this.getActivity(),resTopic.getMessage());
                    if(resTopic.getMessage().equals("请先登录")){
                        IntentUtils.intentLogin(FindFragment.this.getActivity());
                    }

                }
            }
        });
    }


    //    发现上拉加载更多更新话题
    private void findlist(String showtype) {
        OkGo.post(Constant.FINDLIST).headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("show_type",show_type)
                .params("page", page)
                .params("limit", limit)
                .execute(new SimpleCommonCallback<ResCateData>(this) {
                    @Override
                    public void onSuccess(ResCateData resTopic, Call call, Response response) {
                        List<ResTopic.DataBean.FollowTypeBean> follow_type = resTopic.getData();
                        follow_typeList.addAll(follow_type);
                        refreshUi(null,follow_typeList);
                    }


                }.setShowProgress(true));
    }

    //刷新中间数据的UI
    private void refreshMiddleUi(List<ResTopicHeader.DataBean> data) {
        findHeaderAdapter.setNewData(data);

    }

    //刷新置顶信息和下侧的关注话题信息

    /**
     *
     * @param type 置顶的数据
     * @param follow_type  下侧话题列表
     */
    private void refreshUi(final ResTopic.DataBean.RecommendTypeBean type, List<ResTopic.DataBean.FollowTypeBean> follow_type) {
        if (type != null) {
            tv_desc.setText(type.getDesc());
            tv_name.setText(type.getName());
            GlideUtils.load(this,type.getImgs(),iv_img);
            ll_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.intentDetail(mContext,type.getTypeid());
                }
            });
        }
        findAdapter.setNewData(follow_type);

    }

    private void initView() {

        refresh.setOnRefreshListener(this);
        refresh.setOnLoadmoreListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false);
        //分割线
        rv.addItemDecoration(new RvDividerItemDecoration(
                getActivity(), GridLayoutManager.VERTICAL));
        rv.setLayoutManager(gridLayoutManager);


        findAdapter = new FindAdapter(R.layout.fragment_home_item, null, this, new MyOnItemClickListener() {
            @Override
            public void onItem(int pos) {
                if(!Constant.ISLOGIN){
                    ToastUtils.showToast(FindFragment.this.getActivity(),"请先登录");
                    return;
                }
                List<ResTopic.DataBean.FollowTypeBean> data = findAdapter.getData();
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
                ResTopic.DataBean.FollowTypeBean followTypeBean = data.get(pos);
                findAdapter.setData(pos,followTypeBean);
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
        });
        findAdapter.setOnItemClickListener(this);


        View header_view = View.inflate(mContext, R.layout.fragment_find_header, null);

        setHeaderViewData(header_view);

        //添加头布局
        findAdapter.addHeaderView(header_view);

        rv.setAdapter(findAdapter);


    }

    /**
     * 设置头布局的数据
     *
     * @param header_view
     */
    private void setHeaderViewData(View header_view) {
        tv_name = (TextView) header_view.findViewById(R.id.tv_name);
        tv_desc = (TextView) header_view.findViewById(R.id.tv_desc);
        iv_img = (ImageView) header_view.findViewById(R.id.iv_img);
        ll_content = (LinearLayout) header_view.findViewById(R.id.ll_content);

        RecyclerView rv_header = (RecyclerView) header_view.findViewById(R.id.rv);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL, false);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
       /* //分割线
        rv.addItemDecoration(new RvDividerItemDecoration(
                getActivity(), GridLayoutManager.VERTICAL));*/
        rv_header.setLayoutManager(gridLayoutManager);
        findHeaderAdapter = new FindHeaderAdapter(R.layout.activity_find_middle_item, null, mContext, this);
        findHeaderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<ResTopicHeader.DataBean> data = findHeaderAdapter.getData();
                ResTopicHeader.DataBean dataBean = data.get(position);
                String typeid = dataBean.getTypeid();
                IntentUtils.intentDetail(mContext,typeid);
            }
        });
        rv_header.setAdapter(findHeaderAdapter);


    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        System.out.println("FindFragment:onDestroyView");

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("FindFragment:onViewCreated");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("FindFragment:onCreate");
    }



    @Override
    public void onItem(int pos) {
        if(!Constant.ISLOGIN){
            ToastUtils.showToast(FindFragment.this.getActivity(),"请先登录");
            return;
        }
        List<ResTopicHeader.DataBean> data = findHeaderAdapter.getData();
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
        findHeaderAdapter.setNewData(data);


        //关注和取消关注
        followForNet(typeid, true);
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

    //关注和取消关注
    private void followForNet(String typeid, final boolean isHeader) {

        OkGo.post(Constant.FOLLOWADD).headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("typeid", typeid).execute(new SimpleCommonCallback<ResFollow>(this) {
            @Override
            public void onSuccess(ResFollow resFollow, Call call, Response response) {
                if (resFollow.getReturnCode() == 1) {
                    toast(resFollow.getMessage());
                   /* if(isHeader){
                    findHeaderAdapter.notifyDataSetChanged();
                    }else{
                        findAdapter.notifyDataSetChanged();
                    }*/
                }

            }
        });
    }


    //下侧RV的条目点击事件
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPosition = position;

        //减1是因为置顶和中间的三个话题 是通过addheaderView添加进去的
        List<ResTopic.DataBean.FollowTypeBean> data = findAdapter.getData();
        ResTopic.DataBean.FollowTypeBean followTypeBean = data.get(position);
        //话题id
        String typeid = followTypeBean.getTypeid();
        Intent intent = new Intent(context, CateDetailActivity.class);
        intent.putExtra(Constant.TYPEID, typeid);
        startActivityForResult(intent,Constant.RequestCode);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constant.RequestCode&&resultCode==-1){
            String isFollow = data.getStringExtra(Constant.IS_FOLLOW);
            follow_typeList.get(mPosition).setIs_follow(isFollow);
            findAdapter.setNewData(follow_typeList);
        }


    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        //从新请求接口
        findMiddle();
        refreshlayout.finishRefresh(1000);

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {

        page++;
        findlist(show_type);
        refreshlayout.finishLoadmore(1000);
    }


}
