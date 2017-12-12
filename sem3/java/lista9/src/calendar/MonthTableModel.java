package calendar;

import javax.swing.table.AbstractTableModel;
import java.util.GregorianCalendar;

public class MonthTableModel extends AbstractTableModel {

    private static final int[] daysPerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] dayNames = {"", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private static final String[] monthNames = {"", "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    private int rowCount = 7;
    private int columnCount;
    String[][] monthTableView;

    public MonthTableModel(int year, int month) {
        monthTableView = new String[rowCount][7];
        columnCount = dayNames.length - 1;
        for (int i = 0; i < columnCount; i++) {
            monthTableView[0][i] = dayNames[i + 1];
        }
        this.setUpMonth(year, month);

    }

    public void setUpMonth(int year, int month) {
        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                monthTableView[i][j] = " ";
            }
        }

        if (year == 1582 && month == 10) {
            october1582();
        } else {
            GregorianCalendar gregCal = new GregorianCalendar();
            gregCal.set(year, month - 1, 1);

            int days = daysPerMonth[month];
            if (month == 2 && gregCal.isLeapYear(year)) {
                days++;
            }
            int startingDay = gregCal.get(GregorianCalendar.DAY_OF_WEEK) - 1;
            if (startingDay == 0) {  // Sunday
                startingDay = 7;
            }

            int startCol = startingDay - 1;
            int i = 1;
            for (int d = 0; d < days; d++) {
                monthTableView[i][(startCol + d) % 7] = Integer.toString(d + 1);
                if ((startCol + d) % 7 == 6) {
                    i++;
                }
            }
        }
        this.fireTableDataChanged();
    }

    private void october1582() {
        GregorianCalendar gregCal = new GregorianCalendar();
        gregCal.set(1582, 9, 1);

        int startingDay = gregCal.get(GregorianCalendar.DAY_OF_WEEK) - 1;
        if (startingDay == 0) {  // Sunday
            startingDay = 7;
        }

        int startCol = startingDay - 1;
        int i = 1;
        for (int d = 0; d < 4; d++) {
            monthTableView[i][(startCol + d) % 7] = Integer.toString(d + 1);
            if ((startCol + d) % 7 == 6) {
                i++;
            }
        }
        for (int d = 15; d <= 31; d++) {
            monthTableView[i][(startCol + d + 3) % 7] = Integer.toString(d);
            if ((startCol + d + 3) % 7 == 6) {
                i++;
            }
        }
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
