package grafika;

import javax.swing.*;
import java.awt.*;

class MouseCoordinatesPanel extends JPanel {
    private JLabel coordLabel;

    MouseCoordinatesPanel() {
        coordLabel = new JLabel("(0, 0)");

        this.add(coordLabel);
    }

    void setCoord(Point coord) {
        this.coordLabel.setText("(" + coord.x + ", " + coord.y + ")");
    }
}
