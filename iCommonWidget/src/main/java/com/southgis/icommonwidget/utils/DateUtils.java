package com.southgis.icommonwidget.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具类
 */
@SuppressLint("SimpleDateFormat")
public final class DateUtils {
    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat FORMAT_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前时间---格式yyyy-MM-dd
     */
    public static String getCurrentDate() {
        return FORMAT_DATE.format(new Date());
    }
    public static String getCurrentDate(String stringFormat) {
        return new SimpleDateFormat(stringFormat).format(new Date());
    }
    public static String getCurrentDate(SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当前时间--格式yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentTime() {
        return FORMAT_DATE_TIME.format(new Date());
    }

    /**
     * 获取某天是星期几
     */
    public static String getMonthDayWeek(Date date) {
        if (null == date) {
            return "";
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int week = c.get(Calendar.DAY_OF_WEEK);
        String weekStr = null;

        switch (week) {
            case Calendar.SUNDAY:
                weekStr = "周日";
                break;
            case Calendar.MONDAY:
                weekStr = "周一";
                break;
            case Calendar.TUESDAY:
                weekStr = "周二";
                break;
            case Calendar.WEDNESDAY:
                weekStr = "周三";
                break;
            case Calendar.THURSDAY:
                weekStr = "周四";
                break;
            case Calendar.FRIDAY:
                weekStr = "周五";
                break;
            case Calendar.SATURDAY:
                weekStr = "周六";
                break;
        }
        return weekStr;
    }

    /**
     * 将指定时间的月份提前或减少 value个月
     */
    public static String getTimePassedMonth(Date date, int value, SimpleDateFormat format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, value);
        if (format == null) {
            format = FORMAT_DATE;
        }

        return format.format(calendar.getTime());
    }

    /**
     * 将String类型的日期转化为date型的
     */
    public static Date getDateFromString(String date, SimpleDateFormat format) {
        try {
            return format.parse(date);
        } catch (ParseException exception) {
        }
        return new Date();
    }
    public static Date getDateFromString(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException exception) {
        }
        return new Date();
    }
    public static String getDateFromString(Date date, String format) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 转换日期格式
     */
    public static String convertDateString(String date, SimpleDateFormat format1, SimpleDateFormat format2) {
        try {
            return format2.format(format1.parse(date));
        } catch (ParseException exception) {
        }
        return "";
    }

    /**
     * 比较两个日期的大小（两个日期格式必须为yyyy-MM-dd HH:mm:ss）
     */
    public static boolean isBefore(String date1, String date2) {
        try {
            boolean isBefore = FORMAT_DATE_TIME.parse(date1).before(FORMAT_DATE_TIME.parse(date2));
            return isBefore;
        } catch (ParseException exception) {
        }
        return false;
    }

    /**
     * 根据--指定时间格式--比较时间大小
     */
    public static boolean isBefore(String date1, String date2, String mFormat) {
        SimpleDateFormat format = new SimpleDateFormat(mFormat);
        try {
            boolean isBefore = format.parse(date1).before(format.parse(date2));
            return isBefore;
        } catch (ParseException exception) {
        }
        return false;
    }

    /**
     * 根据--指定时间格式--比较时间大小
     */
    public static boolean isDateAfter(String date1, String date2, String mFormat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(mFormat);
            Date date = format.parse(date1);
            boolean isAfter = format.parse(date2).after(date);
            return isAfter;
        } catch (ParseException exception) {
        }
        return false;
    }
}
