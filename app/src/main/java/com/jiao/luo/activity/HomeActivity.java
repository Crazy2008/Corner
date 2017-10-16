package com.jiao.luo.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.jiao.luo.R;
import com.jiao.luo.Response.ResPub;
import com.jiao.luo.adapter.ViewPagerAdapter;
import com.jiao.luo.fragment.HomeFragment;
import com.jiao.luo.fragment.SettingFragment;
import com.jiao.luo.fragment.SportFragment;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.widget.ControlScrollViewPager;
import com.jiao.luo.widget.MyDialog;
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
 * 首页activity界面
 */
public class HomeActivity extends ActivityCommBase {

    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.iv_sport)
    ImageView ivSport;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    private ControlScrollViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    private MyDialog myDialog;
    private long clickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        int page = getIntent().getIntExtra(Constant.PAGE, -1);

        ButterKnife.bind(this);
        viewPager = (ControlScrollViewPager) findViewById(R.id.viewpager);
       /* bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        //设置bottomNavigationView点击监听事件
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });*/
        //禁止ViewPager滑动
        viewPager.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });
        setupViewPager(viewPager);


        //话题详情页跳过来的哪一页
        if(page!=-1){
            if(page==0){
                jumpPage0(0);
            }
            else if (page==1){
                jumpPage1(1);
            }
            else if(page==2){
                jumpPage2(2);
            }
        }
    }

   /* //设置bottomNavigationView点击监听事件
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                //首页
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                //操场
                case R.id.navigation_sport:
                    viewPager.setCurrentItem(1);
                    return true;
                //设置
                case R.id.navigation_setting:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };*/

    // 设置viewpager的fragment
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new SportFragment());
        fragmentList.add(new SettingFragment());
        adapter.addFragmentList(fragmentList);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("HomeActivity keyCode = [" + keyCode + "], event = [" + event + "]");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            long start = SystemClock.currentThreadTimeMillis();
            if (SystemClock.uptimeMillis() - clickTime <= 1500) {
                //如果两次的时间差＜1s，就不执行操作
                showExitDialog();
            } else {
                //当前系统时间的毫秒值
                clickTime = SystemClock.uptimeMillis();
                toast("再次点击退出");
                return false;
            }
        }
        return true;

    }


    private void showExitDialog() {
        myDialog = new MyDialog(this);
        myDialog.setMessage("确定要退出吗？");
        myDialog.setYesOnclickListener("确定", new MyDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                myDialog.dismiss();
//                loginOut();
                exitApp();
            }
        });
        myDialog.setNoOnclickListener("取消", new MyDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    //退出登录
    private void loginOut() {
        OkGo.post(Constant.LOGOUT)
                .headers("tokens", Constant.TOKEN)
                .params("userid", Constant.USERID)
                .execute(new SimpleCommonCallback<ResPub>(this) {
                    @Override
                    public void onSuccess(ResPub resPub, Call call, Response response) {
                        if (resPub.getReturnCode() == 1) {
                          /*  Constant.clearLoginData();
                            Constant.clearLoginSPData(HomeActivity.this);*/

                        } else {
                            Constant.clearLoginData();
                            Constant.clearLoginSPData(HomeActivity.this);
                            toast("尚未登录");
                            exitApp();
                        }
                    }
                });
    }


    @OnClick({R.id.rl_home, R.id.rl_sport, R.id.rl_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_home:
                jumpPage0(0);
                break;
            case R.id.rl_sport:
                jumpPage1(1);
                break;
            case R.id.rl_user:
                jumpPage2(2);
                break;
        }
    }

    private void jumpPage0(int page) {
        ivHome.setImageResource(R.drawable.tab_find);
        ivSport.setImageResource(R.drawable.tab_set_playground);
        ivUser.setImageResource(R.drawable.tab_set_user);
        viewPager.setCurrentItem(page);
    }
    private void jumpPage1(int page) {
        ivHome.setImageResource(R.drawable.tab_set_find);
        ivSport.setImageResource(R.drawable.tab_playground);
        ivUser.setImageResource(R.drawable.tab_set_user);
        viewPager.setCurrentItem(page);
    }
    private void jumpPage2(int page) {
        ivHome.setImageResource(R.drawable.tab_set_find);
        ivSport.setImageResource(R.drawable.tab_set_playground);
        ivUser.setImageResource(R.drawable.tab_user);
        viewPager.setCurrentItem(page);
    }


}
