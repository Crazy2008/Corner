package com.jiao.luo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jiao.luo.R;
import com.jiao.luo.Response.ResPub;
import com.jiao.luo.Response.ResVote;
import com.jiao.luo.adapter.VoteAdapter;
import com.jiao.luo.model.Vote;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.GlideUtils;
import com.jiao.luo.utils.PopupUtils;
import com.jiao.luo.utils.ToastUtils;
import com.jiao.luo.utils.ZF_PopupWindow;
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
 * 投票界面
 */
public class VoteActivity extends ActivityCommBase implements VoteAdapter.WytListener, View.OnClickListener {


    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_comment)
    ImageView ivComment;
    @BindView(R.id.ll_write)
    LinearLayout llWrite;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_comment_count)
    TextView tv_comment_count;
    private List<Vote> list;
    private List<Vote> checkList = new ArrayList<>();
    private VoteAdapter voteAdapter;
    private boolean isSingle = false;
    private String sid;
    private ImageView iv_banner;
    private TextView tv_title;
    private TextView tv_desc;


    private int subjectid ;
    private ResVote.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        ButterKnife.bind(this);
        sid = getIntent().getStringExtra(Constant.ID);
        initData();


    }

    private void initData() {
        OkGo.post(Constant.SPECIAL_DETAIL)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .params("sid", Integer.parseInt(sid))
                .execute(new SimpleCommonCallback<ResVote>(this) {
                    @Override
                    public void onSuccess(ResVote resVote, Call call, Response response) {
                        if ((resVote.getReturnCode() == 1)) {
                            data = resVote.getData();
                            initView();
                        }
                    }
                });
    }

    private void initView() {
        ResVote.DataBean.VoteInfoBean voteInfo = data.getVoteInfo();

        int ischeckbox = voteInfo.getIscheckbox();
        if (ischeckbox == 0) {
            isSingle = true;
        }
        //设置评论数量
        tv_comment_count.setText(data.getCommentCount());
        list = new ArrayList<>();
        //投票的数据列表
        List<ResVote.DataBean.VoteInfoBean.OptionBean> option = voteInfo.getOption();
        for (int i = 0; i < option.size(); i++) {
            ResVote.DataBean.VoteInfoBean.OptionBean bean = option.get(i);
            list.add(new Vote(bean.getOption(), false, bean.getOptionid(), bean.getSubjectid()));
        }
        voteAdapter = new VoteAdapter(this, list, this, isSingle);

//        new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice);

        View headerView = View.inflate(this, R.layout.vote_adapter_header, null);

        //头部的数据设置
        iv_banner = (ImageView) headerView.findViewById(R.id.iv_banner);
        GlideUtils.load(this,data.getBanner(),iv_banner);

        tv_title = (TextView) headerView.findViewById(R.id.tv_title);
        tv_title.setText(data.getTitle());

        tv_desc = (TextView) headerView.findViewById(R.id.tv_desc);
        tv_desc.setText(data.getDescription());
        lv.setAdapter(voteAdapter);
        View footView = View.inflate(this, R.layout.vote_adapter_btn_foot, null);
        //点击投票的按钮
        Button btn_vote = (Button) footView.findViewById(R.id.btn_vote);
        btn_vote.setOnClickListener(this);


        lv.addHeaderView(headerView);
        lv.addFooterView(footView);
    }


    @Override
    public void onSingleItem(int position) {
        List<Vote> datas = voteAdapter.getDatas();
        for (int i = 0; i < datas.size(); i++) {
            if (position == i) {
                if (datas.get(position).getCheck()) {
                    datas.get(position).setCheck(false);
                } else {
                    datas.get(position).setCheck(true);
                }
            } else {
                datas.get(i).setCheck(false);
            }

        }
        checkList.clear();
        checkList.addAll(datas);
        voteAdapter.setDatas(checkList);

    }

    //多选的回调
    @Override
    public void onMutipleItem(int position) {

        List<Vote> datas = voteAdapter.getDatas();
        for (int i = 0; i < datas.size(); i++) {
            if (position == i) {
                if (datas.get(position).getCheck()) {
                    datas.get(position).setCheck(false);
                } else {
                    datas.get(position).setCheck(true);
                }
            }

        }
        checkList.clear();
        checkList.addAll(datas);
        voteAdapter.setDatas(checkList);


    }

    @OnClick({R.id.iv_back, R.id.ll_write, R.id.iv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_write:
                Intent intent = new Intent(this, VoteCommentActivity.class);
                intent.putExtra(Constant.SID,sid);
                startActivity(intent);
                break;
            case R.id.iv_share:

                ZF_PopupWindow window = new ZF_PopupWindow(this);
                window.show(lv);
                PopupUtils.setBg(this,window);
                break;
        }
    }

    //投票的点击事件
    @Override
    public void onClick(View v) {
            if(!Constant.ISLOGIN){
                ToastUtils.showToast(this,"请先登录");
            }
        StringBuilder optionids = new StringBuilder();
        ArrayList<Object> subList = new ArrayList<>();

        for (Vote vote : checkList) {
            if (vote.getCheck()) {
                subjectid = vote.getSubjectid();
                subList.add(vote.getOptionid());
            }
        }
        System.out.println(optionids.toString());
        for (int i = 0; i < subList.size(); i++) {
            if (i == subList.size()-1) {
                optionids.append(subList.get(i));
            } else {
                optionids.append(subList.get(i) + ",");
            }

        }
        System.out.println(optionids.toString());
        /**
         *
         */
       OkGo.post(Constant.ADDVOTE)
                    .headers("tokens", Constant.TOKEN)
                    .params("userid", Constant.USERID)
                    .params("subjectid", subjectid )//int
                    .params("optionids", optionids.toString() )//String
                    .execute(new SimpleCommonCallback<ResPub>(this) {
                        @Override
                        public void onSuccess(ResPub resPub, Call call, Response response) {
                            if(resPub.getReturnCode()==1){
                                VoteActivity.this.finish();
                                intentVoteResult();
                            }else{
                                toast(resPub.getMessage());
                                new Handler().postDelayed(new Runnable(){
                                    public void run() {
                                        //execute the task
                                        intentVoteResult();
                                    }
                                }, 2*1000);

                            }

                        }
                    });
    }

    private void intentVoteResult() {
        Intent intent = new Intent(this,VoteResultActivity.class);
        intent.putExtra(Constant.SUBJECTID,subjectid);
        intent.putExtra(Constant.TITLE,data.getTitle());

        VoteActivity.this.startActivity(intent);
    }
}
