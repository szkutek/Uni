package calendar;

import javax.swing.*;
import java.awt.*;

public class MonthView extends JPanel {
    private MonthModel monthModel;

    private JLabel monthName;
    private JList monthDaysList;

    public MonthView(int year, int month) {
        monthModel = new MonthModel(year, month);
        monthName = new JLabel(monthModel.getMonthName(month));
        monthDaysList = new JList(monthModel);

        //        TODO set renderers

        this.setLayout(new BorderLayout());
        this.add(monthName, BorderLayout.NORTH);
        this.add(monthDaysList, BorderLayout.CENTER);
    }

}
