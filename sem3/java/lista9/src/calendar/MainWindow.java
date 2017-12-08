package calendar;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        this.setTitle("Calendar Application");
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        ThreeMonthView threeMonthView = new ThreeMonthView(2017, 11);
        this.add(threeMonthView, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
