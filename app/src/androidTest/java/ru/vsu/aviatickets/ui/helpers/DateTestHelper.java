package ru.vsu.aviatickets.ui.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTestHelper {

    public static Date getYesterdayDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    public static String getDateStringWithDots(Date date) {
        if (date == null)
            return "";
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}
