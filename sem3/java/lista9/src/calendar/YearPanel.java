package calendar;

import javax.swing.*;
import java.awt.*;

class YearPanel extends JPanel {
    private JPanel calendar;
    private MonthlyTableView[] c;

    public YearPanel(int year) {
        this.setLayout(new BorderLayout());
        calendar = new JPanel();
        calendar.setLayout(new GridLayout(3, 4));
        GridBagConstraints gbc = new GridBagConstraints();

        c = new MonthlyTableView[12];
        for (int i = 0, month = 1; i < 12; i++, month++) {
            gbc.gridx = i % 4;
            gbc.gridy = i % 3;
            c[i] = new MonthlyTableView(year, month);
            calendar.add(c[i], gbc);
        }
        this.add(calendar, BorderLayout.CENTER);
    }
}
