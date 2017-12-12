package calendar;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;

public class MainWindow extends JFrame {
    private static JTabbedPane tabbedPane;
    private JToolBar toolBar;
    private JSpinner spinner;

    private int year;
    private int month;

    private MonthPanel monthPanel;
    private YearPanel yearPanel;

    public MainWindow() {
        this.setTitle("Calendar Application");
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(900, 600);

        GregorianCalendar gregCal = new GregorianCalendar();

        year = gregCal.get(GregorianCalendar.YEAR);
        month = gregCal.get(GregorianCalendar.MONTH) + 1;

        monthPanel = new MonthPanel(year, month);
        yearPanel = new YearPanel(year, monthPanel);

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("", yearPanel);
        tabbedPane.addTab("", monthPanel);
        setYearTabName(year);
        setMonthTabName(year, month);

        initToolBar();

        this.add(tabbedPane, BorderLayout.CENTER);
        this.add(toolBar, BorderLayout.SOUTH);
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

    private void initToolBar() {
        toolBar = new JToolBar();

        spinner = new JSpinner(new SpinnerNumberModel(year, 0, 2500, 1));
        spinner.addChangeListener((ChangeEvent e) -> {
            int val = (int) spinner.getValue();
            if (val > 0 && val < 2500) {
                year = val;
                yearPanel.setYear(val);
                spinner.setValue(val);
                MainWindow.setYearTabName(val);
            }
        });
        JButton prevYearButton = new JButton("Previous year");
        prevYearButton.addActionListener((ActionEvent e) -> {
            year--;
            yearPanel.setYear(year);
            spinner.setValue(year);
            MainWindow.setYearTabName(year);
        });
        JButton nextYearButton = new JButton("Next year");
        nextYearButton.addActionListener((ActionEvent e) -> {
            year++;
            yearPanel.setYear(year);
            spinner.setValue(year);
            MainWindow.setYearTabName(year);
        });

        JButton prevMonthButton = new JButton("Previous month");
        prevMonthButton.addActionListener((ActionEvent e) -> {
            month = monthPanel.getMonth();
            if (month == 1) {
                year--;
                month = 12;

            } else {
                month--;
            }
            monthPanel.setYearAndMonth(year, month);
            yearPanel.setYear(year);
        });
        JButton nextMonthButton = new JButton("Next month");
        nextMonthButton.addActionListener((ActionEvent e) -> {
            month = monthPanel.getMonth();
            if (month == 12) {
                year++;
                month = 1;

            } else {
                month++;
            }
            monthPanel.setYearAndMonth(year, month);
            yearPanel.setYear(year);
        });

        toolBar.add(prevYearButton);
        toolBar.add(prevMonthButton);
        toolBar.add(spinner);
        toolBar.add(nextMonthButton);
        toolBar.add(nextYearButton);

    }
}
