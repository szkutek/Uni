package calendar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MonthTableView extends JPanel {
    private MonthListModel monthListModel;

    private JLabel monthName;
    private JList monthDaysList;

    public MonthTableView(int year, int month) {
        monthListModel = new MonthListModel(year, month);
        monthName = new JLabel(monthListModel.getMonthName(month));
        monthDaysList = new JList(monthListModel);

        monthDaysList.setCellRenderer(new MonthListRenderer());
        monthDaysList.setBorder(new LineBorder(Color.BLACK, 1));

        this.setLayout(new BorderLayout());
        this.add(monthName, BorderLayout.NORTH);
        this.add(monthDaysList, BorderLayout.CENTER);
    }

}
