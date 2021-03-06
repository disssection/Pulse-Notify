package us.chary.pulsenotify;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarUtil {
    public static ArrayList<String> nameOfEvent = new ArrayList<String>();
    public static ArrayList<Long> startDates = new ArrayList<>();
    public static ArrayList<Long> endDates = new ArrayList<>();
    public static ArrayList<String> descriptions = new ArrayList<String>();

    public static ArrayList<String> readCalendarEvent(Context context) {
        String[] vec = new String[] { "dtend"};
        String selectionClause = "(dtend >= ?)";
        String[] selectionsArgs = new String[]{"" + System.currentTimeMillis()};

        Cursor cursor = context.getContentResolver()
                .query(
                        Uri.parse("content://com.android.calendar/events"),
                        vec, selectionClause,
                        selectionsArgs, null);
        cursor.moveToFirst();
        // fetching calendars name
        String CNames[] = new String[cursor.getCount()];

        // fetching calendars id
        nameOfEvent.clear();
        startDates.clear();
        endDates.clear();
        descriptions.clear();
        for (int i = 0; i < CNames.length; i++) {
            try{
                startDates.add(Long.valueOf(cursor.getString(0)));
            }catch (NumberFormatException e){
            }
            cursor.moveToNext();
        }
        return nameOfEvent;
    }

    public static String getDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy hh:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}