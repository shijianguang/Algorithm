/**
 * Problem link: https://leetcode.com/problems/longest-palindromic-substring
 *
 *
 */
#include <iostream>
#include <string>
#include <assert.h>

using namespace std;

class Solution {
    public:
        string longestPalindrome(string s) {
            return longestPalindrome_fast(s);
        }
    private:
        string longestPalindrome_fast(string& s) {
            string tmp;
            int length = s.length();
            for(int i = 0 ; i < length ; ++ i) {
                tmp.push_back('#');
                tmp.push_back(s[i]);
            }
            tmp.push_back('#');

            int id = 0;
            int mx = 0;
            int tmp_length = tmp.size();
            int* p = new int[tmp_length];
            for (int i = 0 ; i < tmp_length ; ++ i) {
                int tmp_p;
                if(mx > i) {
                    tmp_p = std::min(p[(id << 1) - i], mx - i);
                } else {
                    tmp_p = 1;
                }


                for( ; i - tmp_p >= 0 && i + tmp_p < tmp_length && tmp[i + tmp_p] == tmp[i - tmp_p] ; ++ tmp_p);
                p[i] = tmp_p;
                if(tmp_p + i > mx) {
                    mx = tmp_p + i;
                    id = i;
                }
            }

            int max_length = 0;
            for(int i = 0 ; i < tmp_length ; ++ i) {
                if(p[i] > max_length) {
                    max_length = p[i];
                    id = i;
                }
            }
            -- max_length;
            if(max_length == 0) {
                return "";
            } else {
                int start = (id - max_length) >> 1;
                return s.substr(start, max_length);
            }
        }

        string longestPalindrome_brute(string& s) {
            int start = 0, end = 0, max_length = 0;
            int length = s.length();
            for(int i = 0 ; i < length ; ++ i) {
                int tmp_start, tmp_end;
                expand_around_center(s, i, i, tmp_start, tmp_end);
                if((tmp_end - tmp_start + 1) > max_length) {
                    start = tmp_start;
                    end = tmp_end;
                    max_length = end - start + 1;
                }

                expand_around_center(s, i, i + 1, tmp_start, tmp_end);
                if((tmp_end - tmp_start + 1) > max_length) {
                    start = tmp_start;
                    end = tmp_end;
                    max_length = end - start + 1;
                }
            }

            if(max_length == 0) {
                return "";
            } else {
                return s.substr(start, max_length);
            }
        }

        void expand_around_center(string s, int l, int r, int& start, int& end) {
            int length = s.length();
            while(l >= 0 && r < length && s[l] == s[r]) {
                -- l;
                ++ r;
            }

            start = l + 1;
            end = r - 1;
        }
};

int main(int argc, char **argv) {
    Solution solution;

    cout << solution.longestPalindrome("abcba") << endl;
    assert(solution.longestPalindrome("abcba") == "abcba");
    //assert(solution.longestPalindrome("") == "");
    cout << solution.longestPalindrome("a") << endl;
    assert(solution.longestPalindrome("a") == "a");
    cout << solution.longestPalindrome("afbbaabc") << endl;
    assert(solution.longestPalindrome("afbbaabc") == "baab");

}
