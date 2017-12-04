package grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ColorPanel extends JPanel {
    private JButton currentColorIndicator;
    /**
     * Panel z przyciskami do wyboru koloru
     */
    private JPanel buttonPanel;

    /**
     * Kolor wybrany z listy kolorów
     */
    private Color currentColor;
    private JButton advancedChooser;

    /**
     * Lista nazw kolorów
     */
    private static final Map<String, Color> colorNames = new HashMap<>();

    static {
        colorNames.put("Black", Color.BLACK);
        colorNames.put("Red", Color.RED);
        colorNames.put("Green", Color.GREEN);
        colorNames.put("Blue", Color.BLUE);
        colorNames.put("White", Color.WHITE);
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
            Color chosenColor = JColorChooser.showDialog(null, "Choose color",
                    initialBackground);
            if (chosenColor != null) {
                currentColor = chosenColor;
                currentColorIndicator.setBackground(chosenColor);
            }
        });

        setDefaultBrushColor(Color.BLACK);

        buttonPanel.add(advancedChooser);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Current color:"));
        this.add(currentColorIndicator);
        this.add(new JLabel("Choose color:"));

        this.add(new JScrollPane(buttonPanel));
    }

    /**
     * set default brush color as color
     *
     * @param color default brush color
     */
    private void setDefaultBrushColor(Color color) {
        currentColor = color;
        currentColorIndicator.setBackground(color);
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
            currentColor = colorNames.get(chosenColorName);
            currentColorIndicator.setBackground(currentColor);
        };

        for (String s : colorNames.keySet()) {
            JButton b = new JButton(s);
            b.addActionListener(buttonListener);

            buttonPanel.add(b);

            jButtons.add(b);
        }
    }

    public Color getCurrentColor() {
        return currentColor;
    }

}