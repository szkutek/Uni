package calendar;

import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;

public class MainWindow extends JFrame {
    private static JTabbedPane tabbedPane;


    public MainWindow() {
        this.setTitle("Calendar Application");
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        GregorianCalendar gregCal = new GregorianCalendar();
        int year = gregCal.get(GregorianCalendar.YEAR);
        int month = gregCal.get(GregorianCalendar.MONTH);

        MonthPanel monthPanel = new MonthPanel(year, month + 1);
        YearPanel yearPanel = new YearPanel(year);

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("", yearPanel);
        tabbedPane.addTab("", monthPanel);
        setYearTabName(year);
        setMonthTabName(year, month + 1);

        this.add(tabbedPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void setYearTabName(int year) {
        String yearTabName = "Year " + year;
        tabbedPane.setTitleAt(0, yearTabName);
    }

    public static void setMonthTabName(int year, int month) {
        String monthTabName = MonthListModel.getMonthName(month) + " " + year;
        tabbedPane.setTitleAt(1, monthTabName);
    }
}
