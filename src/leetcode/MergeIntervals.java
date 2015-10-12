/**
 * Problem link: https://leetcode.com/problems/merge-intervals
 *
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */

import java.util.*;

public class MergeIntervals {
    public static void main(String[] args) {
    }

    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new IntervalComparator());
        int size = intervals.size();
        Interval latest = null;
        List<Interval> result = new ArrayList<>();
        for(Interval interval : intervals) {
            if(latest == null) {
                latest = interval;
            } else {
                if(interval.start <= latest.end) {
                    if(interval.end > latest.end) {
                        latest.end = interval.end;
                    }
                } else {
                    result.add(latest);
                    latest = interval;
                }
            }
        }

        if(latest != null) {
            result.add(latest);
        }

        return result;
    }

    class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval o1, Interval o2) {
            if(o1.start < o2.start) {
                return -1;
            } else if(o1.start > o2.start) {
                return 1;
            } else {
                if(o1.end < o2.end) {
                    return -1;
                } else if(o1.end > o2.end) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
}
