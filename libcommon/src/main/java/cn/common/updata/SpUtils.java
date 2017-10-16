package cn.common.updata;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 鑫 Administrator on 2017/5/19.
 */

class SpUtils {
    public static void lastUpdata(Context context, long lastTime) {
        getSp(context).edit().putLong("time", lastTime).commit();
    }

    private static SharedPreferences getSp(Context context) {
        return context.getSharedPreferences("updata", Context.MODE_PRIVATE);
    }

    public static long lastUpdata(Context context) {
        return getSp(context).getLong("time", 0);
    }
}
