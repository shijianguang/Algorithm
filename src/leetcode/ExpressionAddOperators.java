/**
 * Problem link: https://leetcode.com/problems/expression-add-operators
 *
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 *
 * Examples: 
 * "123", 6 -> ["1+2+3", "1*2*3"] 
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 */
import java.util.*;

public class ExpressionAddOperators {
    public static void main(String[] args) {
        ExpressionAddOperators solution = new ExpressionAddOperators();

        String num = "232";
        long start = System.currentTimeMillis();
        List<String> result = solution.addOperators(num, 8);
        System.out.println(System.currentTimeMillis() - start);

        for(String ele : result) {
            System.out.println(ele);
        }

        assert result.size() == 2;
        assert (result.get(0).equals("2*3+2") && result.get(1).equals("2+3*2"))
            || (result.get(1).equals("2*3+2") && result.get(0).equals("2+3*2"));
    }

    public List<String> addOperators(String num, int target) {
        Set<String> set = new HashSet<>();
        addOperatorsDFS(num, target, 0, 0, "", set);
        return new ArrayList<>(set);

    }
    void addOperatorsDFS(String num, int target, long diff, long curNum, String out, Set<String> res) {
        if (num.length() == 0 && curNum == target) {
            res.add(out);
        }
        for (int i = 1; i <= num.length(); ++i) {
            String cur = num.substring(0, i);
            long curLong = Long.parseLong(cur);
            if (cur.length() > 1 && cur.charAt(0) == '0') return;
            String next = num.substring(i);
            if (out.length() > 0) {
                addOperatorsDFS(next, target, curLong, curNum + curLong, out + "+" + cur, res);
                addOperatorsDFS(next, target, -curLong, curNum - curLong, out + "-" + cur, res);
                addOperatorsDFS(next, target, diff * curLong, (curNum - diff) + diff * curLong, out + "*" + cur, res);
            } else {
                addOperatorsDFS(next, target, curLong, curLong, cur, res);
            }
        }
    }
}
