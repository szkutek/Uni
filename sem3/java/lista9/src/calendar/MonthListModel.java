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
//        return monthDaysList[index] + " " + monthNames[month];
    }

    public static String getMonthName(int month) {
        return monthNames[month];
    }

    public void setUpMonth(int year, int month) {
        monthDaysList = new String[daysPerMonth[month]];

        GregorianCalendar gregCal = new GregorianCalendar();
        for (int i = 1; i < monthDaysList.length + 1; i++) {
            gregCal.set(year, month - 1, i);
            int day = gregCal.get(GregorianCalendar.DAY_OF_WEEK);
            monthDaysList[i - 1] = i + " " + dayNames[day];
        }
        this.fireContentsChanged(this, 0, getSize() - 1);
    }

}
