/**
 * Problem link: https://leetcode.com/problems/first-bad-version
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 */
public class FirstBadVersion {
    private static final int BAD_VERSION = 1702766719;
    public static void main(String[] args) {

        FirstBadVersion solution = new FirstBadVersion();

        assert solution.firstBadVersion(2126753390) == 1702766719;
    }

    public int firstBadVersion(int n) {
        if(isBadVersion(1)) {
            return 1;
        }

        long start = 1;
        long end = n;
        while(end > start) {
            long mid = (start + end) >> 1;
            if(isBadVersion((int)mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        if(isBadVersion((int)end)) {
            return (int)end;
        } else {
            return (int)(end + 1);
        }
    }

    /**
     * Mock implementation for test
     */
    boolean isBadVersion(int version) {
        if(version >= BAD_VERSION) {
            return true;
        } else {
            return false;
        }
    }
}
