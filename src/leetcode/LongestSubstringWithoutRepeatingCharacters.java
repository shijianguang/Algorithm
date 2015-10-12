/**
 * Problem link: https://leetcode.com/problems/longest-substring-without-repeating-characters
 *
 * Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 */

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();

        System.out.println(solution.lengthOfLongestSubstring("bbbbbbbbb"));
        assert solution.lengthOfLongestSubstring("bbbbbbbbb") == 1;
        System.out.println(solution.lengthOfLongestSubstring("abcdefg"));
        assert solution.lengthOfLongestSubstring("abcdefg") == 7;
        assert solution.lengthOfLongestSubstring("abcabcbb") == 3;
    }
    public int lengthOfLongestSubstring(String s) {
        int[] hash = new int[256];

        Arrays.fill(hash, -1);

        int start = 0;
        int maxLength = 0;
        int length = s.length();
        for(int i = 0 ; i < length ; ++ i) {
            int index = (int)s.charAt(i);
            if(hash[index] >= start) {
                int subLength = i - start;
                if(subLength > maxLength) {
                    maxLength = subLength;
                }
                start = hash[index] + 1;
            }

            hash[index] = i;
        }

        int subLength = length - start;
        if(subLength > maxLength) {
            maxLength = subLength;
        }

        return maxLength;
    }

}
