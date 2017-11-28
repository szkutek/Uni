package grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

class ColorChooserPanel extends JPanel {
    /***/
    private Drawable drawing;
    /**
     * Panel z przyciskami do wyboru koloru
     */
    private JPanel jButtonPanel;

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
     * Konstruktor tworzy panel z przyciskami do wyboru koloru
     */
    ColorChooserPanel(Drawable drawing) {
        this.drawing = drawing;

        JLabel colorLabel = new JLabel("Choose color:");
        initButtonGroup();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(colorLabel);
        this.add(jButtonPanel);
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
                    drawing.setColor((Color) field.get(null));
                } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
//                drawing.requestFocus();
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
