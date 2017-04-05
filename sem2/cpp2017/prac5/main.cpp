
#include "tree.h"


int main(int argc, char *argv[]) {
    tree t1(string("a"));
    tree t2(string("b"));
    tree t3 = tree(string("f"), {t1, t2});

    tree t4 = subst(t3,"a",tree("c"));
    std::cout << t3 << "\n\n";

    std::cout << t4 << "\n";
    return 0;

    std::vector<tree> arguments = {t1, t2, t3};
    std::cout << tree("F", std::move(arguments)) << "\n";

    t2 = t3;
    t2 = std::move(t3);

//    size_t i = 0;
//    const tree t4 = t3[i];
//    std::cout << "abc" << std::endl;

    return 0;

}



