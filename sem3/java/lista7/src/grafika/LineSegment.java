package grafika;

import java.awt.*;

class LineSegment {
    private Point p1, p2;
    private Color color;

    LineSegment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    LineSegment(Point p1, Point p2, Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
    }

    Point getP1() {
        return p1;
    }

    void setP1(Point p1) {
        this.p1 = p1;
    }

    Point getP2() {
        return p2;
    }

    void setP2(Point p2) {
        this.p2 = p2;
    }

    Color getColor() {
        return color;
    }

    void setColor(Color color) {
        this.color = color;
    }

    void updateLine(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
}
