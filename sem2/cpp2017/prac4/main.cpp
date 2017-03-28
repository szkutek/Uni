
#include "string.h"
#include <iostream>

#include <stdexcept>
#include <vector>

// From the lecture. Not needed for the task:

void fail_often() {
    srand(time(NULL));
    if (rand() & 1)
        throw std::runtime_error("i failed");
}

void f() {
    string s = "this is a string";

    std::vector<string> vect = {"these", "are", "also", "string"};
    string more[] = {"these", "are", "even", "more", "string"};

    // fail_often( );
}

int main(int argc, char *argv[]) {
    // Add more tests by yourself. Untested code = unwritten code.

    string s;
    string s2 = "hello";
    std::cout << "s2 = " << s2 << "\n";

    s = s2;  // Assignment, not initialization.
    s = s;

    std::cout << "s[2] = " << s[2] << "\n";

    s += '2';
    std::cout << "s = " << s << "\n";

    s += s;
    std::cout << "s = " << s << "\n";

    string s3 = s2 + s2;
    std::cout << "s3 = " << s3 << "\n\n";

    s = "this is a string";
    std::cout << s << "\n";
    for (char &c : s)
        c = toupper(c);
    std::cout << "s = " << s << "\n";

    std::cout << "s > s2 " << (s > s2) << "\n";
    std::cout << "s2 == \"hello\" " << (s2 == "hello") << "\n";
    std::cout << "s2 <= \"hello\" " << (s2 <= "hello") << "\n";

    string s1 = "aaaa";
    string s4 = "bb";

    std::cout << " aaaa < bb  " << (s1 < s4) << "\n";

}


