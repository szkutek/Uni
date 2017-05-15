
#include "fifteen.h"


fifteen::fifteen()
        : open_i{dimension - 1},
          open_j{dimension - 1} {
    size_t k = 1;
    for (size_t i = 0; i < dimension; i++) {
        for (size_t j = 0; j < dimension; j++) {
            table[i][j] = k;
            k++;
        }
    }
    table[open_i][open_j] = 0;
}

fifteen::fifteen(std::initializer_list<std::initializer_list<size_t >> init) {
    size_t i = 0;
    for (auto m : init) {
        size_t j = 0;
        for (auto n : m) {
            if (n == 0) {
                open_i = i;
                open_j = j;
            }
            table[i][j] = n;
            j++;
        }
        i++;
    }
}

void fifteen::makemove(move m) {
    switch (m) {
        case move::up:
            if (open_i == dimension - 1) throw illegalmove(m);
            std::swap(table[open_i][open_j], table[open_i + 1][open_j]);
            open_i += 1;
            break;
        case move::down:
            if (open_i == 0) throw illegalmove(m);
            std::swap(table[open_i][open_j], table[open_i - 1][open_j]);
            open_i -= 1;
            break;
        case move::left:
            if (open_j == dimension - 1) throw illegalmove(m);
            std::swap(table[open_i][open_j], table[open_i][open_j + 1]);
            open_j += 1;
            break;
        case move::right:
            if (open_j == 0) throw illegalmove(m);
            std::swap(table[open_i][open_j], table[open_i][open_j - 1]);
            open_j -= 1;
            break;
    }
}

void fifteen::makemove(const std::list<move> &moves) {
    for (move m : moves) makemove(m);
}


size_t fifteen::distance() const {
    size_t d = 0;
    for (size_t i = 0; i < dimension; i++) {
        for (size_t j = 0; j < dimension; j++) {
            d += distance(position(i, j), solvedposition(table[i][j]));
        }
    }
    return d;
}

bool fifteen::issolved() const {
    fifteen solved;
    return equals(solved);
}

size_t size_t_hash(size_t x) {
    return (37 + x) * 13;
}

size_t fifteen::hashvalue() const {
    size_t hash = 0;
    int l = 53;
    for (size_t i = 0; i < dimension; i++) {
        for (size_t j = 0; j < dimension; j++) {
            hash += ((l + size_t_hash(table[i][j])) * l + size_t_hash(i)) * l + size_t_hash(j);
        }
    }
    return hash;
}

bool fifteen::equals(const fifteen &other) const {
    for (size_t i = 0; i < dimension; i++) {
        for (size_t j = 0; j < dimension; j++) {
            if (table[i][j] != other.table[i][j])
                return false;
        }
    }
    return true;
}


std::ostream &operator<<(std::ostream &stream, const fifteen &f) {
    stream << std::setfill(' ');  //fill with spaces
    for (size_t i = 0; i < f.dimension; i++) {
        for (size_t j = 0; j < f.dimension; j++) {
            stream << std::setw(5) << f.table[i][j] << std::right;
        }
        stream << std::endl;
    }
    return stream;
}