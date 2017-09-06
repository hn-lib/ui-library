package com.henong.android.widget.calendar.entity;

/**
 * 
 *
 * FILE: CalendarInfo.java
 *
 * MODULE: com.henong.android.widget.calendar.entity
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
public class CalendarInfo {
    /**
     * 年
     */
    public int year;
    /**
     * 月
     */
    public int month;
    /**
     * 日
     */
    public int day;
    /**
     * 描述词
     */
    public String des;
    /**
     * 是否为休、班。。1为休，2为班，默认为普通日期
     */
    public int rest;

    /**
     * 构造函数
     * @param year  事务年份
     * @param month 事务月份
     * @param day   事务日期号
     * @param des   事务描述
     */
    public CalendarInfo(int year, int month, int day, String des) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.des = des;
    }

    /**
     * 构造函数
     * @param year  事务年份
     * @param month 事务月份
     * @param day   事务日期号
     * @param des   事务描述
     * @param rest  是否为休、班。。1为休，2为班，默认为普通日期
     */
    public CalendarInfo(int year, int month, int day, String des, int rest) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.des = des;
        this.rest = rest;
    }
}
