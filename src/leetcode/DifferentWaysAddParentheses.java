/**
 * Problem link: https://leetcode.com/problems/different-ways-to-add-parentheses
 *
 * iven a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
 *
 * Example 1
 * Input: "2-1-1".
 *
 *    ((2-1)-1) = 0
 *    (2-(1-1)) = 2
 *
 * Output: [0, 2]
 *
 *
 * Example 2
 * Input: "2*3-4*5"
 *
 *    (2*(3-(4*5))) = -34
 *    ((2*3)-(4*5)) = -14
 *    ((2*(3-4))*5) = -10
 *    (2*((3-4)*5)) = -10
 *    (((2*3)-4)*5) = 10
 *
 * Output: [-34, -14, -10, -10, 10]
 */

import java.util.*;

public class DifferentWaysAddParentheses {
    public static void main(String[] args) {
        DifferentWaysAddParentheses solution = new DifferentWaysAddParentheses();
        List<Integer> result = solution.diffWaysToCompute("2*3-4*5");
        for(Integer element : result) {
            System.out.println(element);
        }
    }

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> numberList = new ArrayList<>();
        List<Character> oper = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        parse(input, numberList, oper);
        if(numberList.size() <= 1) {
            return numberList;
        }

        for(int i = 0 ; i < oper.size() ; ++ i) {
            List<Integer> part1 = calculate(numberList, oper, 0, i);
            List<Integer> part2 = calculate(numberList, oper, i + 1, oper.size());
            for(int k = 0 ; k < part1.size() ; ++ k) {
                for(int j = 0 ; j < part2.size() ; ++ j) {
                    Integer tmp = compute(part1.get(k), part2.get(j), oper.get(i));
                    result.add(tmp);
                }
            }
        }

        return result;

    }

    private List<Integer> calculate(List<Integer> numberList, List<Character> oper, int start, int end) {
        List<Integer> result = new ArrayList<>();
        if(start == end) {
            result.add(numberList.get(start));
        } else {
            for(int i = start ; i < end ; ++ i) {
                List<Integer> part1 = calculate(numberList, oper, start, i);
                List<Integer> part2 = calculate(numberList, oper, i + 1, end);
                for(int k = 0 ; k < part1.size() ; ++ k) {
                    for(int j = 0 ; j < part2.size() ; ++ j) {
                        Integer tmp = compute(part1.get(k), part2.get(j), oper.get(i));
                        result.add(tmp);
                    }
                }
            }
        }

        return result;
    }

    private Integer compute(Integer a, Integer b, Character c) {
        if(c == '+')
            return a + b;
        if(c == '-')
            return a - b;
        if(c == '*')
            return a * b;

        return 0;
    }

    private void parse(String expression, List<Integer> numberList, List<Character> oper) {
        if(expression == null || expression.length() == 0) {
            return;
        }

        StringBuffer buffer = new StringBuffer();
        for(int i = 0 ; i < expression.length() ; ++ i) {
            if(expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') {
                numberList.add(Integer.valueOf(buffer.toString()));
                oper.add(expression.charAt(i));
                buffer.setLength(0);
            } else {
                buffer.append(expression.charAt(i));
            }
        }

        if(buffer.length() > 0) {
            numberList.add(Integer.valueOf(buffer.toString()));
        }
    }
}
