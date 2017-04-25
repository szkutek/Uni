#include <iostream>
#include <map>
#include <unordered_map>
#include <vector>
#include <fstream>

struct case_insensitive_cmp {
    bool operator( )(const std::string &s1, const std::string &s2) const {
        // aa<ab
        size_t i = 0;
        size_t min = (s1.size() < s2.size()) ? s1.size() : s2.size();
        while (i < min && (std::tolower(s1[i]) == std::tolower(s2[i])))
            i++;
        if (i == s2.size()) return false;
        if (i == s1.size()) return true;
        return (std::tolower(s1[i]) < std::tolower(s2[i]));

    }
// Return true if s1 < s2, ignoring case of the letters.
};

struct case_insensitive_hash {
    size_t operator( )(const std::string &s) const {

        size_t hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = hash * 37 + std::tolower(s[i]);
        }
        return hash;
    }
};

struct case_insensitive_equality {
    bool operator( )(const std::string &s1, const std::string &s2) const {
        case_insensitive_cmp c;
        // slabe
        return !c(s1, s2) && !c(s2, s1);
    }
};


std::map<std::string, unsigned int, case_insensitive_cmp> frequencytable(
        const std::vector<std::string> &text) {

    std::map<std::string, unsigned int, case_insensitive_cmp> freq;
    for (auto word : text) freq[word]++;
    return freq;
}

std::ostream &operator<<(std::ostream &stream,
                         const std::map<std::string, unsigned int, case_insensitive_cmp> &freq) {
    for (auto iter = freq.begin(); iter != freq.end(); iter++) {
        stream << iter->first << ": " << iter->second << std::endl;
    }
    return stream;
}

std::unordered_map<std::string, unsigned int, case_insensitive_hash,
        case_insensitive_equality> hashed_frequencytable(const std::vector<std::string> &text) {

    std::unordered_map<std::string, unsigned int, case_insensitive_hash,
            case_insensitive_equality> freq;
    for (std::string word : text) freq[word]++;
    return freq;
}

std::vector<std::string> readfile(std::istream &input) {
    std::vector<std::string> vector;
    std::string new_word;
    while (true) {
        int c = input.get();
        if (c == EOF) break;

        if (isspace(c) || ispunct(c)) {
            if (!new_word.empty()) {
                vector.push_back(std::move(new_word));
                new_word.clear();
            }
        } else new_word += static_cast<char>(c);
    }
    if (new_word.size() > 0)
        vector.push_back(new_word);
    return vector;
}

int main() {
    std::vector<std::string> text = {"raz", "dwa", "trzy", "dwa"};
    std::map<std::string, unsigned int, case_insensitive_cmp> freq;
    freq = frequencytable(text);
    std::cout << freq << std::endl;

    std::cout << frequencytable(std::vector<std::string>
                                        {"AA", "aA", "Aa", "this", "THIS"}) << std::endl;


    case_insensitive_cmp c;
    std::cout << c("a", "A") << " " << c("a", "B") << " " << c("A", "b") << "\n";

    case_insensitive_hash h;
    std::cout << h("xxx") << " " << h("XXX") << "\n";
    std::cout << h("Abc") << " " << h("abC") << "\n";
    // Hash value should be case insensitive.
    case_insensitive_equality e;
    std::cout << e("xxx", "XXX") << "\n\n";
    // Prints ’1’.

    std::vector<std::string> texthashed = {"raz", "dwa", "trzy", "dwa"};
    std::map<std::string, unsigned int, case_insensitive_cmp> freqhashed;
    freqhashed = frequencytable(texthashed);
    std::cout << freqhashed << std::endl;


    std::ifstream file("book1.html", std::ifstream::in);
    std::vector<std::string> book = readfile(file);
    std::map<std::string, unsigned int, case_insensitive_cmp> book_freq = frequencytable(book);

//    std::cout << book_freq;
    size_t max = 0;
    std::string max_string;
    for (auto s : book_freq) {
        if (s.second > max) {
            max = s.second;
            max_string = s.first;
        }
    }
    std::cout << "Maksymalna ilosc wystapien w tekscie:\n" << max_string << " " << max << std::endl;
    std::cout << book_freq["hominum"] << std::endl;


    return 0;
}