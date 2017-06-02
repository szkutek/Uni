#include <iostream>
#include "surf.h"

int main() {
    rectangle r(0, 0, 1, 1);
    triangle t(0, 0, 1, 0, 0, 1);
    circle c(0, 0, 1);

    std::cout << "r:\n" << r << "\n";

    rectangle *r2 = r.clone();
    std::cout << "r2:\n" << *r2 << "\n";

//    rectangle r3 = std::move(r);
//    std::cout << "r3:\n" << r3 << "\n";


    std::cout << "r area " << r.area() << " and circumference " << r.circumference() << "\n";
    std::cout << "t area " << t.area() << " and circumference " << t.circumference() << "\n";
    std::cout << "c area " << c.area() << " and circumference " << c.circumference() << "\n";


    return 0;
}