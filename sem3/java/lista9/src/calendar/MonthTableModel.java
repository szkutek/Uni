package calendar;

import javax.swing.table.AbstractTableModel;
import java.util.GregorianCalendar;

public class MonthTableModel extends AbstractTableModel {

    private static final int[] daysPerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private static final String[] monthNames = {"", "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    private int rowCount = 7;
    private int columnCount;
    String[][] monthTableView;

    public MonthTableModel(int year, int month) {
        monthTableView = new String[rowCount][7];
        columnCount = dayNames.length;
        for (int i = 0; i < columnCount; i++) {
            monthTableView[0][i] = dayNames[i];
        }
        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                monthTableView[i][j] = " ";
            }
        }
        GregorianCalendar gregCal = new GregorianCalendar();
        gregCal.set(year, month, 1);
        int startCol = gregCal.get(GregorianCalendar.DAY_OF_WEEK) - 1; // TODO fix starting column?

        int i = 1;
        for (int d = 1; d < daysPerMonth[month]; d++) {
            monthTableView[i][(startCol + d) % 7] = Integer.toString(d);
            if ((startCol + d) % 7 == 6) {
                i++;
            }
        }
//        for d in range(1,maxdays+1):
//...     print("({}, {}): {}".format(j, (i+d)%7, d))
//...     if (i+d)%7 == 6:
//...             j+=1


    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return monthTableView[rowIndex][columnIndex];
    }

    public String getMonthName(int month) {
        return monthNames[month];
    }
}
