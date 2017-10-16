package com.jiao.luo.app;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jiao.luo.R;
import com.jiao.luo.activity.HomeActivity;
import com.jiao.luo.activity.LoginActivity;
import com.jiao.luo.utils.Constant;
import com.jiao.luo.utils.SPTools;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.common.base.ActivityCommBase;

/**
 * Created  on 2017/6/5.
 * 启动页
 */

public class StartActivity extends ActivityCommBase {
    private final static int DELAYED_TIME = 3; /*单位秒*/
    private static final int REQUEST_CODE_FOR_PERMISSION = 101;
    @BindView(R.id.activity_startpage_img)
    ImageView activityStartpageImg;
    private boolean isFirstResume = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
        ButterKnife.bind(this);


        initLoginData();
        initSaveInstance(savedInstanceState);
        initState();

    }

    private void initLoginData() {
        String token = SPTools.getString(this, Constant.T, "");
        if (!token.equals("")) {
            Constant.TOKEN = token;
        }

        int userid = SPTools.getInt(this, Constant.U, -1);

        if (userid != -1) {
            Constant.USERID = userid;
        }

        String headPic = SPTools.getString(this, Constant.H, "");
        if (!headPic.equals("")) {
            Constant.HEADPIC = headPic;
        }

        String nickname = SPTools.getString(this, Constant.N, "");
        if (!nickname.equals("")) {
            Constant.NICKNAME = nickname;
        }
        boolean isLogin = SPTools.getBoolean(this, Constant.I, false);
        if (isLogin) {
            Constant.ISLOGIN = isLogin;
        }


    }


    private void initSaveInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            isFirstResume = savedInstanceState.getBoolean("isFirstResume");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.start_tv).setVisibility(View.GONE);
        String[] requestPermission = getRequestPermission();
        boolean hasPermission = checkPermission(requestPermission);
        boolean hasPermissionDeny = checkPermissionDeny(requestPermission);
        log("onResume() called " + isFirstResume + " " + hasPermission + " " + hasPermissionDeny);

        if (isFirstResume && hasPermissionDeny) { /*第一次进入有拒绝的权限*/
            showGoSetDialog();
            isFirstResume = false;
        } else if (isFirstResume && !hasPermission) { /*第一次进入有未获得的权限*/
            getPermission(requestPermission);
            isFirstResume = false;
        } else if (!isFirstResume && !hasPermission) { /*非第一次进入 未获得权限*/
            finish();
        } else if (hasPermission) {   /*获得了所需权限*/
            checkUserInfo();
        }

    }

    private String[] getRequestPermission() {
        String[] result = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
               /* Manifest.permission.READ_CONTACTS,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,*/
        };
        return result;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putBoolean("isFirstResume", isFirstResume);
        super.onSaveInstanceState(outState, outPersistentState);

    }

    /**
     * 检查所需的权限是否全部 通过
     *
     * @param requestPermission
     * @return true 全部已授权 false 有未授权的
     */
    private boolean checkPermission(String[] requestPermission) {
        boolean allGrant = true;
        for (int i = 0, size = requestPermission.length; i < size; i++) {
            String item = requestPermission[i];
            int state = ContextCompat.checkSelfPermission(this, item);
            if (state != PackageManager.PERMISSION_GRANTED) {
                allGrant = false;
                break;
            }
        }

        return allGrant;
    }

    /**
     * @param requestPermission
     * @return true 所需的权限有拒绝的 false 无拒绝的
     */
    private boolean checkPermissionDeny(String[] requestPermission) {
        boolean hasDeny = false;
        for (int i = 0, size = requestPermission.length; i < size; i++) {
            String item = requestPermission[i];
            int state = ContextCompat.checkSelfPermission(this, item);
            if (state == PackageManager.PERMISSION_DENIED) {
                hasDeny = true;
                break;
            }
        }

        return hasDeny;
    }


    /**
     * 沉浸式状态栏
     */
    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void getPermission(String[] requestPermission) {
        ActivityCompat.requestPermissions(this, requestPermission,
                REQUEST_CODE_FOR_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean allGrant = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                allGrant = false;
                break;
            }
        }
        if (allGrant) {
            onPermissionSuccess();
        } else {
            onPermissionFail();
        }
    }

    private void onPermissionFail() {
        showGoSetDialog();
    }

    private void onPermissionSuccess() {
//        checkUserInfo();
    }

    /**
     * 禁止且不再提示后再次提示
     */
    private void showGoSetDialog() {
        new AlertDialog.Builder(this)
                .setMessage("应用需要存储权限,通讯录权限，相机权限，录音权限")
                .setPositiveButton("开启权限", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + StartActivity.this.getPackageName()));
                        StartActivity.this.startActivity(intent);
                    }
                })
                .setNegativeButton("退出应用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();

                    }
                })
                .create().show();

    }

    /**
     * 检查是否存在用户信息
     * token值是否有效
     */
    private void checkUserInfo() {
        String token = SPTools.getString(this, Constant.TOKEN, "");
        if ("".equals(token) || token == null) {
            //TODO
//          goNext(LoginActivity.class);
            goMain();
        } else {
            goMain();
        }

    }

    private void goMain() {
        goNext(HomeActivity.class);

    }


    private void goLogin() {
        // TODO: 2017/6/29
        goNext(LoginActivity.class);

    }

    private void goNext(final Class<? extends Activity> clazz) {
     /* final TextView textView = (TextView) findViewById(R.id.start_tv);
        textView.setText(DELAYED_TIME + "s");
        textView.setVisibility(View.VISIBLE);


        final ValueAnimator animator = ValueAnimator.ofInt(DELAYED_TIME, 0)
                .setDuration(DELAYED_TIME * 1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currentValue = (int) valueAnimator.getAnimatedValue();
                if (currentValue <= 0) {
                    startActivity(new Intent(StartActivity.this, clazz));
                    finish();
                } else {
                    textView.setText(currentValue + "s");
                }
            }
        });
        animator.start();

        findViewById(R.id.activity_startpage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.cancel();
                startActivity(new Intent(StartActivity.this, clazz));
                finish();
            }
        });*/
        startActivity(new Intent(StartActivity.this, clazz));
    }


}
