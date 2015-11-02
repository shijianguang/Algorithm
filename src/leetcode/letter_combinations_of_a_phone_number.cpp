/**
 * Problem link: https://leetcode.com/problems/letter-combinations-of-a-phone-number
 *
 * Given a digit string, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */

#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Solution {
    public:
        string digit2letters[10] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        vector<string> letterCombinations(string digits) {
            vector<string> result;
            if(digits.size() == 0) {
                return result;
            }
            string letters;
            dfs(digits, 0, letters, result);
            return result;
        }
    private:
        void dfs(string& digits, int index, string& letters, vector<string>& result) {
            if(index == digits.size()) {
                result.push_back(letters);
                return;
            }

            int digit = digits[index] - '0';
            if(digit == 0 || digit == 1) {
                dfs(digits, index + 1, letters, result);
            } else {
                string tmp = digit2letters[digit];
                for(int i = 0 ; i < tmp.size() ; ++ i) {
                    letters += tmp[i];
                    dfs(digits, index + 1, letters, result);
                    letters.erase(letters.size() - 1, 1);
                }
            }
        }
};

int main(int argc, char** argv) {
    Solution solution;

    vector<string> result = solution.letterCombinations("23");

    for(auto ele : result) {
        cout << ele << " ";
    }

    cout << endl;
}
