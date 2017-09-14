package com.henong.dialog.pickerview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.henong.library_base.R;
import com.henong.pickerview.LoopListener;
import com.henong.pickerview.LoopView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by niqiang on 16/1/27.
 */
public class DatePickerView extends LinearLayout {

    private static final int DEFAULT_MIN_YEAR = 1900;
    public LoopView yearLoopView;
    public LoopView monthLoopView;
    public LoopView dayLoopView;

    private int minYear; // min year
    private int maxYear; // max year
    private int yearPos = 0;
    private int monthPos = 0;
    private int dayPos = 0;
    private Context mContext;

    List<String> yearList = new ArrayList();
    List<String> monthList = new ArrayList();
    List<String> dayList = new ArrayList();

    public DatePickerView(Context context) {
        this(context, null);

    }

    public DatePickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DatePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_date_picker, this);
    }

    public DatePickerView(Context context, int minYear, int maxYear, String dateStr) {
        this(context);
        this.minYear = minYear;
        this.maxYear = maxYear;
        setSelectedDate(dateStr);
        init();
    }

    private void init() {
        yearLoopView = (LoopView) findViewById(R.id.picker_year);
        monthLoopView = (LoopView) findViewById(R.id.picker_month);
        dayLoopView = (LoopView) findViewById(R.id.picker_day);

        //set loopview text size
        yearLoopView.setTextSize(25);
        monthLoopView.setTextSize(25);
        dayLoopView.setTextSize(25);

        //set checked listen
        yearLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                if (item >= 0) {
                    yearPos = item;
                    initDayPickerView();
                }
            }
        });
        monthLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                if (item >= 0) {
                    monthPos = item;
                    initDayPickerView();
                }
            }
        });
        dayLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                if (item >= 0) {
                    dayPos = item;
                }
            }
        });
        initPickerViews(); // init year and month loop view
        initDayPickerView(); //init day loop view
    }

    public void setLoop(boolean isYearLoop, boolean isMonthLoop, boolean isDayLoop) {
        //do not loop,default can loop
        if (!isYearLoop) yearLoopView.setNotLoop();
        if (!isMonthLoop) monthLoopView.setNotLoop();
        if (!isDayLoop) dayLoopView.setNotLoop();
    }

    /**
     * Init year and month loop view,
     * Let the day loop view be handled separately
     */
    private void initPickerViews() {

        int yearCount = maxYear - minYear + 1;

        for (int i = 0; i < yearCount; i++) {
            yearList.add(format2LenStr(minYear + i));
        }

        for (int j = 0; j < 12; j++) {
            monthList.add(format2LenStr(j + 1));
        }

        yearLoopView.setArrayList((ArrayList) yearList);
        yearLoopView.setInitPosition(yearPos);

        monthLoopView.setArrayList((ArrayList) monthList);
        monthLoopView.setInitPosition(monthPos);
    }

    /**
     * Init day item
     */
    private void initDayPickerView() {

        int dayMaxInMonth;
        Calendar calendar = Calendar.getInstance();
        dayList = new ArrayList<String>();

        calendar.set(Calendar.YEAR, minYear + yearPos);
        calendar.set(Calendar.MONTH, monthPos);

        //get max day in month
        dayMaxInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < dayMaxInMonth; i++) {
            dayList.add(format2LenStr(i + 1));
        }

        dayLoopView.setArrayList((ArrayList) dayList);
        dayLoopView.setInitPosition(dayPos);
    }

    /**
     * set selected date position value when initView.
     *
     * @param dateStr
     */
    public void setSelectedDate(String dateStr) {

        if (!TextUtils.isEmpty(dateStr)) {

            long milliseconds = getLongFromyyyyMMdd(dateStr);
            Calendar calendar = Calendar.getInstance(Locale.CHINA);

            if (milliseconds != -1) {

                calendar.setTimeInMillis(milliseconds);
                yearPos = calendar.get(Calendar.YEAR) - minYear;
                monthPos = calendar.get(Calendar.MONTH);
                dayPos = calendar.get(Calendar.DAY_OF_MONTH) - 1;
            }
        }
    }

    public String getSelectedDate() {
        int year = minYear + yearPos;
        int month = monthPos + 1;
        int day = dayPos + 1;
        StringBuffer sb = new StringBuffer();

        sb.append(String.valueOf(year));
        sb.append("-");
        sb.append(format2LenStr(month));
        sb.append("-");
        sb.append(format2LenStr(day));
        return sb.toString();
    }

    /**
     * get long from yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static long getLongFromyyyyMMdd(String date) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date parse = null;
        try {
            parse = mFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parse != null) {
            return parse.getTime();
        } else {
            return -1;
        }
    }

    public static String getStrDate() {
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
        return dd.format(new Date());
    }

    /**
     * Transform int to String with prefix "0" if less than 10
     * @param num
     * @return
     */
    public static String format2LenStr(int num) {

        return (num < 10) ? "0" + num : String.valueOf(num);
    }
}
