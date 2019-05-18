package ru.vsu.aviatickets.ui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConvert {

    public static String getTimeString(Date first, Date second) {
        String pattern = "HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return String.format("%s - %s", simpleDateFormat.format(first), simpleDateFormat.format(second));
    }

    public static String getDurationString(Integer duration) {
        if (duration == null || duration < 0)
            return null;
        int hours = duration / 60;
        int minutes = duration % 60;
        String hoursStr = (hours < 10 ? "0" : "") + hours;
        String minutesStr = (minutes < 10 ? "0" : "") + minutes;
        return String.format("%s:%s", hoursStr, minutesStr);
    }

    public static String getDayMonthString(Date first, Date second) {
        String pattern = "dd MMMM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return String.format("%s - %s", simpleDateFormat.format(first), simpleDateFormat.format(second));
    }

    public static String getDayMonthString(Date date) {
        String pattern = "dd MMMM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static String getDateWithSlashes(Date date) {
        if (date == null)
            return "";
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static Date getDateFromStringWithSlashes(String stringDate) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
