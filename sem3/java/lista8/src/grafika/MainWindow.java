package grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JToolBar toolBar;

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
     * Maksymalna wartość przez którą możemy przeskalować BufferedImage
     */
    private static int maxScaling = 8;
    private static int minScaling = 1;

    /**
     * Tworzy główne okno aplikacji
     */
    public MainWindow() {
        this.setTitle("Almost Paint Application");
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(windowWidth, windowHeight);

        image = createRandomImage(100, 100);


        JToolBar toolBar = new JToolBar();
        initToolBar(toolBar);


        colorPanel = new ColorPanel();
        mouseCoordinatesPanel = new MouseCoordinatesPanel();
        drawingPanel = new DrawingPanel(image, mouseCoordinatesPanel, colorPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.add(new JScrollPane(colorPanel));
        splitPane.add(new JScrollPane(drawingPanel));

        this.add(toolBar, BorderLayout.NORTH);
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

    private void initToolBar(JToolBar toolBar) {

        JButton buttonOpenFile = createButton("Open", "o", "Open file");
        toolBar.add(buttonOpenFile);
        JButton buttonPlus = createButton("Plus", "p", "Zoom in");
        toolBar.add(buttonPlus);
        JButton buttonMinus = createButton("Minus", "m", "Zoom out");
        toolBar.add(buttonMinus);
        JButton buttonUp = createButton("Up", "u", "Go to the top of the image");
        toolBar.add(buttonUp);
        JButton buttonDown = createButton("Down", "d", "Go to the bottom of the image");
        toolBar.add(buttonDown);
        JButton buttonLeft = createButton("Left", "l", "Go to the left of the image");
        toolBar.add(buttonLeft);
        JButton buttonRight = createButton("Right", "r", "Go to the right of the image");
        toolBar.add(buttonRight);


    }

    private JButton createButton(String name, String actionCommand, String summary) {
        JButton button = new JButton(name);
        button.setActionCommand(actionCommand);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                int scale = 0;
                switch (command.charAt(0)) {
                    case 'o':
                        break;
                    case 'p':
                        scale = drawingPanel.getScale();
                        if (scale < maxScaling) {
                            drawingPanel.setScale(scale + 1);
                        }
                        break;
                    case 'm':
                        scale = drawingPanel.getScale();
                        if (scale > minScaling) {
                            drawingPanel.setScale(scale - 1);
                        }
                        break;
                    case 'u':
                        break;
                    case 'd':
                        break;
                    case 'l':
                        break;
                    case 'r':
                        break;
                }
                System.out.println(summary);
            }
        });
        return button;
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