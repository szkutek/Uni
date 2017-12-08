package calendar;

import javax.swing.*;
import java.awt.*;

public class ThreeMonthView extends JPanel {
    private int year;
    private int month;

    private JPanel prevMonth;
    private JPanel thisMonth;
    private JPanel nextMonth;

    public ThreeMonthView(int year, int month) {
        this.year = year;
        this.month = month;

        this.setLayout(new GridLayout(1, 3));

        if (month == 1) { // prev is from the year before
            prevMonth = new MonthView(year - 1, 12);
        } else {
            prevMonth = new MonthView(year, month - 1);
        }

        thisMonth = new MonthView(year, month);

        if (month == 12) {
            nextMonth = new MonthView(year + 1, 1);
        } else {
            nextMonth = new MonthView(year, month + 1);
        }



        this.add(prevMonth, 0);
        this.add(thisMonth, 1);
        this.add(nextMonth, 2);
    }
}
