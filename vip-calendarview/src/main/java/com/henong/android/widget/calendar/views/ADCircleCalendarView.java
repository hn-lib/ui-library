package com.henong.android.widget.calendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henong.android.widget.calendar.component.ADCircleMonthView;
import com.henong.android.widget.calendar.component.MonthView;
import com.henong.android.widget.calendar.component.WeekView;
import com.henong.android.widget.calendar.entity.CalendarInfo;
import com.henong.android.widget.calendar.theme.IDayTheme;
import com.henong.android.widget.calendar.theme.IWeekTheme;
import com.henong.android.widget.vipcalendar.R;

import java.util.List;

/**
 * 
 * 
 * FILE: ADCircleCalendarView.java
 * 
 * MODULE: com.henong.android.widget.calendar.views
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
 * MODIFICATIONS: Date user Name Description 2016-11-16 Joker Create this file
 */
public class ADCircleCalendarView extends LinearLayout implements View.OnClickListener {
	private WeekView weekView;
	private ADCircleMonthView circleMonthView;
	private TextView textViewYear, textViewMonth;

	public ADCircleCalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(LinearLayout.VERTICAL);
		LayoutParams llParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		View view = LayoutInflater.from(context).inflate(R.layout.display_grid_date, null);
		weekView = new WeekView(context, null);
		circleMonthView = new ADCircleMonthView(context, null);
		addView(view, llParams);
		addView(weekView, llParams);
		addView(circleMonthView, llParams);
		view.findViewById(R.id.left).setOnClickListener(this);
		view.findViewById(R.id.right).setOnClickListener(this);
		textViewYear = (TextView) view.findViewById(R.id.year);
		textViewMonth = (TextView) view.findViewById(R.id.month);
		/**
		 * 设置默认的顶部时间
		 */
		textViewYear.setText(circleMonthView.getSelYear() + "年");
		textViewMonth.setText((circleMonthView.getSelMonth() + 1) + "月");
		/**
		 * 更新顶部时间
		 */
		circleMonthView.setMonthLisener(new MonthView.IMonthLisener() {
			@Override
			public void setTextMonth() {
				textViewYear.setText(circleMonthView.getSelYear() + "年");
				textViewMonth.setText((circleMonthView.getSelMonth() + 1) + "月");
			}
		});
	}

	public MonthView getMothView() {
		return circleMonthView;
	}

	/**
	 * 设置日历点击事件
	 * 
	 * @param dateClick
	 */
	public void setDateClick(MonthView.IDateClick dateClick) {
		circleMonthView.setDateClick(dateClick);
	}

	/**
	 * 设置星期的形式
	 * 
	 * @param weekString
	 *            默认值 "日","一","二","三","四","五","六"
	 */
	public void setWeekString(String[] weekString) {
		weekView.setWeekString(weekString);
	}

	public void setCalendarInfos(List<CalendarInfo> calendarInfos) {
		circleMonthView.setCalendarInfos(calendarInfos);
	}

	public void setDayTheme(IDayTheme theme) {
		circleMonthView.setTheme(theme);
	}

	public void setWeekTheme(IWeekTheme weekTheme) {
		weekView.setWeekTheme(weekTheme);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.left) {
			circleMonthView.onLeftClick();
		} else {
			circleMonthView.onRightClick();
		}
	}
}
