package com.jiao.luo.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiao.luo.R;
import com.jiao.luo.Response.ResVoteResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class VoteResultAdapter extends BaseAdapter {


    private List<ResVoteResult.DataBean.OptionsBean> list =new ArrayList<>();

    public VoteResultAdapter() {


    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(parent.getContext(), R.layout.vote_result_item,null);
        }
        TextView tv_vote_count = (TextView) convertView.findViewById(R.id.tv_vote_count);
        TextView tv_content = (TextView) convertView.findViewById(R.id.tv_content);
        ResVoteResult.DataBean.OptionsBean optionsBean = list.get(position);

        tv_vote_count.setText(String.valueOf(optionsBean.getVote())) ;
        tv_content.setText(optionsBean.getOption());
        return convertView;
    }
    public void setDatas(List<ResVoteResult.DataBean.OptionsBean> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
