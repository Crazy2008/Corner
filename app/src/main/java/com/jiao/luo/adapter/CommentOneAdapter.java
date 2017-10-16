package com.jiao.luo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiao.luo.R;
import com.jiao.luo.Response.ResComment;
import com.jiao.luo.Response.ResComment.DataBean.ListBean;
import com.jiao.luo.activity.CommentActivity;
import com.jiao.luo.interf.PubListener;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.GlideUtils;
import com.jiao.luo.utils.IntentUtils;
import com.jiao.luo.utils.PopupUtils;
import com.jiao.luo.utils.ScreenUtils;
import com.jiao.luo.utils.TimeUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 大操场评论adapter
 */

public class CommentOneAdapter extends BaseQuickAdapter<ListBean, BaseViewHolder> {


    private final int layoutResId;
    private final List<ListBean> data;
    private final Context context;
    private PubListener listener;
    private int sid;


    public CommentOneAdapter(int layoutResId, List<ListBean> data, Context context, PubListener listener, int sid) {
        super(layoutResId, data);
        this.layoutResId = layoutResId;
        this.data = data;
        this.context = context;
        this.listener = listener;
        this.sid = sid;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final ResComment.DataBean.ListBean item) {
          /*  //设置虚线
//            holder.setBackgroundRes(R.id.ll,R.drawable.shape_dashed);
//            ll.setBackgroundResource(R.drawable.shape_dashed);*/
        final int position = holder.getPosition();
        final LinearLayout ll = holder.getView(R.id.ll);
        ImageView iv_support = holder.getView(R.id.iv_support);
        if (position == 0) {
            holder.setBackgroundRes(R.id.ll, R.drawable.shape_dashed);
//            ll.setBackgroundResource(R.drawable.shape_dashed);
            holder.setText(R.id.tv_name, Constant.NICKNAME);
            CircleImageView iv_icon = holder.getView(R.id.iv_icon);
            GlideUtils.load(context,Constant.HEADPIC,iv_icon);

            holder.getView(R.id.ll_write).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.intentWrite(context, sid);
                }
            });

            holder.getView(R.id.ll_praiseView).setVisibility(View.GONE);

            //内容
            holder.getView(R.id.ll_content).setVisibility(View.GONE);
            holder.getView(R.id.ll_praiseView).setVisibility(View.GONE);
            TextView tv_name = holder.getView(R.id.tv_name);
            tv_name.setTextColor(Color.parseColor("#757575"));
            ll.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            holder.getView(R.id.ll_first).setVisibility(View.VISIBLE);
        } else {
            iv_support.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItem(position, 1);
                }
            });
            //设置点赞数量
            holder.setText(R.id.tv_support, item.getSupport());
            if (item.isSup()) {
                iv_support.setBackgroundResource(R.drawable.icon_a_fabulous);
            } else {
                iv_support.setBackgroundResource(R.drawable.icon_fabulous);
            }


            //设置头像和姓名
            if(item.getUser()!=null){
                holder.setText(R.id.tv_name, item.getUser().getUsername());
                CircleImageView iv_icon = holder.getView(R.id.iv_icon);
                holder.getView(R.id.ll);
                GlideUtils.load(context,item.getUser().getHeadPic(),iv_icon);
            }
          ;
            //说点什么的点击事件


            String content = "你是什么你是什么你是什么你是什么你是什么你是什么你是什么你是什么你是什么你是什么你是什么你是什么你是什么你是什么你是什么你是什么你是什么";
            //点赞的控件
            TextView tv_desc = holder.getView(R.id.tv_desc);
            holder.setText(R.id.tv_desc, item.getContent());
            holder.setText(R.id.tv_support, item.getSupport());
            System.out.println("holder = [" + holder + "], position = [" + position + "]");
            final String[] colorList = {"#52D2DD", "#E86960", "#FDA740", "#A9CD5F", "#82D7AE", "#9182C5", "#F1989C"};
            int i = position % colorList.length;
            final int color = Color.parseColor(colorList[i]);
            holder.setBackgroundColor(R.id.ll, color);


            TextUtils.TruncateAt ellipsize = tv_desc.getEllipsize();
//            if (ellipsize.equals(TextUtils.TruncateAt.END)) {
            if (item.getContent().length() > 30) {
                System.out.println("TruncateAt.END");
                holder.getView(R.id.iv_arrow).setVisibility(View.VISIBLE);
                //点击按钮显示全部内容
                holder.getView(R.id.iv_arrow).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("v = [" + v + "]");
//                        List<ListBean> data = CommentOneAdapter.this.getData();
                        showPopupWindow(ll, color, item, position);
//                        showPopupWindow(ll, color, data.get(position));
                    }
                });
            }

            long creat_at = Long.valueOf(item.getCreat_at());
            long nowTime = System.currentTimeMillis();
            long eigtherTime = nowTime - creat_at;
            String s = TimeUtils.stmpToDate(eigtherTime);
//            holder.setText(R.id.tv_time, s);
//            holder.setText(R.id.tv_desc, item.getContent());
        }
    }

    //TODO
    private void showPopupWindow(LinearLayout ll, int color, final ResComment.DataBean.ListBean comment, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_comment, null);
        //名字
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_name.setText(comment.getUsername());
        TextView tv_desc = (TextView) view.findViewById(R.id.tv_desc);

        LinearLayout ll_support = (LinearLayout) view.findViewById(R.id.ll_support);
        final ImageView iv_support = (ImageView) view.findViewById(R.id.iv_support);
        //点赞


        final TextView tv_support_count = (TextView) view.findViewById(R.id.tv_support_count);
        //背景
        LinearLayout ll_content = (LinearLayout) view.findViewById(R.id.ll_content);
        //点赞数
        tv_support_count.setText(comment.getSupport());
        //点赞图标

        ImageView civ_icon = (CircleImageView) view.findViewById(R.id.civ_icon);

        GlideUtils.load(context,comment.getUser().getHeadPic(),civ_icon);
        boolean sup = comment.isSup();
        if (comment.isSup()) {
            iv_support.setImageResource(R.drawable.icon_a_fabulous);
        } else {
            iv_support.setImageResource(R.drawable.icon_fabulous);

        }

        ll_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean sup = comment.isSup();
                if (comment.isSup()) {
                    iv_support.setImageResource(R.drawable.icon_fabulous);
                    int support = Integer.parseInt(comment.getSupport());
                    if (support > 0) {
                        tv_support_count.setText(String.valueOf(support - 1));
                    } else {
                        tv_support_count.setText("0");
                    }

                } else {
                    iv_support.setImageResource(R.drawable.icon_a_fabulous);
                    int support = Integer.parseInt(comment.getSupport());
                    tv_support_count.setText(String.valueOf(support + 1));

                }
                listener.onItem(position, 1);
            }
        });

//        tv_support.setText(comment.getSupport() + "赞");
        ll_content.setBackgroundColor(color);
        tv_desc.setText(comment.getContent());
        PopupWindow popupWindow = new PopupWindow(view, ScreenUtils.getScreenWidth(context) - 200, ScreenUtils.getScreenWidth(context) - 100);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        popupWindow.showAtLocation(ll, Gravity.CENTER, 0, 0);
        final CommentActivity activity = (CommentActivity) context;
        //设置pop背景为灰色
        PopupUtils.setBg(activity, popupWindow);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }


}
