/**
 * Problem link: https://leetcode.com/problems/add-digits
 *
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 *
 * For example:
 *
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 *
 * Follow up:
 * Could you do it without any loop/recursion in O(1) runtime?
 *
 */

public class AddDigits {
    public static void main(String[] args) {
        AddDigits solution = new AddDigits();

        assert solution.addDigits(38) == 2;
        assert solution.addDigits(138) == 3;
        assert solution.addDigits(1238) == 5;
    }

    public int addDigits(int num) {
        if(num < 10) {
            return num;
        }

        int result = ((num - 10) % 9) + 1;
        return result;
    }
}
