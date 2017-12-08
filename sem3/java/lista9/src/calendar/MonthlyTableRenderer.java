package calendar;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MonthlyTableRenderer extends JLabel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String napis = value.toString();
        setText(napis);
        if (column == 6) {
            setForeground(Color.RED);
        } else {
            setForeground(Color.BLACK);
        }
        return this;
    }
}
