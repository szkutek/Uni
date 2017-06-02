//
// Created by szkutek on 02.06.17.
//

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

std::ostream &surface::operator<<(std::ostream &stream, const surface &s) {
    s.getsurf().print(stream);

    return stream;
}
