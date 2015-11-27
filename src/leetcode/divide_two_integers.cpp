/**
 * Problem link: https://leetcode.com/problems/divide-two-integers
 *
 * Divide two integers without using multiplication, division and mod operator.
 *
 * If it is overflow, return MAX_INT.
 */

#include <iostream>
#include <assert.h>

using namespace std;

class Solution {
    public:
        int divide(int dividend, int divisor) {
            if(dividend == INT_MIN && divisor == -1) {
                return INT_MAX;
            }
            long long divd = dividend;
            long long divs = divisor;
            divd = divd >= 0 ? divd : -divd;
            divs = divs >= 0 ? divs : -divs;
            int res = 0;
            while(divd >= divs) {
                long long a = divs;
                int i;
                for(i = 1 ; a <= divd ; ++ i) {
                    a <<= 1;
                }

                res += (1 << (i - 2));
                divd -= (divs << (i - 2));
            }

            return ((dividend > 0) ^ (divisor > 0)) ? -res : res;
        }
};

int main(int argc, char **argv) {
    Solution solution;

    assert(solution.divide(5, 2) == 2);
    assert(solution.divide(4, 2) == 2);
    assert(solution.divide(4, 3) == 1);
    assert(solution.divide(-14, 3) == -4);
    assert(solution.divide(10, 3) == 3);
    assert(solution.divide(2147483647, -1) == -2147483647);
    assert(solution.divide(-2147483648, -1) == 2147483647);
}
