package com.henong.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.henong.dialog.base.BaseAlertDialog;

/**
 * Created by zhujunjun on 15/12/3.
 * 通用并且统一的文本提示框样式
 */
public class GeneralDialog extends BaseAlertDialog<GeneralDialog> {

    /**
     * title underline
     */
    private View v_line_title;
    /**
     * vertical line between btns
     */
    private View v_line_vertical;
    /**
     * vertical line between btns
     */
    private View v_line_vertical2;
    /**
     * horizontal line above btns
     */
    private View v_line_horizontal;
    /**
     * title underline color(标题下划线颜色)
     */
    private int titleLineColor = Color.parseColor("#DCDCDC");
    /**
     * title underline height(标题下划线高度)
     */
    private float titleLineHeight_DP = 1f;
    private float titleHeight_DP;
    /**
     * btn divider line color(对话框之间的分割线颜色(水平+垂直))
     */
    private int dividerColor = Color.parseColor("#DCDCDC");
    private float dividerWidth = 1f;

    public static final int STYLE_ONE = 0;
    public static final int STYLE_TWO = 1;
    private int style = STYLE_ONE;

    private View contentView;

    public GeneralDialog(Context context) {
        super(context);
        /** default value*/
        titleTextColor = Color.parseColor("#fc6a21");
        titleTextSize_SP = 18f;
        contentTextColor = Color.parseColor("#333333");
        contentTextSize_SP = 15f;
        leftBtnTextColor = Color.parseColor("#333333");
        rightBtnTextColor = Color.parseColor("#ffffff");
        middleBtnTextColor = Color.parseColor("#333333");
        /** default value*/
    }

    @Override
    public View onCreateView() {
        /** title */
        if (titleHeight_DP != 0) {
            tv_title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    dp2px(titleHeight_DP)));
        } else {
            tv_title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
        }
        tv_title.setGravity(Gravity.CENTER_VERTICAL);
        ll_container.addView(tv_title);

        /** title underline */
        v_line_title = new View(context);
        ll_container.addView(v_line_title);

        /** content */
        tv_content.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        if (contentView != null) {
            ll_container.addView(contentView);
        } else {
            ll_container.addView(tv_content);
        }

        v_line_horizontal = new View(context);
        v_line_horizontal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp2px(1f)));
        ll_container.addView(v_line_horizontal);

        /** btns */
        tv_btn_left.setLayoutParams(new LinearLayout.LayoutParams(0, dp2px(50), 1));
        ll_btns.addView(tv_btn_left);

        v_line_vertical = new View(context);
        v_line_vertical.setLayoutParams(new LinearLayout.LayoutParams(dp2px(dividerWidth), LinearLayout.LayoutParams.MATCH_PARENT));
        ll_btns.addView(v_line_vertical);

        tv_btn_middle.setLayoutParams(new LinearLayout.LayoutParams(0, dp2px(50), 1));
        ll_btns.addView(tv_btn_middle);

        v_line_vertical2 = new View(context);
        v_line_vertical2.setLayoutParams(new LinearLayout.LayoutParams(dp2px(dividerWidth), LinearLayout.LayoutParams.MATCH_PARENT));
        ll_btns.addView(v_line_vertical2);

        tv_btn_right.setLayoutParams(new LinearLayout.LayoutParams(0, dp2px(50), 1));
        ll_btns.addView(tv_btn_right);

        ll_container.addView(ll_btns);

        return ll_container;
    }

    public void hideButton() {
        ll_btns.setVisibility(View.GONE);
    }

    @Override
    public void setUIBeforeShow() {
        super.setUIBeforeShow();
        /** title underline */
        v_line_title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                dp2px(titleLineHeight_DP)));
        v_line_title.setBackgroundColor(titleLineColor);
        v_line_title.setVisibility(isTitleShow && style == STYLE_ONE ? View.VISIBLE : View.GONE);

        /** title */
        if (style == STYLE_ONE) {
            tv_title.setMinHeight(dp2px(60));
            tv_title.setGravity(Gravity.CENTER);
            tv_title.setPadding(dp2px(15), dp2px(5), dp2px(0), dp2px(5));
            tv_title.setVisibility(isTitleShow ? View.VISIBLE : View.GONE);
            v_line_title.setVisibility(View.GONE);
        } else if (style == STYLE_TWO) {
            tv_title.setGravity(Gravity.CENTER);
            tv_title.setPadding(dp2px(0), dp2px(15), dp2px(0), dp2px(0));
        }

        /** content */
        if (style == STYLE_ONE) {
            tv_content.setPadding(dp2px(15), dp2px(0), dp2px(15), dp2px(10));
            tv_content.setMinHeight(dp2px(68));
            tv_content.setGravity(contentGravity);
        } else if (style == STYLE_TWO) {
            tv_content.setPadding(dp2px(15), dp2px(7), dp2px(15), dp2px(20));
            tv_content.setMinHeight(dp2px(56));
            tv_content.setGravity(Gravity.CENTER);
        }

        /** btns */
        v_line_horizontal.setBackgroundColor(dividerColor);
        v_line_vertical.setBackgroundColor(dividerColor);
        v_line_vertical2.setBackgroundColor(dividerColor);

        if (btnNum == 1) {
            tv_btn_left.setVisibility(View.GONE);
            tv_btn_right.setVisibility(View.GONE);
            v_line_vertical.setVisibility(View.GONE);
            v_line_vertical2.setVisibility(View.GONE);
        } else if (btnNum == 2) {
            tv_btn_middle.setVisibility(View.GONE);
            v_line_vertical.setVisibility(View.GONE);
        }

        /**set background color and corner radius */
        float radius = dp2px(cornerRadius_DP);
        ll_container.setBackgroundDrawable(CornerUtils.cornerDrawable(bgColor, radius));
        tv_btn_left.setBackgroundDrawable(CornerUtils.btnSelector(radius, btnBgColor, btnPressColor, 0));
        tv_btn_right.setBackgroundDrawable(CornerUtils.btnSelector(radius, btnBgColorOrange, btnPressColorOrange, 1));
        tv_btn_middle.setBackgroundDrawable(CornerUtils.btnSelector(btnNum == 1 ? radius : 0, btnBgColor, btnPressColor, -1));
    }
    // --->属性设置

    public GeneralDialog setContent(View view) {
        this.contentView = view;
        return this;
    }

    /**
     * set style(设置style)
     */
    public GeneralDialog setStyle(int style) {
        this.style = style;
        return this;
    }

    /**
     * set title underline color(设置标题下划线颜色)
     */
    public GeneralDialog setTitleLineColor(int titleLineColor) {
        this.titleLineColor = titleLineColor;
        return this;
    }

    public GeneralDialog setTitleHeight(float height) {
        this.titleHeight_DP = height;
        return this;
    }

    /**
     * set title underline height(设置标题下划线高度)
     */
    public GeneralDialog setTitleLineHeight(float titleLineHeight_DP) {
        this.titleLineHeight_DP = titleLineHeight_DP;
        return this;
    }

    /**
     * set divider color between btns(设置btn分割线的颜色)
     */
    public GeneralDialog setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        return this;
    }
}
