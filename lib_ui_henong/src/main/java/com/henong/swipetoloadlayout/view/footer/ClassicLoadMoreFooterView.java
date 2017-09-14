package com.henong.swipetoloadlayout.view.footer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.henong.library_base.R;
import com.henong.swipetoloadlayout.SwipeLoadMoreFooterLayout;

/**
 * Created by Aspsine on 2015/9/2.
 */
public class ClassicLoadMoreFooterView extends SwipeLoadMoreFooterLayout {
    private TextView tvLoadMore;
    private ImageView ivSuccess;
    private ProgressBar progressBar;

    private int mFooterHeight;

    private boolean isLoadMoreEnd = false;

    public ClassicLoadMoreFooterView(Context context) {
        this(context, null);
    }

    public ClassicLoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setIsLoadMoreEnd(boolean isLoadMoreEnd) {
        this.isLoadMoreEnd = isLoadMoreEnd;
    }

    public ClassicLoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFooterHeight = getResources().getDimensionPixelOffset(R.dimen.load_more_footer_height_classic);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvLoadMore = (TextView) findViewById(R.id.tvLoadMore);
        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onPrepare() {
        if (isLoadMoreEnd) {
            tvLoadMore.setText("已经到底啦O(∩_∩)O！");
            ivSuccess.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            return;
        }
        ivSuccess.setVisibility(GONE);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (isLoadMoreEnd) {
            tvLoadMore.setText("已经到底啦O(∩_∩)O！");
            ivSuccess.setVisibility(View.GONE);
            return;
        }
        if (!isComplete) {
            ivSuccess.setVisibility(GONE);
            progressBar.setVisibility(GONE);
            if (-y >= mFooterHeight) {
                tvLoadMore.setText("松开加载更多");
            } else {
                tvLoadMore.setText("正在上拉…");
            }
        }
    }

    @Override
    public void onLoadMore() {
        if (isLoadMoreEnd) {
            tvLoadMore.setText("已经到底啦O(∩_∩)O！");
            ivSuccess.setVisibility(View.GONE);
            return;
        }
        tvLoadMore.setText("正在加载更多...");
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        if (isLoadMoreEnd) {
            tvLoadMore.setText("已经到底啦O(∩_∩)O！");
            ivSuccess.setVisibility(View.GONE);
            return;
        }
        progressBar.setVisibility(GONE);
        ivSuccess.setVisibility(VISIBLE);
    }

    @Override
    public void onReset() {
        if (isLoadMoreEnd) {
            tvLoadMore.setText("已经到底啦O(∩_∩)O！");
            ivSuccess.setVisibility(View.GONE);
            return;
        }
        ivSuccess.setVisibility(GONE);
    }

}
