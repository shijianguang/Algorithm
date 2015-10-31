/**
 * Problem link: https://leetcode.com/problems/implement-trie-prefix-tree
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */

#include <iostream>
#include <string>
#include <assert.h>

using namespace std;

const int ALPHABET_NUM = 26;
class TrieNode {
    public:
        bool flag;
        TrieNode* next[ALPHABET_NUM];
        TrieNode() {
            flag = false;
            memset(next, 0, sizeof(next));
        }
};

class Trie {
    public:
        Trie() {
            root = new TrieNode();
        }

        void insert(string word) {
            int size = word.length();
            TrieNode* iter = root;
            for(int i = 0 ; i < size ; ++ i) {
                int index = word[i] - 'a';
                if(iter->next[index] != NULL) {
                    iter = iter->next[index];
                } else {
                    TrieNode* node = new TrieNode();
                    iter->next[index] = node;
                    iter = node;
                }

            }
            iter->flag = true;
        }

        bool search(string word) {
            int size = word.length();
            TrieNode* iter = root;
            for(int i = 0 ; i < size ; ++ i) {
                int index = (int)word[i] - 'a';
                if(iter->next[index] == NULL) {
                    return false;
                } else {
                    iter = iter->next[index];
                }
            }

            return iter->flag;
        }

        bool startsWith(string prefix) {
            int size = prefix.length();
            TrieNode* iter = root;
            for(int i = 0 ; i < size ; ++ i) {
                int index = (int)prefix[i] - 'a';
                if(iter->next[index] == NULL) {
                    return false;
                } else {
                    iter = iter->next[index];
                }
            }
            return true;
        }

    private:
        TrieNode* root;
};

int main(int argc, char ** argv) {
    // Your Trie object will be instantiated and called as such:
    Trie trie;
    trie.insert("somestring");
    assert(trie.search("key") == false);
    cout << trie.startsWith("some") << endl;
    assert(trie.startsWith("some"));
    trie.insert("test");
    assert(trie.search("test"));
}
