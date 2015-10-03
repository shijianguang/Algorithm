/**
 * Problem link: https://leetcode.com/problems/h-index
 *
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 *
 * According to the definition of {@link https://en.wikipedia.org/wiki/H-index h-index on Wikipedia}: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 *
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.
 *
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 *
 * Hint:
 *
 * An easy approach is to sort the array first.
 * What are the possible values of h-index?
 * A faster approach is to use extra space.
 */

public class HIndex {
    public static void main(String[] args) {
        HIndex solution = new HIndex();

        assert solution.hIndex(new int[]{3, 0, 6, 1, 5}) == 3;
        assert solution.hIndex(new int[]{0, 0}) == 0;
    }

    public int hIndex(int[] citations) {
        int[] counter = new int[citations.length + 1];
        int maxH = citations.length;

        for(int i = 0 ; i < citations.length ; ++ i) {
            int cita = citations[i];
            if(cita > maxH) {
                ++ counter[maxH];
            } else {
                ++ counter[cita];
            }
        }

        int sum = 0;
        int i = maxH;
        for ( ; i > 0 ; -- i) {
            sum += counter[i];
            if(i <= sum) {
                break;
            }
        }

        return i;
    }
}
