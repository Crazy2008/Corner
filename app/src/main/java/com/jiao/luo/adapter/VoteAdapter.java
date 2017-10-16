package com.jiao.luo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiao.luo.R;
import com.jiao.luo.model.Vote;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class VoteAdapter extends BaseAdapter {


    private final Context context;
    private final List<Vote> list;
    private final WytListener listener;
    private boolean isSingle;

    public VoteAdapter(Context context, List<Vote> list, WytListener listener, boolean isSingle) {
        this.context = context;
        this.list = list;
        this.listener = listener;
        this.isSingle = isSingle;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.activity_vote_item, null);
        }
        LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.ll);
        ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
        TextView tv_content = (TextView) convertView.findViewById(R.id.tv_content);

        Vote vote = list.get(position);
        if (vote.getCheck()) {
            iv_icon.setBackgroundResource(R.drawable.check_selected);
        } else {
            iv_icon.setBackgroundResource(R.drawable.check_none);
        }
        tv_content.setText(vote.getTitle());
        //如果是单选

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSingle) {
                    listener.onSingleItem(position);
                } else {
                    listener.onMutipleItem(position);
                }
            }
        });


        return convertView;
    }

    public interface WytListener {
        //单选
        void onSingleItem(int position);
        //多选
        void onMutipleItem(int position);

    }

    public void setDatas(List<Vote> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public  List<Vote> getDatas(){
        return this.list;
    }
}
