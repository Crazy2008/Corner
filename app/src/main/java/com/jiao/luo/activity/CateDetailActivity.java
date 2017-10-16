 package com.jiao.luo.activity;

 import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResCateDetail;
import com.jiao.luo.Response.ResFollow;
import com.jiao.luo.Response.ResNotice;
import com.jiao.luo.Response.ResPub;
import com.jiao.luo.adapter.CateDetailAdapter;
import com.jiao.luo.interf.MyOnItemClickListener;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.GlideUtils;
import com.jiao.luo.utils.IntentUtils;
import com.jiao.luo.utils.PopupUtils;
import com.jiao.luo.utils.ToastUtils;
import com.jiao.luo.utils.ZF_PopupWindow;
import com.jiao.luo.widget.EmptyHeader;
import com.jiao.luo.widget.MorePopupWindow;
import com.lzy.okgo.OkGo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.common.base.ActivityCommBase;
import cn.common.http2.callback.SimpleCommonCallback;
import jp.wasabeef.glide.transformations.BlurTransformation;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 话题的详情页
 */

public class CateDetailActivity extends ActivityCommBase implements MyOnItemClickListener, BaseQuickAdapter.OnItemClickListener, View.OnClickListener, OnLoadmoreListener, OnRefreshListener {


    private String typeid;
    private String url;

    private int page = 1;
    private int limit = 10;
    private Bitmap bitmap;
    private ImageView ivIcon;
    private ImageView ivBack;
    private ImageView iv_share;
    private ImageView iv_more;
    private TextView tv_follow_num;
    private TextView tv_title;
    private RelativeLayout rl_header;
    private ImageView iv_add;
    private LinearLayout ll_header;
    private RelativeLayout rl_notice;
    private View view_line;
    private RecyclerView rv;

    private boolean flag = false;
    private boolean isRefresh = true;


    List<ResCateDetail.DataBean.SubdataBean> datas = new ArrayList<>();
    private CateDetailAdapter cateDetailAdapter;
    private View headerView;
    private ResCateDetail.DataBean data;
    private ToggleButton tb_toggle;
    private ImageView iv_header;
    private LinearLayout ll_parent;
    private SmartRefreshLayout refresh;
    private TextView tv_name;
    private RelativeLayout rl_bg;
    private FrameLayout fr_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate_detail);
        ll_parent = (LinearLayout) findViewById(R.id.ll_parent);
        rv = (RecyclerView) findViewById(R.id.rv);
        refresh = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        //话题id
        typeid = getIntent().getStringExtra(Constant.TYPEID);
        initView();
        initData();
    }

    private void initData() {
        OkGo.post(Constant.FOLLOWNEW).headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                //TODO
                .params("typeid", typeid)
                .params("page", page)
                .params("limit", limit)
                .execute(new SimpleCommonCallback<ResCateDetail>(this) {
                    @Override
                    public void onSuccess(ResCateDetail resCateDetail, Call call, Response response) {
                        if(resCateDetail.getReturnCode()==1){
                            data = resCateDetail.getData();
                            if (data != null) {
                                if (isRefresh) {
                                    setHeaderData(data);
                                }
                                List<ResCateDetail.DataBean.SubdataBean> subdata = data.getSubdata();
                                datas.addAll(subdata);
                                cateDetailAdapter.setNewData(datas);
                            }
                        }else{
                            ToastUtils.showToast(CateDetailActivity.this,resCateDetail.getMessage());
                            if(resCateDetail.getMessage().equals("请先登录")){
                                IntentUtils.intentLogin(CateDetailActivity.this);
                            }
                        }
                    }
                }.setShowProgress(true));


    }

    //是否推送的接口
    private void noticeForNet(int is_notice) {
        if (!Constant.ISLOGIN) {
            ToastUtils.showToast(this, "请先登录");
            return;
        }
        OkGo.post(Constant.FOLLOWPUSH)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("catid", Integer.parseInt(typeid))
                .params("is_notice", is_notice)
                .execute(new SimpleCommonCallback<ResNotice>(this) {
                    @Override
                    public void onSuccess(ResNotice resNotice, Call call, Response response) {
                        Intent intent = new Intent();
                        if (resNotice.getReturnCode() == 1) {
                            if (resNotice.getData().getIs_notice().equals("-1")) {
                                ToastUtils.showToast(CateDetailActivity.this, "推送关闭");
                                intent.putExtra(Constant.U, "-1");
                                setResult(RESULT_OK, intent);
                            } else {
                                ToastUtils.showToast(CateDetailActivity.this, "推送开启");
                                intent.putExtra(Constant.U, "1");
                                setResult(RESULT_OK, intent);
                            }
                        } else {
                            ToastUtils.showToast(CateDetailActivity.this, resNotice.getMessage());
                        }
                    }
                });
    }

    //设置顶部数据
    private void setHeaderData(final ResCateDetail.DataBean data) {
        tb_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_toggle.isChecked()) {
                    noticeForNet(1);
                } else {
                    noticeForNet(-1);
                }
            }
        });

        String is_follow = data.getIs_follow();
        if ("未关注".endsWith(is_follow)) {
            iv_add.setImageResource(R.drawable.bt_add);
            flag = false;
        } else {
            view_line.setVisibility(View.GONE);
            iv_add.setImageResource(R.drawable.bt_success);
            flag = true;
            //是否推送
            int is_notice = data.getIs_notice();
            rl_notice.setVisibility(View.VISIBLE);
            //=-1表示不推送
            if (is_notice == -1) {
                tb_toggle.setChecked(false);
            } else {
                tb_toggle.setChecked(true);
            }
        }


        //加载背景，
        Glide.with(this)
                .load(data.getImgs())
                .dontAnimate()
                .error(R.drawable.ic_default_image)
                // 设置高斯模糊
                .bitmapTransform(new BlurTransformation(this, 23, 3))
                .into(iv_header);
        //设置关注人数
        tv_follow_num.setText(data.getFollow_num() + "人关注");
        tv_title.setText(data.getName());
        tv_name.setText(data.getName());


        //设置头像
