/**
 * Problem link: https://leetcode.com/problems/merge-intervals
 *
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */
#include <stdlib.h>

struct Interval {
    int start;
    int end;
};

typedef struct Interval Interval; 
void sort(struct Interval*, struct Interval*);

struct Interval* merge(struct Interval* intervals, int intervalsSize, int* returnSize) {
    sort(intervals, intervals + intervalsSize);
    Interval* result = (Interval*) malloc(intervalsSize * sizeof(Interval));
    int count = 0;
    Interval latest;
    for(int i = 0 ; i < intervalsSize ; ++ i) {
        if(i == 0) {
            latest = intervals[i];
        } else {
            Interval tmp = intervals[i];
            if(tmp.start <= latest.end) {
                if(tmp.end > latest.end) {
                    latest.end = tmp.end;
                }
            } else {
                result[count ++] = latest;
                latest = intervals[i];
            }
        }
    }
    if(intervalsSize > 0) {
        result[count ++] = latest;
    }
    (*returnSize) = count;
    return result;
}

int comp(struct Interval* left, struct Interval* right) {
    return left->start < right->start ? -1 : 1;
}

void sort(struct Interval* start, struct Interval* end) {
    if(end - start <= 1) {
        return;
    }
    struct Interval* pstart = start;
    struct Interval* pend = end - 1;
    Interval key = (*start);
    do {
        while(pend > pstart && comp(pend, &key) > 0) {
            -- pend;
        }

        (*pstart) = (*pend);
        while(pend > pstart && comp(pstart, &key) < 0) {
            ++ pstart;
        }
        (*pend) = (*pstart);
    } while(pend > pstart);

    (*pstart) = key;

    sort(start, pstart);
    sort(pstart + 1, end);
}

int main(int argc, char **argv) {
    return 0;
}
