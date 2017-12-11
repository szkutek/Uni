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
        String yearTabName = String.valueOf(year);
        String monthTabName = String.valueOf(month);

        ThreeMonthView threeMonthView = new ThreeMonthView(year, month + 1);
        YearPanel yearPanel = new YearPanel(year);

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab(yearTabName, yearPanel);
        tabbedPane.addTab(monthTabName, threeMonthView);

        this.add(tabbedPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void setYearTabName(String yearTabName) {
        tabbedPane.setTitleAt(0, yearTabName);
    }

    public static void setMonthTabName(String monthTabName) {
        tabbedPane.setTitleAt(1, monthTabName);
    }
}
