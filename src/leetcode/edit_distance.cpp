#include <iostream>
#include <string>
#include <vector>
#include <assert.h>

using namespace std;

class Solution {
    public:
        int minDistance(string word1, string word2) {
            int word1_len = word1.length();
            int word2_len = word2.length();

            int** dp = new int*[word2_len + 1];
            for(int i = 0 ; i <= word2_len ; ++ i) {
                dp[i] = new int[word1_len + 1];
            }

            for(int i = 0 ; i <= word2_len ; ++ i) {
                dp[i][0] = i;
            }

            for(int i = 0 ; i <= word1_len ; ++ i) {
                dp[0][i] = i;
            }

            for(int i = 1 ; i <= word2_len ; ++ i) {
                for(int j = 1 ; j <= word1_len ; ++ j) {
                    if(word2[i - 1] == word1[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = std::min(std::min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                    }
                }
            }

            int result = dp[word2_len][word1_len];
            delete[] dp;
            return result;
        }
};

int main(int argc, char** argv) {

    Solution solution;

    cout << solution.minDistance("ab", "ac") << endl;
    assert(solution.minDistance("ab", "ac") == 1);
    assert(solution.minDistance("abc", "ac") == 1);
    assert(solution.minDistance("sea", "eat") == 2);
}