//        Glide.with(this).load(data.getImgs()).transform(new MyCornersTransform(this, 20)).into(ivIcon);
        GlideUtils.load(this, data.getImgs(), ivIcon);

    }

    private void initView() {

        refresh.setOnRefreshListener(this);
        refresh.setRefreshHeader(new EmptyHeader(this));
        refresh.finishRefresh(true);
        refresh.setOnLoadmoreListener(this);

        refresh.setOnMultiPurposeListener(new MyMultiPurposeListener());
//        refresh.setOnDragListener(this);

        fr_content = (FrameLayout) findViewById(R.id.fr_content);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        iv_share = (ImageView) findViewById(R.id.iv_share);
        iv_more = (ImageView) findViewById(R.id.iv_more);
        rl_header = (RelativeLayout) findViewById(R.id.rl_header);
        tv_name = (TextView) findViewById(R.id.tv_name);
        //初始化字体透明度
        tv_name.setTextColor(Color.argb(0, 150, 150, 150));
        rl_bg = (RelativeLayout) findViewById(R.id.rl_bg);
        rl_bg.getBackground().setAlpha(0);


     rv.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               System.out.println("v = [" + v + "], event = [" + event + "]");
                       float rawY =event.getY();
               switch (event.getAction()){
                   case  MotionEvent.ACTION_DOWN:
                       Log.d("A","ACTION_DOWN"+rawY);
                       break;
                   case MotionEvent.ACTION_MOVE:

                       Log.d("A","ACTION_MOVE"+rawY);
                       break;
                   case  MotionEvent.ACTION_UP:
                       Log.d("A","ACTION_UP"+rawY);
                       break;
                   case MotionEvent.ACTION_CANCEL:
                       Log.d("A","ACTION_CANCEL"+rawY);
                       break;
               }
               return false;
           }
       });
        ivBack.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        iv_more.setOnClickListener(this);


        headerView = View.inflate(this, R.layout.activity_cate_header, null);
        ivIcon = (ImageView) headerView.findViewById(R.id.iv_icon);


        tv_follow_num = (TextView) headerView.findViewById(R.id.tv_follow_num);
        tv_title = (TextView) headerView.findViewById(R.id.tv_title);

        iv_add = (ImageView) headerView.findViewById(R.id.iv_add);
        tb_toggle = (ToggleButton) headerView.findViewById(R.id.tb_toggle);


        iv_add.setOnClickListener(this);


        ll_header = (LinearLayout) headerView.findViewById(R.id.ll_header);
        iv_header = (ImageView) headerView.findViewById(R.id.iv_header);
        //背景线
        view_line = headerView.findViewById(R.id.view_line);

        rl_notice = (RelativeLayout) headerView.findViewById(R.id.rl_notice);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

