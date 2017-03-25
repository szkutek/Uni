
#include "stack.h"
#include <iostream>

int main() {
    stack s = {1,2,3};
    std::cout << s << "\n";

    s.top() = 10;
    s.push(4);
    std::cout << s << "\n";
    
    s.pop();
    s.pop();
    s.pop();
    s.pop();
    std::cout << s << "\n";


    stack s1 = {1, 2, 3, 4, 5};

    stack s3(s1);
    std::cout << "stack s3(s1) : " << s3 << "\n";

    stack s2 = s1; // Copy constructor.
    std::cout << "stack s2 = s1; s2 = " << s2 << "\n";
// j is not size_t, because multiplying size_t with itself is
// unnatural:
    for (unsigned int j = 0; j < 6; ++j)
        s2.push(j * j);
    std::cout << "po petli for: s2 = " << s2 << "\n";
    s1 = s2;
    std::cout << "s1 = s2; s1 = " << s1 << "\n";
// Assignment.
    s1 = s1;
    std::cout << "s1 = s1; s1 = " << s1 << "\n";
// Always check for self assignment.
    s1 = {100, 101, 102, 103};
// Works because the compiler inserts constructor and
// calls assignment with the result.

#if 1
// Won’t compile. In order to get it compiled, remove const:
    stack &sconst = s1;
    std::cout << s1 << "\n";
    sconst.top() = 20;
    sconst.push(15);
    std::cout << sconst << "\n";
#endif

}
