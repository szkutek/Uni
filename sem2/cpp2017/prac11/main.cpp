#include "surface.h"

int main() {
    const rectangle r(0, 0, 1, 1);
    const triangle t(0, 0, 1, 0, 0, 1);
    circle c(0, 0, 1);

//    konstruktory

    const surface s1(t);
    surface s2(std::move(t));
    surface s3(s1);
    surface s4(std::move(s3));

//    std::cout << "r:\n" << r << "\n";
//    std::cout << "surface s1(t): \n" << s1 << "\n";
//    std::cout << "surface s2(std::move(t)):\n" << s2 << "\n";
//    std::cout << "surface s3(s1):\n" << s3 << "\n";
//    std::cout << "surface s4(std::move(s3)):\n" << s4 << "\n";

    surface z1 = s1;
//    surface z2 = std::move(z1);
    surface z2 = surface(t);
    surface z3 = t;
    surface z4 = std::move(c);

    const std::vector<surface> table = {z1, z2, z3, z4};
    std::cout << "--------------------------------\n";
    std::cout << "surface vector:\n\n" << &table << "\n";

    print_statistics(table);

    return 0;
}