package calendar;

import javax.swing.*;
import java.awt.*;

public class MonthListRenderer extends JLabel implements ListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String napis = value.toString();
        setText(napis);
        if (napis.contains("Sunday")) {
            setForeground(Color.RED);
        } else {
            setForeground(Color.BLACK);
        }
        return this;
    }
}
