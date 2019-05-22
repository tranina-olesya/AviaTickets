package ru.vsu.aviatickets.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTestHelper {
    public static Date getDateFromString(String stringDate) {
        String pattern = "HH:mm dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Long getLongFromDate(Date date) {
        if (date == null)
            return null;
        return date.getTime() / 1000L;
    }
}
