package com.henong.android.widget.calendarview.materialcalendarview.format;


import com.henong.android.widget.calendarview.materialcalendarview.CalendarDay;

/**
 * Used to format a {@linkplain com.prolificinteractive.materialcalendarview.CalendarDay} to a string for the month/year title
 */
public interface TitleFormatter {

    /**
     * Converts the supplied day to a suitable month/year title
     *
     * @param day the day containing relevant month and year information
     * @return a label to display for the given month/year
     */
    CharSequence format(CalendarDay day);
}
