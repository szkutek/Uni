package grafika;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class DrawingCanvas extends Canvas implements Drawable {
    private List<LineSegment> lineSegments;

    private LineSegment currentLineSegment;

    private Color currentColor;

    private boolean cursorInCanvas = false;

    @SuppressWarnings("FieldCanBeLocal")
    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            currentLineSegment = new LineSegment(e.getPoint(), e.getPoint(), currentColor);
            lineSegments.add(currentLineSegment);
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (cursorInCanvas && currentLineSegment != null) {
                LineSegment line = currentLineSegment;
                line.updateLine(currentLineSegment.getP1(), e.getPoint());
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            currentLineSegment = null;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            cursorInCanvas = true;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (currentLineSegment != null) {
                lineSegments.remove(currentLineSegment);
                currentLineSegment = null;
            }
            cursorInCanvas = false;
            repaint();
        }
    };

    @SuppressWarnings("FieldCanBeLocal")
    private KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_F:
                    remove('f');
                    break;
                case KeyEvent.VK_L:
                    remove('l');
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    remove('a');
                    break;
            }
            repaint();
        }
    };

    DrawingCanvas() {
        super();

        setBackground(Color.WHITE);

        this.lineSegments = new ArrayList<>();

        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
        this.addKeyListener(keyAdapter);

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (LineSegment line : lineSegments) {
            if (line == currentLineSegment) {
                g2d.setPaint(Color.GRAY);
            } else {
                g2d.setPaint(line.getColor());
            }
            g2d.drawLine(line.getP1().x, line.getP1().y, line.getP2().x, line.getP2().y);
        }
    }

    private void remove(char c) {
        if (lineSegments != null && lineSegments.size() > 0) {
            switch (c) {
                case 'a':
                    lineSegments.clear();
                    break;
                case 'f':
                    lineSegments.remove(0);
                    break;
                case 'l':
                    lineSegments.remove(lineSegments.size() - 1);
                    break;
            }
        }
    }


    @Override
    public void setColor(Color color) {
        this.currentColor = color;
    }
}


