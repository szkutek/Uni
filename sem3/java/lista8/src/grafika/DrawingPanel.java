package grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

class DrawingPanel extends JPanel {
    private BufferedImage image;
    private MouseCoordinatesPanel mouseCoordinatesPanel;
    private ColorPanel colorPanel;

    //    private Color currentColor;
    private boolean cursorInCanvas = false;

    @SuppressWarnings("FieldCanBeLocal")
    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (cursorInCanvas) {
                if (e.getX() < image.getWidth() && e.getY() < image.getHeight()) {
                    Color currentColor = colorPanel.getCurrentColor();
                    image.setRGB(e.getX(), e.getY(), currentColor.getRGB());
                }
            }
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            cursorInCanvas = true;
            mouseCoordinatesPanel.setCoord(e.getPoint());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseCoordinatesPanel.setCoord(e.getPoint());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            cursorInCanvas = false;
            mouseCoordinatesPanel.setCoord(new Point(0, 0));
            repaint();
        }
    };

    DrawingPanel(BufferedImage image, MouseCoordinatesPanel mouseCoordinatesPanel, ColorPanel colorPanel) {
        super();
        this.image = image;
        this.mouseCoordinatesPanel = mouseCoordinatesPanel;
        this.colorPanel = colorPanel;
        this.setSize(image.getWidth(), image.getHeight());

        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, this);

    }
}
