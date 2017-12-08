package calendar;

import javax.swing.*;

public class MonthPanel extends JPanel {
    private MonthListModel monthListModel;

    private int year;
    private int month;

    private ThreeMonthView months;

    public MonthPanel(int year, int month) {
        this.year = year;
        this.month = month;


    }
}
