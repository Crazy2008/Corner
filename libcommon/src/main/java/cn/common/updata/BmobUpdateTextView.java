package cn.common.updata;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by 鑫 Administrator on 2017/5/3.
 * 去掉其中的多个换行
 */

public class BmobUpdateTextView extends android.support.v7.widget.AppCompatTextView {
    public BmobUpdateTextView(Context context) {
        this(context, null);
    }

    public BmobUpdateTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BmobUpdateTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        String string = (String) text;
        /*连续的换行替换成一个*/
        string = string.replaceAll("\n\n", "\n").trim();
        /*最后的换行符去掉*/
        if (string.length() > 1 && string.charAt(string.length() - 1) == '\n') {
            string = string.substring(0, string.length() - 2);
        }
        super.setText(string, type);
    }
}
