package cn.common.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.common.LibCommonUtils;
import cn.common.local.XUtils;


/**
 * Created by Administrator on 2016/11/14.
 * 可直接使用butterknife
 * 跳转动画
 * 网络连接成功通知
 */

public class ActivityCommBase extends AppCompatActivity {
    static {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                ex.printStackTrace();
                exitApp();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    protected boolean hasLoadData = false;
    BroadcastReceiver receiver;

    /**
     * 退出每个activity以退出应用
     */
    public static void exitApp() {
        // System.exit(0); //不能退出
        for (WeakReference activity : ActivityUtils.activitys) {
            if (activity != null) {
                Object o = activity.get();
                if (o instanceof Activity) {
                    ((Activity) o).finish();
                }
            }
        }
        Process.killProcess(Process.myPid());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        onViewEvent();
    }
    public String getEditValue(EditText e){
        return  e.getText().toString().trim();
    }

    /**
     * view 初始化完成，对view进行设置
     * 对父类的调用在其子类实现完成后
     */
    protected void onViewEvent() {
        loadData(hasLoadData);
        hasLoadData = false;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        contextAnimal();
    }

    /**
     * 跳转动画设置
     */
    protected void contextAnimal() {

    }

    protected void log(String s) {
        if (LibCommonUtils.isDebug) {
            Log.d(this.getClass().getSimpleName(), this.hashCode() + "@" + s);
        }
    }

    protected void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        contextAnimal();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    /**
     * 网络连接成功时
     */
    protected void onNetConnect() {
    }

    /**
     * 可以进行加载数据
     *
     * @param _hasLoadData true 是否已加载过数据
     */
    protected void loadData(boolean _hasLoadData) {
        log("loadData() called with: " + "_hasLoadData" + _hasLoadData);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == ActivityConstant.RequestCodeForCookieError) {
            loadDataForce();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 强制性的加载数据
     */
    public void loadDataForce() {
        loadData(false);
    }

    public void loadDataClear() {
        hasLoadData = false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.addActivity(this);
        if (!XUtils.isConnected(this)) {
            receiver = new NetBroadcastReceiver();
            registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        } else {
//            toast("无网络连接");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    private static class ActivityUtils {
        private static List<WeakReference> activitys = new ArrayList<>();

        /**
         * 添加activiyt到列表 以便退出时用到
         *
         * @param a
         */
        public static void addActivity(Activity a) {
            activitys.add(new WeakReference(a));
        }

        /**
         * 判断某个界面是否在栈顶
         *
         * @param context
         * @param className 某个界面名称
         */
        public static boolean activityIsTaskTop(Context context, String className) {
            if (context == null || TextUtils.isEmpty(className)) {
                return false;
            }
            //只能获到简单的信息
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
            if (list != null && list.size() > 0) {
                ComponentName cpn = list.get(0).topActivity;
                if (className.equals(cpn.getClassName())) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 判断当前栈内是否包含该activity实例
         *
         * @param className 否包含该activity实例
         */
        public static boolean activityInTask(String className) {
            for (WeakReference activity : activitys) {
                if (activity != null) {
                    Object o = activity.get();
                    if (o instanceof Activity) {
                        Activity _activity = ((Activity) o);
                        if (_activity.getClass().getName().equals(className)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        /**
         * 关闭位于className顶部的activity className不删除， 最顶部的不删除
         *
         * @param className
         * @param finishClass
         */
        public static void activityFinishAfter(String className, boolean finishClass) {
            boolean beginDestory = false;
            for (int i = 0; i < activitys.size() - 1; i++) {
                WeakReference activity = activitys.get(i);
                if (activity != null) {
                    Object o = activity.get();
                    if (o instanceof Activity) {
                        Activity _activity = ((Activity) o);
                        if (_activity.getClass().getName().equals(className) && !beginDestory) {
                        /*当前类 且 未开始清除时*/
                            beginDestory = true;
                            if (finishClass) {
                                _activity.finish();
                            }
                            continue;
                        }
                        if (beginDestory) {
                            _activity.finish();
                        }
                    }
                }
            }
        }


        /*除当前页面，其他全部关闭*/
        public static void activityDestoryOutThis(Activity context) {
            for (int i = 0; i < activitys.size(); i++) {
                WeakReference activity = activitys.get(i);
                if (activity != null) {
                    Object o = activity.get();
                    if (o instanceof Activity) {
                        Activity _activity = ((Activity) o);
                        if (_activity != context) {
                            _activity.finish();
                        }
                    }
                }
            }
        }
    }

    class NetBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (XUtils.isConnected(context)) {
                onNetConnect();
            }
        }
    }


}
