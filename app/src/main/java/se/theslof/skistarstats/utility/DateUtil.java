package se.theslof.skistarstats.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by theslof on 2018-02-09.
 */

public class DateUtil{
    private static final String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";

    private DateUtil(){}

    public static Date parseDate(String date){
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Date pDate;

        try {
            pDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return pDate;
    }

    public static boolean isLater(String date1, String date2) {
        Date latestDate = parseDate(date1);
        Date newDate = parseDate(date2);

        if(latestDate == null || newDate == null)
            return false;

        return newDate.after(latestDate);
    }

    public static String fDate(String date){
        String[] dateArray = date.split("T");
        return dateArray[0] + " " + dateArray[1].substring(0,5);
    }
}
