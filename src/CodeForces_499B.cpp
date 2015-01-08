/*
 * Problem link: http://codeforces.com/problemset/problem/499/B
 */

#include <stdio.h>
#include <map>
#include <string>
#include <string.h>
#include <unordered_map>

const int MAX_WORD_LEN = 15;

int main(int argc, char ** argv) {
    int n, m;
    std::map<std::string, std::string> dict;

    scanf("%d %d", &n, &m);

    char word_a[MAX_WORD_LEN];
    char word_b[MAX_WORD_LEN];

    for (int i = 0 ; i < m ; ++ i) {
        scanf("%s %s", word_a, word_b);
        if (strlen(word_a) <= strlen(word_b))
            continue;

        std::string word_a_string(word_a);
        std::string word_b_string(word_b);
        dict[word_a_string] = word_b_string;
    }

    for (int i = 0 ; i < n - 1 ; ++ i) {
        scanf("%s", word_a);
        std::string word_string(word_a);
        std::map<std::string, std::string>::iterator it = dict.find(word_string);
        if (it != dict.end())
            printf("%s ", it->second.c_str());
        else
            printf("%s ", word_a);
    }

    scanf("%s", word_a);
    std::string word_string(word_a);
    std::map<std::string, std::string>::iterator it = dict.find(word_string);
    if (it != dict.end())
        printf("%s\n", it->second.c_str());
    else
        printf("%s\n", word_a);
}