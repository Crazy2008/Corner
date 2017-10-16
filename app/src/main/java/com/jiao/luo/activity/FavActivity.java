package com.jiao.luo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiao.luo.Decoration.RvDividerItemDecoration;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResFav;
import com.jiao.luo.adapter.FavAdapter;
import com.jiao.luo.interf.MyOnItemClickListener;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.IntentUtils;
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

/**
 * 我喜欢的
 */


public class FavActivity extends ActivityCommBase implements MyOnItemClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rv)
    RecyclerView rv;
    private int page = 1;
    private int limit = 10;
    private FavAdapter favAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        OkGo.post(Constant.FAVLIST)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("page", page)
                .params("limit", limit)
                .execute(new SimpleCommonCallback<ResFav>(this) {
                    @Override
                    public void onSuccess(ResFav resFav, Call call, Response response) {
                        if (resFav.getReturnCode() == 1) {
                            List<ResFav.DataBean> data = resFav.getData();
                            favAdapter.setNewData(data);
                        }

                    }
                });
    }

    private void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        //分割线
        rv.addItemDecoration(new RvDividerItemDecoration(
                this, GridLayoutManager.VERTICAL));
        rv.setLayoutManager(gridLayoutManager);

        favAdapter = new FavAdapter(null, this, this);

        favAdapter.setOnItemClickListener(this);
        rv.setAdapter(favAdapter);


    }

    @OnClick({R.id.iv_back, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                startActivityForResult(new Intent(this, FavSearchActivity.class), Constant.RequestCode);
                break;
        }
    }

    @Override
    public void onItem(int pos) {

    }

    @Override
    public void onFavItem(int pos) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestCode&&resultCode==RESULT_OK) {
            page = 1;
            favSearchForNet();
        }

    }

    private void favSearchForNet() {
     OkGo.post(Constant.FAVSEARCH)
             .headers("tokens", Constant.TOKEN)
             .params("userid", Constant.USERID)
             .params("keyword", Constant.keyword)
             .params("page", page)
             .params("limit", limit)
             .execute(new SimpleCommonCallback<ResFav>(this) {
                 @Override
                 public void onSuccess(ResFav resFav, Call call, Response response) {
                     if(resFav.getReturnCode()==1){
                         List<ResFav.DataBean> data = resFav.getData();
                         if(data!=null&&data.size()>0){
                         favAdapter.setNewData(data);
                         }else{
                             toast("未搜到相应内容");
                         }
                     }else{
                         toast(resFav.getMessage());
                     }
                 }
             });

    }

    @Override
    public void onCommentItem(int position) {
        List<ResFav.DataBean> data = favAdapter.getData();
        ResFav.DataBean dataBean = data.get(position);
        String newsid = dataBean.getNewsid();
        IntentUtils.intentComment(this, newsid);
    }

    @Override
    public void onRelayItem(int pos) {
        ZF_PopupWindow window = new ZF_PopupWindow(this);
        window.show(rv);
        PopupUtils.setBg(this, window);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<ResFav.DataBean> data = favAdapter.getData();
        ResFav.DataBean dataBean = data.get(position);
        ResFav.DataBean.NewsBean news = dataBean.getNews();
        int comment_count = news.getComment_count();
        int fav_count = news.getFav_count();
        String url = news.getUrl();
        String newsid = dataBean.getNewsid();
        IntentUtils.intentWeb(this, url, fav_count, comment_count, Integer.parseInt(newsid));
    }








}
