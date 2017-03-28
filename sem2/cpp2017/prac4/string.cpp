
#include "string.h"

std::ostream &operator<<(std::ostream &out, const string &s) {
    for (char c : s)
        out << c;
    return out;
}

void string::operator+=(char c) { // Append character at the end.
    char *tmp = p;
    p = new char[len + 1];
    strncpy(p, tmp, len);
    p[len] = c;
    len++;
    delete[] tmp;
}

void string::operator+=(const string &s) {
    char *tmp = p;
    p = new char[len + s.get_len()];
    strncpy(p, tmp, len);
    strncpy(p + len, s.p, s.get_len());
    len += s.get_len();
    delete[] tmp;
}

string operator+(string s1, const string &s2) {
    s1 += s2;
    return s1;
}

bool operator==(const string &s1, const string &s2) {
    if (s1.get_len() != s2.get_len()) return false;

    for (size_t i = 0; i < s1.get_len(); i++)
        if (s1[i] != s2[i]) return false;

    return true;
}

bool operator!=(const string &s1, const string &s2) {
    return !(s1 == s2);
}

bool operator<(const string &s1, const string &s2) {
    // aa<ab
    size_t i = 0;
    size_t min = (s1.get_len() < s2.get_len()) ? s1.get_len() : s2.get_len();
    while (i < min && s1[i] == s2[i])
        i++;
    if (i == s2.get_len()) return false;
    if (i == s1.get_len()) return true;
    return s1[i] < s2[i];
}

bool operator>(const string &s1, const string &s2) {
    return s2 < s1;
}

bool operator<=(const string &s1, const string &s2) {
    return !(s1 > s2);
}

bool operator>=(const string &s1, const string &s2) {
    return !(s1 < s2);
}