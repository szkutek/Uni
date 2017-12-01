package grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

class DrawingPanel extends JPanel {
    private MouseCoordinatesPanel mouseCoordinatesPanel;
    private BufferedImage image;
    private Color currentColor;
    private boolean cursorInCanvas = false;

    @SuppressWarnings("FieldCanBeLocal")
    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (cursorInCanvas) {
                image.setRGB(e.getX(), e.getY(), Color.RED.getRGB());
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

    DrawingPanel(BufferedImage image, MouseCoordinatesPanel mouseCoordinatesPanel) {
        super();
        this.image = image;
        this.mouseCoordinatesPanel = mouseCoordinatesPanel;
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
