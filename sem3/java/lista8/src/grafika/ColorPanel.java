package grafika;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

    private JList jList;
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
        currentColorIndicator.setEnabled(false);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        initBasicColorButtonsJList();
        advancedChooser = new JButton("More ...");

        advancedChooser.addActionListener(e -> {
            Color initialBackground = currentColorIndicator.getBackground();
            Color chosenColor = JColorChooser.showDialog(null, "Choose color",
                    initialBackground);
            if (chosenColor != null) {
                currentColor = chosenColor;
                currentColorIndicator.setBackground(chosenColor);
            }
        });

        setDefaultBrushColor();

        buttonPanel.add(advancedChooser);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel("Current color:"));
        this.add(currentColorIndicator);
        this.add(new JLabel("Choose color:"));

        this.add(buttonPanel);
    }

    /**
     * set default brush color as color
     */
    private void setDefaultBrushColor() {
        jList.setSelectedIndex(0);
        String chosenColorName = (String) jList.getSelectedValue();
        currentColor = colorNames.get(chosenColorName);
        currentColorIndicator.setBackground(currentColor);
    }

    /**
     * Metoda pomocnicza tworząca listę do wyboru podstawowych kolorów
     */
    private void initBasicColorButtonsJList() {
        jList = new JList(colorNames.keySet().toArray());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String chosenColorName = (String) jList.getSelectedValue();
                    currentColor = colorNames.get(chosenColorName);
                    currentColorIndicator.setBackground(currentColor);
                }
            }
        });

        buttonPanel.add(new JScrollPane(jList));
    }

    /**
     * Metoda pomocnicza. Tworzy przyciski (JButton)
     */
    private void initBasicColorButtons() {
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