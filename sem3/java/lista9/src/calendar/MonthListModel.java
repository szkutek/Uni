package calendar;

import javax.swing.*;
import java.util.GregorianCalendar;

public class MonthListModel extends AbstractListModel {
    private static final int[] daysPerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] dayNames = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private static final String[] monthNames = {"", "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

//    private int year;
//    private int month;

    private String[] monthDaysList;

    public MonthListModel(int year, int month) {
//        this.year = year;
//        this.month = month;

        setUpMonth(year, month);
    }

    @Override
    public int getSize() {
        return monthDaysList == null ? 0 : monthDaysList.length;
    }

    @Override
    public Object getElementAt(int index) {
        return monthDaysList[index];
    }

    public static String getMonthName(int month) {
        return monthNames[month];
    }

    public void setUpMonth(int year, int month) {
        if (year == 1582 && month == 10) {
            october1582();
        } else {
            GregorianCalendar gregCal = new GregorianCalendar();
            int days = daysPerMonth[month];

            if (month == 2 && gregCal.isLeapYear(year)) {
                days++;
            }
            monthDaysList = new String[days];

            for (int i = 1; i < monthDaysList.length + 1; i++) {
                gregCal.set(year, month - 1, i);
                int day = gregCal.get(GregorianCalendar.DAY_OF_WEEK);
                monthDaysList[i - 1] = i + " " + dayNames[day];
            }
        }
        this.fireContentsChanged(this, 0, getSize() - 1);
    }

    private void october1582() {
        GregorianCalendar gregCal = new GregorianCalendar();

        monthDaysList = new String[21];

        int i = 0;
        for (int d = 1; d <= 4; d++, i++) {
            gregCal.set(1582, 9, d);
            int day = gregCal.get(GregorianCalendar.DAY_OF_WEEK);
            monthDaysList[i] = d + " " + dayNames[day];
        }
        for (int d = 15; d <= 31; d++, i++) {
            gregCal.set(1582, 9, d);
            int day = gregCal.get(GregorianCalendar.DAY_OF_WEEK);
            monthDaysList[i] = d + " " + dayNames[day];
        }
    }

}
