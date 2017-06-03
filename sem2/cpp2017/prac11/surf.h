//
// Created by szkutek on 02.06.17.
//

#ifndef PRAC11_SURF_H
#define PRAC11_SURF_H


#include <ostream>

//TODO destructors
//TODO jakis blad w skladni? autoformatowanie zle dziala
struct surf
{
    virtual double area( ) const = 0;
    virtual double circumference( ) const = 0;
    virtual surf* clone( ) const & = 0;
    virtual surf* clone( ) && = 0;
    virtual void print( std::ostream& ) const = 0;
    virtual ~surf( ) {}
};
struct rectangle : public surf
{
    double x1, y1;
    double x2, y2;

    rectangle(double x1, double y1, double x2, double y2);

    double area( ) const override;
    double circumference( ) const override;
    rectangle* clone( ) const & override;
    rectangle* clone( ) && override;
    void print( std::ostream& ) const override;

    ~rectangle();
};
struct triangle : public surf
{
    double x1, y1; // Positions of corners.
    double x2, y2;
    double x3, y3;

    triangle(double x1, double y1, double x2, double y2, double x3, double y3);

    double area( ) const override;
    double circumference( ) const override;
    triangle* clone( ) const & override;
    triangle* clone( ) && override;
    void print( std::ostream& ) const override;

    ~triangle();
};
struct circle : public surf
{
    double x; // Position of center.
    double y;
    double radius;

    circle(double x, double y, double radius);

    double area( ) const override;
    double circumference( ) const override;
    circle* clone( ) const & override;
    circle* clone( ) && override;
    void print( std::ostream& ) const override;

    ~circle();
};

std::ostream &operator<<(std::ostream &stream, const surf &s);


#endif //PRAC11_SURF_H
