/**
 * Problem link: https://leetcode.com/problems/minimum-window-substring
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 *
 * Note:
 * If there is no such window in S that covers all characters in T, return the emtpy string "".
 *
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */

import java.util.*;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();
        String result = solution.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(result);
        assert result.equals("BANC");

        result = solution.minWindow("bba", "ba");
        System.out.println(result);
        assert result.equals("ba");

        result = solution.minWindow("acbbaca", "aba");
        System.out.println(result);
        assert result.equals("baca");

        result = solution.minWindow("qdsvbyuipqxnhkbgqdgozelvapgcainsofnrfjzvawihgmpwpwnqcylcnufqcsiqzwhhhjchfmqmagkrexigytklnrdslmkniuurdwzikrwlxhcbgkjegwsvnvpzhamrwgjzekjobizbreexqqewmwubtjadlowhwhiarurvcsyvwcunsylgwhisrivezrmvzwwsqppuhnreqmtkkgrjozbhjwlkpzgqwejotylamcgeqzobihmwinduloecqmtoqcejcpmqusukulncsbabodxbtbeloxzgbesdveupyocuzryutyxjdulzvpklokspqkslqodqfhlgajatkxfntqorhzcxlwmdigoyxtrcccidnlyxidnevdveczbpwpugyjhveyxhcfkpqipboehjhcombrdzhyghjncnnzwpggezrvcfzjqjngvoyyqhwwohlsvarrpzavatrcasnqbazyrzxhivfydsqasjtjiteloxposdhtfgswhrfpomnteftyonjyiojxnznfeubjctijmnyaanwgsphieqhpgsoutbbxycjaxrklekogakpsbwdimkxvelpyosvmxgnuxzgejwmjgbehxhpmtohzbyxqsvepbrmzsufcqrnwttfscxgxlpxnpufirjxtdjuvfzzvqprlizelwmkjchwtcdbvpbosminsjpncehnmgtzegknkrmdvrhrgihywsoobdedhltvtmxzuhmeaakysrpybyzxqnouqszzfswahtzbanidoubilsgoqfnjubdmvclaxkaedbfeppj", "fjknwevk");
        System.out.println(result);
        assert result.equals("fzzvqprlizelwmkjchwtcdbvpbosminsjpncehnmgtzegk");
    }

    public String minWindow(String s, String t) {
        int start = 0, end = 0;
        int tLength = t.length();
        int sLength = s.length();
        boolean[] hashArray = new boolean[256];
        short[] count = new short[256];

        for(int i = 0 ;i < tLength ; ++ i) {
            short charCode = (short)(t.charAt(i));
            hashArray[charCode] = true;
            count[charCode] += 1;
        }

        for(start = 0 ; start < sLength ; ++ start) {
            if(hashArray[(short)(s.charAt(start))]) {
                break;
            }
        }

        if(start == sLength) {
            return "";
        }

        int currentCount = tLength;
        for(end = start ; end < sLength ; ++ end) {
            short charCode = (short)(s.charAt(end));
            if(hashArray[charCode]) {
                if(count[charCode] > 0) {
                    currentCount -= 1;
                }
                count[charCode] -= 1;
            }
            if(currentCount == 0)
                break;
        }
        if(end == sLength) {
            return "";
        }

        for( ; start < sLength ; ++ start) {
            short charCode = (short)(s.charAt(start));
            if(hashArray[charCode]) {
                if(count[charCode] == 0) {
                    break;
                }

                count[charCode] += 1;
            }
        }

        int currentLength = end - start;
        if(currentLength == 0) {
            return s.substring(start, end + 1);
        }
        int resultStart = start;
        int resultEnd = end;
        char nextChar = s.charAt(start);
        for(int i = end + 1 ; i < sLength ; ++ i) {
            if(s.charAt(i) == nextChar) {
                end = i;
                int j = start + 1;
                for( ; j <= end ; ++ j) {
                    short charCode = (short)(s.charAt(j));
                    if(hashArray[charCode]) {
                        if(count[charCode] < 0) {
                            count[charCode] += 1;
                            continue;
                        }
                        if(count[charCode] == 0) {
                            break;
                        }
                    }
                }
                start = j;
                nextChar = s.charAt(start);
                if(currentLength > end - start) {
                    currentLength = end - start;
                    resultStart = start;
                    resultEnd = end;
                }
            } else {
                short charCode = (short)(s.charAt(i));
                if(hashArray[charCode]) {
                    count[charCode] -= 1;
                }
            }
        }

        return s.substring(resultStart, resultEnd + 1);
    }
}
