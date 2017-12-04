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
    private int scale;

    private boolean cursorInCanvas = false;

    @SuppressWarnings("FieldCanBeLocal")
    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (cursorInCanvas) {
                Color currentColor = colorPanel.getCurrentColor();
                image.setRGB(e.getX() / scale, e.getY() / scale, currentColor.getRGB());
            }
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getX() < image.getWidth() * scale && e.getY() < image.getHeight() * scale) {
                cursorInCanvas = true;
                mouseCoordinatesPanel.setCoord(e.getPoint());
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseCoordinatesPanel.setCoord(e.getPoint());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getX() > image.getWidth() * scale || e.getY() > image.getHeight() * scale) {
                cursorInCanvas = false;
                mouseCoordinatesPanel.setCoord(new Point(0, 0));
                repaint();
            }
        }
    };

    DrawingPanel(BufferedImage image, MouseCoordinatesPanel mouseCoordinatesPanel, ColorPanel colorPanel) {
        super();
        this.image = image;
        this.mouseCoordinatesPanel = mouseCoordinatesPanel;
        this.colorPanel = colorPanel;
        this.scale = 1;

        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int w = image.getWidth() * this.scale;
        int h = image.getHeight() * this.scale;

        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, w, h, this);
        this.repaint();
    }

    int getScale() {
        return scale;
    }

    void setScale(int scale) {
        this.scale = scale;
    }
}
