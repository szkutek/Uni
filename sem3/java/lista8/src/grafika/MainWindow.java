package grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

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
     * Panel z paskiem narzędzi
     */
    private ToolbarPanel toolbar;

    /**
     * Panel do wyboru kolorów
     */
    private ColorPanel colorPanel;

    /**
     * Obszar, na którym można rysować
     */
    private DrawingPanel drawingPanel;

    /**
     * Panel ze współrzędnymi kursora myszy
     */
    private MouseCoordinatesPanel mouseCoordinatesPanel;

    /**
     * Obraz
     */
    private BufferedImage image;

    /**
     * Tworzy główne okno aplikacji
     */
    public MainWindow() {
        this.setTitle("Almost Paint Application");
        this.setLocation(200, 200);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(windowWidth, windowHeight);

        image = createRandomImage(200, 500);

        colorPanel = new ColorPanel();
        mouseCoordinatesPanel = new MouseCoordinatesPanel();
        drawingPanel = new DrawingPanel(image, mouseCoordinatesPanel, colorPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.add(new JScrollPane(colorPanel));
        splitPane.add(new JScrollPane(drawingPanel));

        this.add(splitPane, BorderLayout.CENTER);
        this.add(mouseCoordinatesPanel, BorderLayout.SOUTH);


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainWindow.this.dispose();
                System.exit(0);
            }
        });

        this.setVisible(true);
    }

    private BufferedImage createRandomImage(int w, int h) {
        int type = BufferedImage.TYPE_INT_ARGB;

        BufferedImage img = new BufferedImage(w, h, type);

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                img.setRGB(x, y, Color.GREEN.getRGB());
            }
        }
        return img;
    }

}