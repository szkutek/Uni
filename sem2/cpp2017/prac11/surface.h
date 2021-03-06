//
// Created by szkutek on 02.06.17.
//

#ifndef PRAC11_SURFACE_H
#define PRAC11_SURFACE_H

#include "surf.h"
#include <vector>
#include <iostream>

struct surface {
    surf *ref;

    surface(const surface &s);

    surface(surface &&s);

    surface(const surf &s);

    surface(surf &&s);

    void operator=(const surface &s);

    void operator=(surface &&s);

    void operator=(const surf &s);

    void operator=(surf &&s);


    ~surface() {
        delete ref;
    }

    const surf &getsurf() const {
        return *ref;
    }
// There is no non-const access, because
// changing would be dangerous.

};

std::ostream &operator<<(std::ostream &stream, const surface &s);


std::ostream &operator<<(std::ostream &stream, const std::vector<surface> &table);

void print_statistics(const std::vector<surface> &table);


#endif //PRAC11_SURFACE_H
