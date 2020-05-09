package com.example.newsapplication;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utill {
public static final String TAG = Utill.class.getSimpleName();
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public static void getCurrentDate() {

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        //2020-05-07

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);
        Log.d(TAG, "getCurrentDate: currentDate " + formattedDate);
        //getNews(formattedDate);
    }

    public static String getDate(String yearDate){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = df.parse(yearDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("date:"+date);
        df=new SimpleDateFormat("dd-MMM-yyyy");
        String formatedDate = df.format(date);
        System.out.println("Formated Date:"+df.format(date));
        System.out.println("date.getTime"+date.getTime());
        return formatedDate;
    }

    public static long getDateInMillis(String srcDate) {
        SimpleDateFormat desiredFormat = new SimpleDateFormat("d MMMM yyyy, hh:mm aa");

        long dateInMillis = 0;
        try {
            Date date = desiredFormat.parse(srcDate);
            dateInMillis = date.getTime();
            return dateInMillis;
        } catch (ParseException e) {
            e.getMessage();

            e.printStackTrace();
        }

        return 0;
    }



}