//        rv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL));
     /*   SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(16);
        rv.addItemDecoration(spacesItemDecoration);*/
        //分割线
//        rv.addItemDecoration(new RvDividerHeight(this, LinearLayoutManager.VERTICAL));
        rv.setLayoutManager(gridLayoutManager);

        cateDetailAdapter = new CateDetailAdapter(this, null, this);

        cateDetailAdapter.addHeaderView(headerView);

        rv.setAdapter(cateDetailAdapter);
        cateDetailAdapter.setOnItemClickListener(this);
    }

    //原文
    @Override
    public void onItem(int position) {
        intentUrl(position);
    }

    @Override
    public void onFavItem(int position) {

        String id = datas.get(position).getId();
        ResCateDetail.DataBean.SubdataBean bean = null;
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
        cateDetailAdapter.setData(position, bean);

        int value = Integer.parseInt(id);

        OkGo.post(Constant.NEWSFAV).headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("newsid", value).execute(new SimpleCommonCallback<ResPub>(this) {
            @Override
            public void onSuccess(ResPub resPub, Call call, Response response) {
                if (resPub.getReturnCode() == 1) {
                    ToastUtils.showToast(CateDetailActivity.this, resPub.getMessage());
                }
            }
        });

    }

    @Override
    public void onCommentItem(int position) {
        List<ResCateDetail.DataBean.SubdataBean> data = cateDetailAdapter.getData();
        ResCateDetail.DataBean.SubdataBean subdataBean = data.get(position);
        String id = subdataBean.getId();
        Intent intent = new Intent(this, PublicCommentActivity.class);
        intent.putExtra(Constant.NEWID, id);
       startActivityForResult(intent,Constant.RequestCode);
    }

    /**
     * 转发
     *
     * @param position
     */
    @Override
    public void onRelayItem(int position) {
        ZF_PopupWindow window = new ZF_PopupWindow(this);
        window.show(rv);
//        window.showAtLocation(rv, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        PopupUtils.setBg(this, window);
    }

    //详情页的条目点击事件
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


        intentUrl(position);
    }

    //跳转原文d
    private void intentUrl(int position) {
        ResCateDetail.DataBean.SubdataBean subdataBean = datas.get(position);
        String url = subdataBean.getUrl();
        Intent intent = new Intent(this, OriginalActivity.class);
        //收藏数量
        int fav_count = subdataBean.getFav_count();
        //评论数量
        int comment_count = subdataBean.getComment_count();
        String newsId = subdataBean.getId();
        IntentUtils.intentWeb(this, url, fav_count, comment_count, Integer.parseInt(newsId));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constant.RequestCode&&resultCode==RESULT_OK){
            page=1;
            datas.clear();
            initData();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                ZF_PopupWindow window = new ZF_PopupWindow(this);
                window.show(rv);
                PopupUtils.setBg(this, window);
                break;
            case R.id.iv_more:
                MorePopupWindow morePopupWindow = new MorePopupWindow(this);
                morePopupWindow.show(rv);
                break;
            //是否关注
            case R.id.iv_add:

                if (!Constant.ISLOGIN) {
                    ToastUtils.showToast(this, "请先登录");
                    return;
                }

                if (flag) {
                    //已关注
                    iv_add.setImageResource(R.drawable.bt_add);
                    rl_notice.setVisibility(View.GONE);
                    view_line.setVisibility(View.VISIBLE);
                    flag = !flag;
                } else {
                    iv_add.setImageResource(R.drawable.bt_success);
                    rl_notice.setVisibility(View.VISIBLE);
                    view_line.setVisibility(View.GONE);
                    flag = !flag;
                }
                //关注和取消关注
                followForNet();
                break;
        }
    }


    //关注和取消关注
    private void followForNet() {

        OkGo.post(Constant.FOLLOWADD).headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("typeid", typeid).execute(new SimpleCommonCallback<ResFollow>(this) {
            @Override
            public void onSuccess(ResFollow resFollow, Call call, Response response) {
                Intent intent = new Intent();
                if (resFollow.getReturnCode() == 1) {
                    String message = resFollow.getMessage();
                    ToastUtils.showToast(CateDetailActivity.this, message);
                   /* if(isHeader){
                    findHeaderAdapter.notifyDataSetChanged();
                    }else{
                        findAdapter.notifyDataSetChanged();
                    }*/
                    if (resFollow.getMessage().equals("关注话题成功")) {
                        intent.putExtra(Constant.IS_FOLLOW, "已关注");
                        setResult(RESULT_OK, intent);
                    } else if (resFollow.getMessage().equals("取消关注话题成功")) {
                        intent.putExtra(Constant.IS_FOLLOW, "未关注");
                        setResult(RESULT_OK, intent);
                    }


                }

            }
        });
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshlayout.finishLoadmore();
        isRefresh = false;
        page++;
        initData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        /*refreshlayout.finishRefresh(1000);
         page = 1;
        isRefresh = false;
        initData();*/
        refreshlayout.finishRefresh(100);
        refreshlayout.finishRefresh(true);
    }



    private class MyMultiPurposeListener implements OnMultiPurposeListener {

        @Override
        public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
            System.out.println("onHeaderPulling = percent = [ " + percent + "], offset = [" + offset + "], headerHeight = [" + headerHeight + "], extendHeight = [" + extendHeight + "]");
            if(offset<=142){
                int alpha = new Double(offset * 1.76).intValue();
                rl_bg.getBackground().setAlpha(alpha);
                tv_name.setTextColor(Color.argb(alpha, 150, 150, 150));
                System.out.println("onHeaderPulling   alpha======"+alpha);
            }

        }

        @Override
        public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
            System.out.println("onHeaderReleasing =percent = [" + percent + "], offset = [" + offset + "], headerHeight = [" + headerHeight + "], extendHeight = [" + extendHeight + "]");
            if(offset<=142){
                int alpha = new Double(offset * 1.76).intValue();
                rl_bg.getBackground().setAlpha(alpha);
                tv_name.setTextColor(Color.argb(alpha, 150, 150, 150));
                System.out.println("onHeaderPulling   alpha======"+alpha);
              }
