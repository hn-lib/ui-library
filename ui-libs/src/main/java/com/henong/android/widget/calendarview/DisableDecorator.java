package com.henong.android.widget.calendarview;

import com.henong.android.widget.calendarview.materialcalendarview.CalendarDay;
import com.henong.android.widget.calendarview.materialcalendarview.DayViewDecorator;
import com.henong.android.widget.calendarview.materialcalendarview.DayViewFacade;

/**
 * Created by user on 2017/4/1.
 */

public class DisableDecorator implements DayViewDecorator {
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.getYear() > CalendarDay.today().getYear()
                ||((day.getYear() == CalendarDay.today().getYear())
                    && ((day.getMonth() > CalendarDay.today().getMonth())
                        || ((day.getMonth() == CalendarDay.today().getMonth())
                             && (day.getDay() > CalendarDay.today().getDay()))));
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setDaysDisabled(true);
    }
}
