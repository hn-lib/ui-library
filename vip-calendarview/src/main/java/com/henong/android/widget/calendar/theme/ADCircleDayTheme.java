package com.henong.android.widget.calendar.theme;

import android.graphics.Color;

/**
 * 
 *
 * FILE: ADCircleDayTheme.java
 *
 * MODULE: com.henong.android.widget.calendar.theme
 *
 * PURPOSE: TODO
 *
 * AUTHOR(S): Joker.Chen
 *
 * GROUP: R&D
 *
 * DATE CREATED: 2016-11-16
 *
 * REFERENCE DOCUMENT ID: 
 *
 * MODIFICATIONS:
 * Date       user Name   Description
 * 2016-11-16    Joker       Create this file
 */
public class ADCircleDayTheme implements IDayTheme {
    @Override
    public int colorSelectBG() {
        return Color.parseColor("#38C0C3");
    }

    @Override
    public int colorSelectDay() {
        return Color.parseColor("#FFFFFF");
    }

    @Override
    public int colorToday() {
        return Color.parseColor("#68CB00");
    }

    @Override
    public int colorMonthView() {
        return Color.parseColor("#FFFFFF");
    }

    @Override
    public int colorWeekday() {
        return Color.parseColor("#4F4F4F");
    }

    @Override
    public int colorWeekend() {
        return Color.parseColor("#BEBEBE");
    }

    @Override
    public int colorDecor() {
        return Color.parseColor("#4AB9AE");
    }

    @Override
    public int colorRest() {
        return Color.parseColor("#2AC5C8");
    }

    @Override
    public int colorWork() {
        return Color.parseColor("#C78D7D");
    }

    @Override
    public int colorDesc() {
        return Color.parseColor("#4F4F4F");
    }

    @Override
    public int sizeDay() {
        return 30;
    }

    @Override
    public int sizeDesc() {
        return 14;
    }

    @Override
    public int sizeDecor() {
        return 4;
    }

    @Override
    public int dateHeight() {
        return 70;
    }

    @Override
    public int colorLine() {
        return Color.parseColor("#CBCBCB");
    }

    @Override
    public int smoothMode() {
        return 0;
    }
}