//            tv_name.getBackground().setAlpha(offset*2);
   /*         rl_bg.setBackgroundColor(Color.parseColor("#2a2a2a"));
            if (offset <= 0) {
                rl_header.getBackground().setAlpha(255);
                tv_name.setTextColor(Color.argb(0, 150, 150, 150));
                rl_bg.setBackground(null);
            } else {
                rl_header.getBackground().setAlpha(offset);
                tv_name.setTextColor(Color.argb(offset, 150, 150, 150));
                rl_bg.getBackground().setAlpha(offset);
            }*/
        }

        @Override
        public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int extendHeight) {

        }

        @Override
        public void onHeaderFinish(RefreshHeader header, boolean success) {

        }

        @Override
        public void onFooterPulling(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
            System.out.println("onFooterPulling = percent = [" + percent + "], offset = [" + offset + "], footerHeight = [" + footerHeight + "], extendHeight = [" + extendHeight + "]");

        }

        @Override
        public void onFooterReleasing(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
            System.out.println("onFooterReleasing =  percent = [" + percent + "], offset = [" + offset + "], footerHeight = [" + footerHeight + "], extendHeight = [" + extendHeight + "]");

        }

        @Override
        public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int extendHeight) {

        }

        @Override
        public void onFooterFinish(RefreshFooter footer, boolean success) {

        }

        @Override
        public void onLoadmore(RefreshLayout refreshlayout) {

        }

        @Override
        public void onRefresh(RefreshLayout refreshlayout) {

        }

        @Override
        public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
            System.out.println("refreshLayout = [" + refreshLayout + "], oldState = [" + oldState + "], newState = [" + newState + "]");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
