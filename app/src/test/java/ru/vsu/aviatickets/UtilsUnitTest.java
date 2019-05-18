package ru.vsu.aviatickets;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import ru.vsu.aviatickets.ui.utils.DateConvert;
import ru.vsu.aviatickets.ui.utils.StringUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UtilsUnitTest {

    @Test
    public void date_shouldConvertFromString() {
        Date dateFromStringWithSlashes = DateConvert.getDateFromStringWithSlashes("15/10/2018");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date date = calendar.getTime();
        assertEquals(dateFromStringWithSlashes, date);
    }

    @Test
    public void date_shouldNotConvertFromString() {
        Date dateFromStringWithSlashes = DateConvert.getDateFromStringWithSlashes("15.10.2018");
        assertNull(dateFromStringWithSlashes);
    }

    @Test
    public void date_shouldCovertDuration() {
        int duration = 75;
        String durationString = DateConvert.getDurationString(duration);
        assertEquals(durationString, "01:15");
    }

    @Test
    public void date_shouldNotCovertDuration() {
        int duration = -19;
        String durationString = DateConvert.getDurationString(duration);
        assertNull(durationString);
    }

    @Test
    public void date_shouldConvertDateToString() {
        String expected = "28/12/2015";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 28);
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date date = calendar.getTime();
        String actual = DateConvert.getDateWithSlashes(date);
        assertEquals(expected, actual);
    }

    @Test
    public void date_shouldConvertAnotherDateToString() {
        String expected = "08/01/2015";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 8);
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date date = calendar.getTime();
        String actual = DateConvert.getDateWithSlashes(date);
        assertEquals(expected, actual);
    }

    @Test
    public void price_shouldFormatPrice() {
        String expected = "112 631";
        Double price = 112630.9;
        String actual = StringUtils.formatPrice(price);
        assertEquals(expected, actual);
    }

    @Test
    public void price_shouldFormatAnotherPrice() {
        String expected = "5 671";
        Double price = 5671.2;
        String actual = StringUtils.formatPrice(price);
        assertEquals(expected, actual);
    }
}
