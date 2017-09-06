package com.henong.android.widget.calendar.theme;

/**
 * 
 *
 * FILE: IWeekTheme.java
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
public interface IWeekTheme {
    /**
     * 顶部线颜色
     * @return 16进制颜色值 hex color
     */
    int colorTopLinen();

    /**
     * 底部颜色
     * @return 16进制颜色值 hex color
     */
    int colorBottomLine();

    /**
     * 工作日颜色
     * @return 16进制颜色值 hex color
     */
    int colorWeekday();

    /**
     * 周末颜色
     * @return 16进制颜色值 hex color
     */
    int colorWeekend();

    /**
     * 星期的整个背景色
     * @return
     */
    int colorWeekView();
    /**
     * 线的宽度
     * @return px
     */
    int sizeLine();

    /**
     * 字体大小
     * @return px
     */
    int sizeText();
}
