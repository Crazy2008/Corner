package cn.common.base;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 鑫 Administrator on 2017/4/28.
 */

public class ActivityBaseEventBus extends ActivityCommBase {

    @Override
    protected void onViewEvent() {
        super.onViewEvent();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
