//
// Created by szkutek on 02.06.17.
//

#include "surf.h"
#include <cmath>


double distance(double x1, double y1, double x2, double y2) {
    return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
}


rectangle::rectangle(double x1, double y1, double x2, double y2)
        : x1(x1), y1(y1), x2(x2), y2(y2) {}

double rectangle::area() const {
    return fabs((x1 - x2) * (y1 - y2));
}

double rectangle::circumference() const {
    return fabs(x1 - x2) + fabs(y1 - y2);
}

rectangle *rectangle::clone() const &{
    return new rectangle(*this);
//    return new rectangle{*this};
}

rectangle *rectangle::clone() &&{
    return new rectangle(std::move(*this));
}

void rectangle::print(std::ostream &out) const {
    out << "(" << x1 << ", " << y2 << ")" << "------";
    out << "(" << x2 << ", " << y2 << ")" << "\n";
    out << "(" << x1 << ", " << y1 << ")" << "------";
    out << "(" << x2 << ", " << y1 << ")" << "\n";
}

rectangle::~rectangle() {

}


triangle::triangle(double x1, double y1, double x2, double y2, double x3, double y3)
        : x1(x1), y1(y1), x2(x2), y2(y2), x3(x3), y3(y3) {}

double triangle::area() const {
    return 0.5 * fabs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1));
}

double triangle::circumference() const {
    return distance(x1, y1, x2, y2) + distance(x1, y1, x3, y3) + distance(x2, y2, x3, y3);
}

void triangle::print(std::ostream &out) const {
    out << "(x1, y1) = (" << x1 << ", " << y1 << ")" << "\n";
    out << "(x2, y2) = (" << x2 << ", " << y2 << ")" << "\n";
    out << "(x3, y3) = (" << x3 << ", " << y3 << ")" << "\n";
}

triangle *triangle::clone() const &{
    return new triangle(*this);
}

triangle *triangle::clone() &&{
    return new triangle(std::move(*this));
}

triangle::~triangle() {

}


double circle::area() const {
    return M_PI * radius * radius;
}

double circle::circumference() const {
    return 2 * M_PI * radius;
}

circle *circle::clone() const &{
    return new circle(*this);
}

circle *circle::clone() &&{
    return new circle(std::move(*this));
}

circle::circle(double x, double y, double radius)
        : x(x), y(y), radius(radius) {}

void circle::print(std::ostream &out) const {
    out << "Center (x,y) = " << x << ", " << y << ")" << "\n";
    out << "Radius R = " << radius << "\n";
}

circle::~circle() {

}


std::ostream &operator<<(std::ostream &stream, const surf &s) {
    s.print(stream);
    return stream;
}
