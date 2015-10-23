/**
 * Problem link: https://leetcode.com/problems/generate-parentheses
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 */
#include <iostream>
#include <string>
#include <vector>
#include <assert.h>

using namespace std;

class Solution {
    public:
        vector<string> generateParenthesis(int n) {
            vector<string> result;
            string buffer(n << 1, '\0');
            generate(buffer, 0, 0, n, result);
            return result;
        }
    private:
        void generate(string& str, int index, int left, int n, vector<string>& result) {
            if(n == 0) {
                result.push_back(str);
                return;
            }
            if(left < n) {
                str[index] = '(';
                generate(str, index + 1, left + 1, n, result);
                if(left > 0) {
                    str[index] = ')';
                    generate(str, index + 1, left - 1, n - 1, result);
                }
            } else {
                str[index] = ')';
                generate(str, index + 1, left - 1, n - 1, result);
            }
        }
};

void print_vec(vector<string>& result) {
    for(auto value : result) {
        cout << value << " ";
    }

    cout << endl;
}
int main(int argv, char** argc) {
    Solution solution;

    vector<string> result;

    result = solution.generateParenthesis(3);
    assert(result.size() == 5);
    print_vec(result);
}
