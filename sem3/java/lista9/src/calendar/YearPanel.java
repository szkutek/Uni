package calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class YearPanel extends JPanel {
    private JPanel calendar;
    private MonthTableView[] c;

    private JButton prevYearButton;
    private JButton nextYearButton;

    private int year;

    public YearPanel(int year) {
        this.year = year;
        calendar = new JPanel();
        calendar.setLayout(new GridLayout(3, 4));

        c = new MonthTableView[12];

        setUpYearPanel(this.year);

        prevYearButton = new JButton("Previous year");
        prevYearButton.addActionListener((ActionEvent e) -> {
            this.year--;
            // TODO JSpinner setValue
            MainWindow.setYearTabName("" + this.year);
            for (int i = 0; i < 12; i++) {
                c[i].setUpMonth(this.year, i + 1);
            }
        });
        nextYearButton = new JButton("Next year");
        nextYearButton.addActionListener((ActionEvent e) -> {
            this.year++;
            MainWindow.setYearTabName("" + this.year);
            for (int i = 0; i < 12; i++) {
                c[i].setUpMonth(this.year, i + 1);
            }
        });

        JToolBar toolBar = new JToolBar();
//        toolBar.setLayout(new BorderLayout());

        toolBar.add(prevYearButton);
        toolBar.add(nextYearButton);


        this.setLayout(new BorderLayout());
        this.add(calendar, BorderLayout.CENTER);
        this.add(toolBar, BorderLayout.SOUTH);
    }

    private void setUpYearPanel(int year) {
        calendar = new JPanel();
        calendar.setLayout(new GridLayout(3, 4));

        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0, month = 1; i < 12; i++, month++) {
            gbc.gridx = i % 4;
            gbc.gridy = i % 3;
            c[i] = new MonthTableView(year, month);
            calendar.add(c[i], gbc);
        }
    }
}
