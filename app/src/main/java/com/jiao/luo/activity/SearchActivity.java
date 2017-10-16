package com.jiao.luo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiao.luo.Decoration.RvDividerItemDecoration;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResSearch;
import com.jiao.luo.adapter.SearchAdapter;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.IntentUtils;
import com.jiao.luo.utils.SPTools;
import com.lzy.okgo.OkGo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.common.base.ActivityCommBase;
import cn.common.http2.callback.SimpleCommonCallback;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 搜索话题界面
 */

public class SearchActivity extends ActivityCommBase implements  OnLoadmoreListener, OnRefreshListener, BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.et_Search)
    EditText etSearch;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.ll_clear)
    LinearLayout llClear;
    @BindView(R.id.fl_layout)
    TagFlowLayout flLayout;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.ll_parent)
    LinearLayout ll_parent;
    @BindView(R.id.ll_default)
    LinearLayout ll_default;
    private int page = 1;
    private int limit = 10;

    List<ResSearch.DataBean> dataList = new ArrayList<>();
    private SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);
        ButterKnife.bind(this);
        initView();


    }

    private void initView() {
        searchClick();

        refresh.setOnLoadmoreListener(this);
        refresh.setOnRefreshListener(this);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        //分割线
        rv.addItemDecoration(new RvDividerItemDecoration(
                this, GridLayoutManager.VERTICAL));
        rv.setLayoutManager(gridLayoutManager);
        searchAdapter = new SearchAdapter(R.layout.fragment_home_item, null, this,this);
        searchAdapter.setOnItemClickListener(this);
        rv.setAdapter(searchAdapter);



        Set<String> hashSet = SPTools.getHashSet(SearchActivity.this, Constant.SP_HISTORY);
        if (hashSet == null || hashSet.size() == 0) {
            llContent.setVisibility(View.VISIBLE);
            return;
        }
        if (hashSet != null) {
            Iterator<String> iterator = hashSet.iterator();
            final ArrayList<String> datas = new ArrayList<>();
            while (iterator.hasNext()) {
                datas.add(iterator.next());
            }

            flLayout.setAdapter(new TagAdapter<String>(datas) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView textView = (TextView) View.inflate(SearchActivity.this, R.layout.activity_search_item, null);
                    textView.setText(s);
                    return textView;
                }
            });
           flLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
               @Override
               public boolean onTagClick(View view, int position, FlowLayout parent) {
                   String s = datas.get(position);
                   System.out.println("s="+s);
                   etSearch.setText(s);
                   searchDataForNet(s);
                   return false;
               }
           });

        }


    }

    //搜索的点击处理事件
    private void searchClick() {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.ACTION_DOWN)) {
                    System.out.println("v = [" + v + "], actionId = [" + actionId + "], event = [" + event + "]");


                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                    //do something;
                    Set<String> hashSet = SPTools.getHashSet(SearchActivity.this, Constant.SP_HISTORY);
                    SPTools.removeString(SearchActivity.this, Constant.SP_HISTORY);
                    if (hashSet != null) {
                        hashSet.add(etSearch.getText().toString().trim());
                    } else {
                        hashSet = new HashSet<String>();
                        hashSet.add(etSearch.getText().toString().trim());
                    }
                    SPTools.setHash(SearchActivity.this, Constant.SP_HISTORY, hashSet);
                    hashSet.clear();
                    dataList.clear();




                    searchDataForNet(etSearch.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 搜索
     *
     * @param keyword 字段
     */
    private void searchDataForNet(String keyword) {
        OkGo.post(Constant.SEARCH)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("keyword", keyword)
                .params("page", page)
                .params("limit", limit)
                .execute(new SimpleCommonCallback<ResSearch>(this) {
                    @Override
                    public void onSuccess(ResSearch resSearch, Call call, Response response) {
                        if (resSearch.getReturnCode() == 1) {
                            List<ResSearch.DataBean> data = resSearch.getData();
                            if (data != null && data.size() > 0) {
                                llContent.setVisibility(View.GONE);
                                dataList.addAll(data);
                                searchAdapter.setNewData(dataList);
                            }else{
                                ll_parent.setVisibility(View.GONE);
                                ll_default.setVisibility(View.VISIBLE);
                            }
                        } else {
                            toast(resSearch.getMessage());
                        }

                    }
                });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @OnClick({R.id.tv_cancle, R.id.ll_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancle:
                finish();
                break;
            case R.id.ll_clear:
                Set<String> hashSet = SPTools.getHashSet(SearchActivity.this, Constant.SP_HISTORY);
                if (hashSet != null) {
                    hashSet.clear();
                    SPTools.setHash(SearchActivity.this, Constant.SP_HISTORY, hashSet);
                    llContent.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshlayout.finishLoadmore(1000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<ResSearch.DataBean> data = searchAdapter.getData();
        ResSearch.DataBean dataBean = data.get(position);
        String typeid = dataBean.getTypeid();
        IntentUtils.intentDetail(this,typeid);
    }
}
