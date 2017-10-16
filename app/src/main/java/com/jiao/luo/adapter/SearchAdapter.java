package com.jiao.luo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResFollow;
import com.jiao.luo.Response.ResSearch;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.GlideUtils;
import com.jiao.luo.utils.ToastUtils;
import com.lzy.okgo.OkGo;

import java.util.List;

import cn.common.http2.callback.SimpleCommonCallback;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/27.
 */

public class SearchAdapter extends BaseQuickAdapter<ResSearch.DataBean,BaseViewHolder> {


    private final int layoutResId;
    private final List<ResSearch.DataBean> data;
    private final Context context;
    private Activity activity;

    public SearchAdapter(int layoutResId, List<ResSearch.DataBean> data, Context context, Activity activity) {
        super(layoutResId, data);
        this.layoutResId = layoutResId;
        this.data = data;
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ResSearch.DataBean item) {

        CheckedTextView tv_add = helper.getView(R.id.ct_add);
        //-1是因为加了一个HeaderView
        final int position = helper.getPosition() ;

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getIs_follow().equals("未关注")){
                    item.setIs_follow("已关注");
                }else{
                    item.setIs_follow("未关注");
                }
                notifyDataSetChanged();
                followForNet(item.getTypeid());


            }
        });
        if ("未关注".equals(item.getIs_follow())) {
            tv_add.setBackgroundResource(R.drawable.bt_add);
        } else {
            tv_add.setBackgroundResource(R.drawable.bt_success);
        }


        //标题
        helper.setText(R.id.tv_title, item.getName());
        //描述
        helper.setText(R.id.tv_desc, item.getDesc());
        helper.setText(R.id.tv_type, item.getTypeid());
        ImageView iv_icon = helper.getView(R.id.iv_icon);

        GlideUtils.load(context,item.getImgs(),iv_icon);
    }

    //关注和取消关注
    private void followForNet(String typeid) {
        if(!Constant.ISLOGIN){
            ToastUtils.showToast(context,"请先登录");
            return;
        }
        OkGo.post(Constant.FOLLOWADD).headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("typeid", typeid).execute(new SimpleCommonCallback<ResFollow>(activity) {
            @Override
            public void onSuccess(ResFollow resFollow, Call call, Response response) {
                if (resFollow.getReturnCode() == 1) {
                    Toast.makeText(context,resFollow.getMessage(),Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
