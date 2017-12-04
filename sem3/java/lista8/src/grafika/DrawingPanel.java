package grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

class DrawingPanel extends JPanel {
    private BufferedImage image;
    private MouseCoordinatesPanel mouseCoordinatesPanel;
    private ColorPanel colorPanel;
    private double scale;

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

        this.scale = 2.0;

        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(this.image, 0, 0, this);
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int w = getWidth();
        int h = getHeight();
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        double x = (w - scale * imageWidth) / 2;
        double y = (h - scale * imageHeight) / 2;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.scale(scale, scale);
        g2.drawRenderedImage(image, at);
//        g.drawImage(this.image, 0, 0, this);

    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}
