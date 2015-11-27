/**
 * Problem link: https://leetcode.com/problems/substring-with-concatenation-of-all-words
 *
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 *
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 *
 * You should return the indices: [0,9].
 * (order does not matter).
 */
import java.util.*;
public class SubstringWithConcatenationOfAllWords {
    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWords solution = new SubstringWithConcatenationOfAllWords();

        List<Integer> result = solution.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
        printCollection(result);

        result = solution.findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"});
        printCollection(result);
    }

    private static <T> void printCollection(Collection<T> collection) {
        for(T c : collection) {
            System.out.print(c);
            System.out.print(" ");
        }

        System.out.println();
    }

    private static final Integer ONE = new Integer(1);
    public List<Integer> findSubstring(String s, String[] words) {
        int wordLen = words[0].length();
        int wordArrayLen = words.length;

        List<Integer> result = new ArrayList<>();

        Map<String, Integer> counterMap = new HashMap<>();
        for(int i = 0 ; i < wordArrayLen ; ++ i) {
            Integer count = counterMap.get(words[i]);
            if(count == null) {
                counterMap.put(words[i], ONE);
            } else {
                counterMap.put(words[i], count + 1);
            }
        }

        for(int i = 0 ; i < wordLen ; ++ i) {
            Map<String, Integer> curr = new HashMap<>();
            int start = i;
            int limit = s.length() - wordLen;
            int counter = 0;
            for(int j = i ; j <= limit ; j += wordLen) {
                String subString = s.substring(j, j + wordLen);
                Integer sum = counterMap.get(subString);
                if(sum != null) {
                    Integer n = curr.get(subString);
                    if(n == null) {
                        n = ONE;
                    } else {
                        n = n + 1;
                    }
                    curr.put(subString, n);
                    if(n <= sum) {
                        ++ counter;
                    } else {
                        while(sum < curr.get(subString)) {
                            String tmp = s.substring(start, start + wordLen);
                            Integer tmpN = curr.get(tmp);
                            if(tmpN != null) {
                                curr.put(tmp, tmpN - 1);
                                if(tmpN <= counterMap.get(tmp)) {
                                    -- counter;
                                }
                            }

                            start += wordLen;
                        }
                    }

                    if(counter == wordArrayLen) {
                        result.add(start);
                        String tmp = s.substring(start, start + wordLen);
                        Integer tmpN = curr.get(tmp);
                        curr.put(tmp, tmpN - 1);
                        -- counter;
                        start += wordLen;
                    }
                } else {
                    start = j + wordLen;
                    curr.clear();
                    counter = 0;
                }

            }
        }
        return result;
    }
}
