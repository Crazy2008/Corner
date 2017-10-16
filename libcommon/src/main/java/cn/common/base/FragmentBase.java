package cn.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.common.LibCommonUtils;

/**
 * fragment 懒加载 http://www.jianshu.com/p/104be7cd72b6
 * butterknife
 */

public class FragmentBase extends Fragment {
    private Unbinder butterKnife;
    public Context mContext;

    protected void log(String s) {
        if (LibCommonUtils.isDebug) {
            Log.d(this.getClass().getSimpleName(), this.hashCode() + "@" + s);
        }
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    protected void toast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }


    private boolean hasLoadData = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        log("onResume() called with: " + "");
        super.onResume();
        onVisible();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        butterKnife = ButterKnife.bind(this, view);
        onViewEvent();
    }

    /**
     * view 初始化完成，对view进行设置
     */
    protected void onViewEvent() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        butterKnife.unbind();

    }

    /**
     * 实现Fragment数据的缓加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    protected void onInVisible() {
    }

    protected void onVisible() {
        if (getUserVisibleHint() && getView() != null) {
            loadData(hasLoadData);
            this.hasLoadData = true;
        }
    }


    /**
     * 可以进行加载数据
     *
     * @param _hasLoadData true 是否已加载过数据
     */
    protected void loadData(boolean _hasLoadData) {
        log("loadData() called with: " + "_hasLoadData" + _hasLoadData);
    }

    /**
     * 获取数据出错时调用，免得下次无法再获取数据了
     */
    public void loadDataClear() {
        hasLoadData = false;
    }

    /**
     * 强制性的加载数据
     */
    public void loadDataForce() {
        loadData(false);
    }


}