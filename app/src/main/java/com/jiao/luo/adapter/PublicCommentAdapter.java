package com.jiao.luo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResPubComment;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.GlideUtils;
import com.jiao.luo.utils.TimeUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/18.
 */

public class PublicCommentAdapter extends BaseQuickAdapter<ResPubComment.DataBean.ListBean,BaseViewHolder> {

    private final int layoutResId;
    private final Context context;
    private final List<ResPubComment.DataBean.ListBean> data;

    public PublicCommentAdapter(int layoutResId, Context context, List<ResPubComment.DataBean.ListBean> data) {
        super(layoutResId, data);
        this.layoutResId = layoutResId;
        this.context = context;
        this.data = data;
    }


   int i= R.layout.activity_comment_horizontal;
    @Override
    protected void convert(final BaseViewHolder helper, final ResPubComment.DataBean.ListBean item) {
                    helper.setText(R.id.tv_name,item.getUsername());

        ImageView iv_icon = helper.getView(R.id.iv_icon);
        GlideUtils.load(context,item.getUser().getHeadPic(),iv_icon);
            //时间
        String time = TimeUtils.stmpToDate(item.getCreat_at());
        helper.setText(R.id.tv_time,time);
            //评论内容
        helper.setText(R.id.tv_desc,item.getContent());

        ImageView iv_support = helper.getView(R.id.iv_support);

        if(item.getIs_support()==-1){
            iv_support.setImageResource(R.drawable.icon_fabulous);
        }else{
            iv_support.setImageResource(R.drawable.icon_a_fabulous);
        }
        helper.setText(R.id.tv_support_count,item.getSupport());





        helper.getView(R.id.ll_support).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getIs_support()==-1){
                    commentsupport(item.getId());
                    item.setSupport(Integer.parseInt(item.getSupport())+1+"");
                    item.setIs_support(1);
                }else{
                    item.setIs_support(-1);
                    if(Integer.parseInt(item.getSupport())>0){
                        item.setSupport(Integer.parseInt(item.getSupport())-1+"");
                    }
                    commentsupport(item.getId());
                }
                notifyItemChanged(helper.getPosition());
            }
        });



    }

    private void commentsupport(String id) {

        OkGo.post(Constant.COMMENTSUPPORT)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("cid",id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }
                });
    }
    /*//点赞或者取消点赞
    private void addSupport(String cid, int method) {
        OkGo.post(Constant.ADDSUPPORT)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("sid", sid)
                .params("cid", Integer.parseInt(cid))
                .params("method", method)
                .execute(new SimpleCommonCallback<ResPub>(this) {
                    @Override
                    public void onSuccess(ResPub resPub, Call call, Response response) {
                        if (resPub.getReturnCode() == 1) {
                        } else {
                            toast(resPub.getMessage());
                        }
                    }
                });

    }*/
}
