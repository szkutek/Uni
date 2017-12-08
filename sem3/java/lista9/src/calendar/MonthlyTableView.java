package calendar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class MonthlyTableView extends JPanel {
    private MonthTableModel monthTableModel;

    private JLabel monthName;
    private JTable monthDaysTable;

    public MonthlyTableView(int year, int month) {
        monthTableModel = new MonthTableModel(year, month);
        monthName = new JLabel(monthTableModel.getMonthName(month));
        monthDaysTable = new JTable(monthTableModel);

//        monthDaysTable.setDefaultRenderer(Object.class, new MonthlyTableRenderer());
        monthDaysTable.setBorder(new LineBorder(Color.BLACK, 1));

        this.setLayout(new BorderLayout());
        this.add(monthName, BorderLayout.NORTH);
        this.add(monthDaysTable, BorderLayout.CENTER);
    }

}


