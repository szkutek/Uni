package grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Główne okno aplikacji
 */
public class MainWindow extends JFrame {
    /**
     * Szerokość głównego okna aplikacji
     */
    private static final int windowHeight = 400;
    /**
     * Wysokość głównego okna aplikacji
     */
    private static final int windowWidth = 800;

    /**
     * Panel do wyboru kolorów
     */
    private ColorChooserPanel colorPanel;

    /**
     * Obszar, na którym można rysować
     */
    private DrawingCanvas drawingCanvas;

    /**
     * Tworzy główne okno aplikacji
     */
    public MainWindow() {
        this.setTitle("Almost Paint Application");

        this.setLocation(200, 200);
        this.setSize(windowWidth, windowHeight);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        drawingCanvas = new DrawingCanvas();
        colorPanel = new ColorChooserPanel(drawingCanvas);

        this.add(colorPanel, BorderLayout.WEST);
        this.add(drawingCanvas, BorderLayout.CENTER);


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainWindow.this.dispose();
                System.exit(0);
            }
        });

        this.setVisible(true);
    }


}