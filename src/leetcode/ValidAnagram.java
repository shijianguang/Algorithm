/**
 * Problem link: https://leetcode.com/problems/valid-anagram/
 */

public class ValidAnagram {
    public static void main(String argv[]) {
        String s = null, t = null;
        Solution solution = new Solution();

        s = "anagram";
        t = "nagaram";
        assert solution.isAnagram(s, t);

        s = "rat";
        t = "car";
        assert !solution.isAnagram(s, t);
    }

    public static class Solution {
        public boolean isAnagram(String s, String t) {
            int sLength = s.length();
            int tLength = t.length();

            if(sLength != tLength) {
                return false;
            }

            int[] hashMap = new int[128];
            for(int i = 0 ; i < sLength ; ++ i) {
                byte asciiCode = (byte)s.charAt(i);
                hashMap[(int)asciiCode] += 1;
            }

            for(int i = 0 ; i < tLength ; ++ i) {
                byte asciiCode = (byte)t.charAt(i);
                if(hashMap[(int)asciiCode] == 0) {
                    return false;
                }
                hashMap[(int)asciiCode] -= 1;
            }

            return true;
        }
    }
}
