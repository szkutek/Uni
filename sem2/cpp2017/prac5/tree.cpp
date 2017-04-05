
#include "tree.h"


std::ostream &operator<<(std::ostream &stream, const tree &t) {
    stream << t.functor() << "[" << t.getaddress() << "]\n";
    for (size_t i = 0; i < t.nrsubtrees(); i++)
        stream << t[i] << " ";
    return stream;
}
// Doesn't need to be friend, because it uses only functor( ),
// nrsubtrees( ) and [ ].

tree subst(const tree &t, const string &var, const tree &val) {

//    tree tmp(t);

    if (t.nrsubtrees() == 0) {
        if (t.functor() == var)
            return val;
        return t;
    }
    tree tmp = t;
    for (size_t i = 0; i < tmp.nrsubtrees(); i++)
//            tmp[i] = subst(tmp[i], var, val);
        tmp.replacesubtree(i, subst(tmp[i], var, val));
    return tmp;


}
