package grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

class ColorPanel extends JPanel {
    private JButton currentColorIndicator;
    /**
     * Panel z przyciskami do wyboru koloru
     */
    private JPanel buttonPanel;

    /**
     * Kolor wybrany z listy kolorów
     */
    private Color chosenColor;
    private JButton advancedChooser;

    /**
     * Lista nazw kolorów
     */
    private static final ArrayList<String> colorNames;

    static {
        colorNames = new ArrayList<>(Arrays.asList("Black", "Red", "Green", "Blue", "White"));
    }


    /**
     * Konstruktor tworzy panel z przyciskami do wyboru koloru
     */
    ColorPanel() {
        currentColorIndicator = new JButton("Current color");
        currentColorIndicator.setFocusable(false);

        initBasicColorButtons();
        this.advancedChooser = new JButton("More ...");

        this.advancedChooser.addActionListener(e -> {
            Color initialBackground = currentColorIndicator.getBackground();
            Color background = JColorChooser.showDialog(null, "Choose color",
                    initialBackground);
            if (background != null) {
                currentColorIndicator.setBackground(background);
            }
        });
        buttonPanel.add(advancedChooser);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Current color:"));
        this.add(currentColorIndicator);
        this.add(new JLabel("Choose color:"));

        this.add(new JScrollPane(buttonPanel));


    }


    /**
     * Metoda pomocnicza. Tworzy przyciski (JButton)
     */
    private void initBasicColorButtons() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        ArrayList<JButton> jButtons = new ArrayList<>();

        ActionListener buttonListener = e -> {
            JButton b = (JButton) e.getSource();
            String chosenColorName = b.getText();
            try {
                Field field = Class.forName("java.awt.Color").getField(chosenColorName.toLowerCase());
                chosenColor = (Color) field.get(null);
            } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e1) {
                e1.printStackTrace();
            }
            currentColorIndicator.setBackground(chosenColor);
        };

        for (String s : colorNames) {
            JButton b = new JButton(s);
            b.addActionListener(buttonListener);

            buttonPanel.add(b);

            jButtons.add(b);
        }
        jButtons.get(0).setSelected(true);
    }

    public Color getChosenColor() {
        return chosenColor;
    }

    public void setChosenColor(Color chosenColor) {
        this.chosenColor = chosenColor;
    }
}