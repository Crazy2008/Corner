package com.jiao.luo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jiao.luo.Decoration.SpacesItemDecoration;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResCate;
import com.jiao.luo.Response.ResMemberCate;
import com.jiao.luo.adapter.CateAdapter;
import com.jiao.luo.interf.MyOnItemClickListener;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.ToastUtils;
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
 * 选择模块界面
 */
public class CateActivity extends ActivityCommBase implements MyOnItemClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.btn_yes)
    Button btn_yes;
    private List<ResCate.DataBean> dataList = new ArrayList<>();
    private List<ResCate.DataBean> checkedList = new ArrayList<>();
    private CateAdapter cateAdapter;
    private ResCate.DataBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate);
        ButterKnife.bind(this);
        initView();
        initData();


    }

    private void initData() {
        OkGo.post(Constant.CATELIST).execute(new SimpleCommonCallback<ResCate>(this) {
            @Override
            public void onSuccess(ResCate resCate, Call call, Response response) {
                dataList = resCate.getData();
                cateAdapter.setNewData(dataList);
            }
        });

    }

    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(14);
        rv.addItemDecoration(spacesItemDecoration);
        cateAdapter = new CateAdapter(this, R.layout.activity_cate_item, null, this);

        rv.setAdapter(cateAdapter);

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
    }

    @Override
    public void onItem(int pos) {
        System.out.println("pos = [" + pos + "]");
        checkedList.clear();
        for (int i = 0; i < dataList.size(); i++) {
            bean = dataList.get(i);
            if (pos == i) {
                bean.setChecked(!bean.isChecked());
            }
            checkedList.add(bean);

        }
        cateAdapter.setNewData(checkedList);
        dataList.clear();
        dataList.addAll(checkedList);
        cateAdapter.notifyDataSetChanged();

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

    @OnClick({R.id.iv_back, R.id.btn_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_yes:
                //选择模块完成后
                uploadCateList();
                break;
        }
    }

    private void uploadCateList() {
        ArrayList<Integer> catIdList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        //从adapter里面拿到数据集合
        List<ResCate.DataBean> data = cateAdapter.getData();
        if(data==null||data.size()==0){
            return;
        }
        //遍历集合中字段 checked 是true 就是选中的
        for (int i = 0; i < data.size(); i++) {
            ResCate.DataBean dataBean = data.get(i);
            if (dataBean.isChecked()) {
                int catid = dataBean.getCatid();
                catIdList.add(catid);
            }

        }




        //如果选中的数量小于三个 随机再添加
      /*  Random random = new Random();
            int size = catIdList.size();
        if(size<3){
            for (int j = 0; j<(3- size); j++){
                int k = random.nextInt(data.size()-1);
                if(data.get(k).isChecked()){
                    continue;
                }else{
                    catIdList.add(data.get(k).getCatid());
                }
            }
        }*/
      //强制用户选择三个模块
      if(catIdList.size()<3){
          ToastUtils.showToast(CateActivity.this,"您必须选择三个模块");
          return;
      }

        //遍历选中的集合
        for (int i = 0; i < catIdList.size(); i++) {
            if (i != catIdList.size()-1) {
                builder.append(catIdList.get(i) + ",");
            } else {
                builder.append(catIdList.get(i));
            }
        }

        Constant.CATEIDSID_LIST.addAll(catIdList);


        System.out.println(builder.toString());
        OkGo.post(Constant.UPLOAD_CATELIST)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("catid", builder.toString()).execute(new SimpleCommonCallback<ResMemberCate>(this) {
            @Override
            public void onSuccess(ResMemberCate resMemberCate, Call call, Response response) {
                if(resMemberCate.getReturnCode()==1){
                    toast("关注模块成功");
                    startActivity(new Intent(CateActivity.this,HomeActivity.class));
                    finish();
                }else{
                    toast(resMemberCate.getMessage());
                }
            }
        });

    }
}
