package grafika;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
     * JScrollPane w którym znajduje się DrawingPanel
     */
    private JScrollPane scrollPaneDrawing;

    private final JFileChooser fileChooser;

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

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPG, PNG and GIF images", "jpg", "JPEG", "png", "gif"));

        image = createRandomImage(300, 300);

        JToolBar toolBar = new JToolBar();
        initToolBar(toolBar);


        colorPanel = new ColorPanel();
        mouseCoordinatesPanel = new MouseCoordinatesPanel();
        drawingPanel = new DrawingPanel(image, mouseCoordinatesPanel, colorPanel);
        drawingPanel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight())); // necessary for scrolling

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.add(colorPanel);

        scrollPaneDrawing = new JScrollPane(drawingPanel);
        splitPane.add(scrollPaneDrawing);

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
        JButton buttonOpenFile = createButton("Open", "o", "Open file", "open");
        buttonOpenFile.addActionListener((ActionEvent e) -> {
            int returnVal = fileChooser.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                BufferedImage origImage;
                try {
//                    TODO fix reading image (now it doesnt update width and height)
                    origImage = ImageIO.read(selectedFile);
                    drawingPanel.setImage(origImage);
                    drawingPanel.setScale(1);

                    drawingPanel.revalidate();
                    drawingPanel.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        toolBar.add(buttonOpenFile);

        JButton buttonPlus = createButton("Plus", "p", "Zoom in", "zoomIn");
        buttonPlus.addActionListener((ActionEvent e) -> {
            int scale = drawingPanel.getScale();
            if (scale < maxScaling) {
                scale += 1;
                drawingPanel.setScale(scale);
                drawingPanel.setPreferredSize(new Dimension(image.getWidth() * scale, image.getHeight() * scale));
            }
        });
        toolBar.add(buttonPlus);

        JButton buttonMinus = createButton("Minus", "m", "Zoom out", "zoomOut");
        buttonMinus.addActionListener((ActionEvent e) -> {
            int scale = drawingPanel.getScale();
            if (scale > minScaling) {
                scale -= 1;
                drawingPanel.setScale(scale);
                drawingPanel.setPreferredSize(new Dimension(image.getWidth() * scale, image.getHeight() * scale));
            }
        });
        toolBar.add(buttonMinus);

        JButton buttonUp = createButton("Up", "u", "Go to the top side of the image", "up");
        buttonUp.addActionListener((ActionEvent e) -> {
            scrollPaneDrawing.getVerticalScrollBar().setValue(0);
        });
        toolBar.add(buttonUp);

        JButton buttonDown = createButton("Down", "d", "Go to the bottom side of the image", "down");
        buttonDown.addActionListener((ActionEvent e) -> {
            scrollPaneDrawing.getVerticalScrollBar().setValue(scrollPaneDrawing.getVerticalScrollBar().getMaximum());
        });
        toolBar.add(buttonDown);

        JButton buttonLeft = createButton("Left", "l", "Go to the left side of the image", "left");
        buttonLeft.addActionListener((ActionEvent e) -> {
            scrollPaneDrawing.getHorizontalScrollBar().setValue(0);
        });
        toolBar.add(buttonLeft);

        JButton buttonRight = createButton("Right", "r", "Go to the right side of the image", "right");
        buttonRight.addActionListener((ActionEvent e) -> {
            scrollPaneDrawing.getHorizontalScrollBar().setValue(scrollPaneDrawing.getHorizontalScrollBar().getMaximum());
        });
        toolBar.add(buttonRight);
    }

    private JButton createButton(String name, String actionCommand, String summary, String iconName) {
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(summary);

        try {
            Image img = ImageIO.read(getClass().getResource(iconName + ".png"));
            int scaledW = 20;
            int scaledH = 20;
            button.setIcon(new ImageIcon(img.getScaledInstance(scaledW, scaledH, 1)));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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