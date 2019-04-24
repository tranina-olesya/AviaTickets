package ru.vsu.aviatickets.ui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {

    public static String getTimeString(Date first, Date second) {
        String pattern = "HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return String.format("%s - %s",  simpleDateFormat.format(first), simpleDateFormat.format(second));
    }

    public static String getDurationString(Integer duration) {
        int hours = duration / 60;
        int minutes = duration % 60;
        String hoursStr = (hours < 10 ? "0" : "") + String.valueOf(hours);
        String minutesStr = (minutes < 10 ? "0" : "") + String.valueOf(minutes);
        return String.format("%s:%s", hoursStr, minutesStr);
    }
}
