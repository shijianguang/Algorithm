/**
 * Problem link: https://leetcode.com/problems/sliding-window-maximum
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 *
 * Note: 
 * You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 *
 * Follow up:
 * Could you solve it in linear time?
 *
 */

import java.util.*;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] test = {1, 3, -1, -3, 5, 3, 6, 7};
        SlidingWindowMaximum solution = new SlidingWindowMaximum();
        int[] result = solution.maxSlidingWindow(test, 3);
        for(int i = 0 ;i < result.length ; ++ i) {
            System.out.print(result[i] + " ");
        }
        System.out.println();

        assert result.length == 6;
        assert result[0] == 3;
        assert result[1] == 3;
        assert result[2] == 5;
        assert result[3] == 5;
        assert result[4] == 6;
        assert result[5] == 7;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k == 0) {
            return new int[0];
        }
        int length = nums.length;
        int[] result = new int[length - k + 1];
        int resultIndex = 0;

        Deque<Pair> queue = new ArrayDeque<>();

        for(int i = 0 ; i < k ; ++ i) {
            while(queue.size() > 0 && queue.getLast().second < nums[i])
                queue.removeLast();

            queue.addLast(Pair.newPair(i, nums[i]));
        }
        result[resultIndex ++] = queue.getFirst().second;
        for(int i = k ; i < length ; ++ i) {
            while(queue.size() > 0 && queue.getFirst().first <= (i - k)) {
                queue.removeFirst();
            }

            while(queue.size() > 0 && queue.getLast().second < nums[i]) {
                queue.removeLast();
            }
            queue.addLast(Pair.newPair(i, nums[i]));
            result[resultIndex ++] = queue.getFirst().second;
        }

        return result;
    }

    static class Pair {
        int first;
        int second;
        static Pair newPair(int first, int second) {
            Pair pair = new Pair();
            pair.first = first;
            pair.second = second;
            return pair;
        }
    }
}
