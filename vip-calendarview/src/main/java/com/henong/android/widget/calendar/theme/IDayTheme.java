package com.henong.android.widget.calendar.theme;

/**
 * 
 *
 * FILE: IDayTheme.java
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
public interface IDayTheme {
    /**
     * 选中日期的背景色
     * @return 16进制颜色值 hex color
     */
    int colorSelectBG();

    /**
     * 选中日期的颜色
     * @return 16进制颜色值 hex color
     */
    int colorSelectDay();

    /**
     * 今天日期颜色
     * @return 16进制颜色值 hex color
     */
    int colorToday();

    /**
     * 日历的整个背景色
     * @return
     */
    int colorMonthView();

    /**
     * 工作日的颜色
     * @return
     */
    int colorWeekday();

    /**
     * 周末的颜色
     * @return
     */
    int colorWeekend();

    /**
     * 事务装饰颜色
     * @return  16进制颜色值 hex color
     */
    int colorDecor();

    /**
     * 假日颜色
     * @return 16进制颜色值 hex color
     */
    int colorRest();

    /**
     * 班颜色
     * @return  16进制颜色值 hex color
     */
    int colorWork();

    /**
     * 描述文字颜色
     * @return 16进制颜色值 hex color
     */
    int colorDesc();

    /**
     * 日期大小
     * @return
     */
    int sizeDay();

    /**
     * 描述文字大小
     * @return
     */
    int sizeDesc();

    /**
     * 装饰器大小
     * @return
     */
    int sizeDecor();
    /**
     * 日期高度
     * @return
     */
    int dateHeight();

    /**
     * 线条颜色
     * @return
     */
    int colorLine();

    /**
     * 滑动模式  0是渐变滑动方式，1是没有滑动方式
     * @return
     */
    int smoothMode();
}
