package grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

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
    private JPanel colorPanel;

    /**
     * Panel z przyciskami do wyboru koloru
     */
    private JPanel jButtonPanel;

    /**
     * Obszar, na którym można rysować
     */
    private DrawingCanvas drawingCanvas;

    /**
     * Kolor wybrany z listy kolorów
     */
    private String chosenColor;

    /**
     * Lista nazw kolorów
     */
    private static final ArrayList<String> colorNames;

    static {
        colorNames = new ArrayList<>(Arrays.asList("Black", "Blue", "Red", "Yellow", "Green", "Pink"));
    }


    /**
     * Tworzy główne okno aplikacji
     */
    public MainWindow() {
        this.setTitle("Almost Paint Application");

        this.setLocation(200, 200);
        this.setSize(windowWidth, windowHeight);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        initColorPanel();
        drawingCanvas = new DrawingCanvas();

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

    /**
     * Tworzy panel z przyciskami do wyboru koloru
     */
    private void initColorPanel() {
        colorPanel = new JPanel();

        JLabel colorLabel = new JLabel("Choose color:");
        initButtonGroup();

        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
        colorPanel.add(colorLabel);
        colorPanel.add(jButtonPanel);
    }

    /**
     * Metoda pomocnicza. Tworzy przyciski radiowe (JRadioButton) i dodaje je do grupy przycisków
     */
    private void initButtonGroup() {
        jButtonPanel = new JPanel();
        jButtonPanel.setLayout(new BoxLayout(jButtonPanel, BoxLayout.Y_AXIS));

        ArrayList<JRadioButton> jButtons = new ArrayList<>();
        ButtonGroup jButtonGroup = new ButtonGroup();

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton b = (JRadioButton) e.getSource();
                chosenColor = b.getText();
                try {
                    Field field = Class.forName("java.awt.Color").getField(chosenColor.toLowerCase());
                    drawingCanvas.setCurrentColor((Color) field.get(null));
                } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
        };

        for (String s : colorNames) {
            JRadioButton b = new JRadioButton(s);
            b.addActionListener(buttonListener);

            jButtonGroup.add(b);
            jButtonPanel.add(b, BorderLayout.CENTER);

            jButtons.add(b);
        }
        jButtons.get(0).setSelected(true);
    }

}