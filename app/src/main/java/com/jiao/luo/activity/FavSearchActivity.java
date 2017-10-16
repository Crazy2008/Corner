package com.jiao.luo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiao.luo.R;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.SPTools;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavSearchActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_search);
        ButterKnife.bind(this);
        initView();
//        initData();


    }

    private void initView() {
        searchClick();
        Set<String> hashSet = SPTools.getHashSet(this, Constant.SP_HISTORY);
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
                    TextView textView = (TextView) View.inflate(FavSearchActivity.this, R.layout.activity_search_item, null);
                    textView.setText(s);
                    return textView;
                }
            });
            flLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    String s = datas.get(position);
                    System.out.println("s=" + s);
                    etSearch.setText(s);
                   Constant.keyword=s;
                    FavSearchActivity.this.setResult(RESULT_OK);
                    FavSearchActivity.this.finish();
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
                                                       InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                                       imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                                                       String keyWord = etSearch.getText().toString().trim();
                                                       Set<String> hashSet = SPTools.getHashSet(FavSearchActivity.this, Constant.SP_HISTORY);
                                                       SPTools.removeString(FavSearchActivity.this, Constant.SP_HISTORY);
                                                       if (hashSet != null) {
                                                           hashSet.add(keyWord);
                                                       } else {
                                                           hashSet = new HashSet<String>();
                                                           hashSet.add(keyWord);
                                                       }
                                                       SPTools.setHash(FavSearchActivity.this, Constant.SP_HISTORY, hashSet);
                                                       hashSet.clear();
                                                       Constant.keyword = keyWord;
                                                       FavSearchActivity.this.setResult(RESULT_OK);
                                                       FavSearchActivity.this.finish();
                                                       return true;
                                                   }
                                                   return false;
                                               }
                                           }

        );
    }

    @OnClick({R.id.et_Search, R.id.tv_cancle,R.id.ll_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_Search:
                break;
            case R.id.tv_cancle:
                finish();
            case R.id.ll_clear:

                Set<String> hashSet = SPTools.getHashSet(FavSearchActivity.this, Constant.SP_HISTORY);
                if (hashSet != null) {
                    hashSet.clear();
                    SPTools.setHash(FavSearchActivity.this, Constant.SP_HISTORY, hashSet);
                    llContent.setVisibility(View.GONE);
                }
                break;
        }
    }


}
