package com.henong;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.henong.library_base.R;

/**
 * Created by user on 2017/9/11.
 * 新增商品 分类的样式
 */

public class HNCommonItemView extends RelativeLayout {
    /**
     * 分类    杀虫剂    请选择>
     * mTitleTV  mDetailTV  mHintTV
     */
    private TextView mTitleTV;
    private TextView mHintTV;
    private TextView mDetailTV;



    public HNCommonItemView(Context context) {
        this(context,null);
    }

    public HNCommonItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HNCommonItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.item_common_item_view,this,true);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.HNCommonItemView,defStyleAttr,0);
        String title = a.getString(R.styleable.HNCommonItemView_title);
        String hint = a.getString(R.styleable.HNCommonItemView_hint);
        String detail = a.getString(R.styleable.HNCommonItemView_detail);
        float titleSize = a.getFloat(R.styleable.HNCommonItemView_titleTextSize,17);
        float hintSize = a.getFloat(R.styleable.HNCommonItemView_hintTextSize,17);
        float detailSize = a.getFloat(R.styleable.HNCommonItemView_detailTextSize,17);
        boolean hideRight = a.getBoolean(R.styleable.HNCommonItemView_hideRight, false);
        a.recycle();
        mTitleTV  = (TextView) findViewById(R.id.common_item_title);
        mHintTV = (TextView) findViewById(R.id.common_item_hint);
        mDetailTV = (TextView) findViewById(R.id.common_item_detail);
        mTitleTV.setText(title);
        mHintTV.setText(hint);
        mDetailTV.setText(detail);
        mTitleTV.setTextSize(titleSize);
        mHintTV.setTextSize(hintSize);
        mDetailTV.setTextSize(detailSize);
        if (hideRight){
            mHintTV.setVisibility(GONE);
        }else {
            mHintTV.setVisibility(VISIBLE);
        }

    }

    public void setTitle(String title){
        mTitleTV.setText(title);
    }
    public void setHint(String hint){
        mHintTV.setText(hint);
    }
    public void setTitleTextSize(float size){
        mTitleTV.setTextSize(size);
    }
    public void setHintTextSize(float size){
        mHintTV.setTextSize(size);
    }
    public void setDetail(String detail){
        mDetailTV.setText(detail);
    }
    public void setHideRight(boolean hideRight){
        if (hideRight){
            mHintTV.setVisibility(GONE);
        }else {
            mHintTV.setVisibility(VISIBLE);
        }
    }
}
