/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2016 Shanghai HeNong Network Technology Co.,Ltd.All rights reserved.
 *
 * This source code has been made available to you by HeNong on
 * AS-IS.Anyone receiving this source is licensed under
 * HeNong copyrights to use it in any way he or she deems fit,including
 * copying it,modifying it,compiling it,and redistributing it either with
 * or without modifictions.
 *
 * Any person who transfers this source code or any derivative work must
 * include the HeNong copyright notice, this paragraph,and the preceding
 * two paragraphs in the transferred software.
 *
 * Copyright (c) 2016 Shanghai HeNong Network Technology Co.,Ltd.All rights reserved.
 * LICENSED MATERIAL - PROGRAM PROPERTY OF HENONG
 */
package com.henong.android.widget.calendar.theme;

import android.graphics.Color;

import com.henong.android.widget.vipcalendar.R;

/**
 * 
 * FILE: ServiceCallendarDayTheme.java
 * 
 * MODULE: com.henong.android.widget.calendar.theme
 * 
 * PURPOSE: TODO
 * 
 * AUTHOR(S): Joker.Chen
 * 
 * GROUP: R&D
 * 
 * DATE CREATED: 2016-11-17
 * 
 * REFERENCE DOCUMENT ID:
 * 
 * MODIFICATIONS: Date user Name Description 2016-11-17 Joker Create this file
 */

public class ServiceCallendarDayTheme implements IDayTheme {

	@Override
	public int colorSelectBG() {
		return R.color.orange1;
	}

	@Override
	public int colorSelectDay() {
		return Color.WHITE;
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
		return R.color.gray_white;
	}

	@Override
	public int colorWeekend() {
		return R.color.gray_white;
	}

	@Override
	public int colorDecor() {
		return Color.parseColor("#68CB00");
	}

	@Override
	public int colorRest() {
		return Color.parseColor("#68CB00");
	}

	@Override
	public int colorWork() {
		return Color.parseColor("#FF9B12");
	}

	@Override
	public int colorDesc() {
		return Color.parseColor("#FF9B12");
	}

	@Override
	public int sizeDay() {
		// TODO Auto-generated method stub
		return 30;
	}

	@Override
	public int sizeDesc() {
		// TODO Auto-generated method stub
		return 15;
	}

	@Override
	public int sizeDecor() {
		return 6;
	}

	@Override
	public int dateHeight() {
		return 70;
	}

	@Override
	public int colorLine() {
		return Color.WHITE;
	}

	@Override
	public int smoothMode() {
		return 0;
	}

}
