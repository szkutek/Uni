//
// Created by szkutek on 02.06.17.
//

#include <vector>
#include <iostream>
#include "surface.h"

surface::surface(const surface &s)
        : ref{s.ref->clone()} {}

surface::surface(surface &&s)
        : ref{std::move(*s.ref).clone()} {}

surface::surface(const surf &s)
        : ref{s.clone()} {}

surface::surface(surf &&s)
        : ref{std::move(s).clone()} {}


void surface::operator=(const surface &s) {
    *this = surface(s);
}

void surface::operator=(surface &&s) {
    std::swap(ref, s.ref);
}

void surface::operator=(const surf &s) {
    if (&s != ref) {
        delete ref;
        ref = s.clone();
    }
}

void surface::operator=(surf &&s) {
    if (&s != ref) {
        delete ref;
        ref = std::move(s).clone();
    }
}


std::ostream &operator<<(std::ostream &stream, const surface &s) {
    s.getsurf().print(stream);
    return stream;
}


std::ostream &operator<<(std::ostream &stream, const std::vector<surface> &table) {
    for (size_t i = 0; i < table.size(); ++i) {
        stream << i << "-th element = " << table[i] << "\n";
    }
    return stream;
}

void print_statistics(const std::vector<surface> &table) {
    double total_area = 0.0;
    double total_circumference = 0.0;
    for (const auto &s : table) {
        std::cout << "adding info about " << s << "\n";
        total_area += s.getsurf().area();
        total_circumference += s.getsurf().circumference();
    }
    std::cout << "total area is " << total_area << "\n";
    std::cout << "total circumference is " << total_circumference << "\n";
}