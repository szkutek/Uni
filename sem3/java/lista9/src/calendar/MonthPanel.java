package calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MonthPanel extends JPanel {
    private int year;
    private int month;

    private MonthListView prevMonth;
    private MonthListView thisMonth;
    private MonthListView nextMonth;

    public MonthPanel(int year, int month) {
        this.year = year;
        this.month = month;

        JPanel months3 = new JPanel();
        months3.setLayout(new GridLayout(1, 3));

        if (month == 1) { // prev is from the year before
            prevMonth = new MonthListView(year - 1, 12);
        } else {
            prevMonth = new MonthListView(year, month - 1);
        }

        thisMonth = new MonthListView(year, month);

        if (month == 12) {
            nextMonth = new MonthListView(year + 1, 1);
        } else {
            nextMonth = new MonthListView(year, month + 1);
        }

        months3.add(prevMonth, 0);
        months3.add(thisMonth, 1);
        months3.add(nextMonth, 2);

        JButton prevMonthButton = new JButton("Previous month");
        prevMonthButton.addActionListener((ActionEvent e) -> {
            if (this.month == 1) {
                this.year--;
                this.month = 12;
            } else {
                this.month--;
            }
            updateMonths();
        });
        JButton nextMonthButton = new JButton("Next month");
        nextMonthButton.addActionListener((ActionEvent e) -> {
            if (this.month == 12) {
                this.year++;
                this.month = 1;
            } else {
                this.month++;
            }
            updateMonths();
        });

        JToolBar toolBar = new JToolBar();
        toolBar.add(prevMonthButton);
        toolBar.add(nextMonthButton);

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(months3), BorderLayout.CENTER);
//        this.add(toolBar, BorderLayout.SOUTH);
    }

    private void updateMonths() {
        MainWindow.setYearTabName(this.year);
        MainWindow.setMonthTabName(this.year, this.month);

        if (month == 1) { // prev is from the year before
            prevMonth.setUpMonth(year - 1, 12);
        } else {
            prevMonth.setUpMonth(year, month - 1);
        }

        thisMonth.setUpMonth(year, month);

        if (month == 12) { // next is from the next year
            nextMonth.setUpMonth(year + 1, 1);
        } else {
            nextMonth.setUpMonth(year, month + 1);
        }
    }

    void setYear(int year) {
        this.year = year;
        updateMonths();
    }

    void setMonth(int month) {
        this.month = month;
        updateMonths();
    }

    void setYearAndMonth(int year, int month) {
        this.year = year;
        this.month = month;
        updateMonths();
    }

    public int getMonth() {
        return month;
    }
}
