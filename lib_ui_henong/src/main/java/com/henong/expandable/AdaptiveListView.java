package com.henong.expandable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by zyd on 2017/4/12.、
 * 自适应LlistView
 */

public class AdaptiveListView extends ListView {

    public AdaptiveListView(Context context) {
        super(context);
    }

    public AdaptiveListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdaptiveListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
