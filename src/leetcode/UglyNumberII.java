/**
 * Problem link: https://leetcode.com/problems/ugly-number-ii
 *
 * Write a program to find the n-th ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 *
 * Note that 1 is typically treated as an ugly number.
 */

public class UglyNumberII {
    public static void main(String[] args) {
        UglyNumberII solution = new UglyNumberII();

        assert solution.nthUglyNumber(1) == 1;
        assert solution.nthUglyNumber(4) == 4;
        assert solution.nthUglyNumber(10) == 12;
    }

    public int nthUglyNumber(int n) {
        if(n == 1) {
            return 1;
        }

        UniqueMinHeap heap = new UniqueMinHeap(2 * n);
        heap.insert(1L);
        for(int i = 1 ; i < n ; ++ i) {
            long tmp = heap.pop();
            while(!heap.isEmpty() && heap.peek() == tmp) {
                tmp = heap.pop();
            }

            heap.insert(tmp << 1);
            heap.insert(tmp * 3);
            heap.insert(tmp * 5);
        }

        return (int)heap.peek();
    }

    static class UniqueMinHeap {
        long[] array;
        int size;
        int capacity;
        public UniqueMinHeap() {
            this(2);
        }

        public UniqueMinHeap(int capacity) {
            array = new long[capacity];
            this.capacity = capacity;
            size = 0;
        }

        public void insert(long n) {
            ++ size;
            ensure(size);
            array[size - 1] = n;
            siftUp(size - 1);
        }

        public long peek() {
            return array[0];
        }

        public long pop() {
            long result = array[0];
            array[0] = array[size - 1];
            -- size;
            siftDown(0);
            return result;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        void ensure(int size) {
            while(size >= capacity) {
                long[] newArray = new long[capacity + 128];
                System.arraycopy(array, 0, newArray, 0, array.length);
                array = newArray;
                capacity += 128;
            }
        }

        void siftDown(int pos) {
            int parent = pos;
            int child = (parent << 1) + 1;
            while(child < size) {
                if(child < size - 1) {
                    child = array[child] < array[child + 1] ? child : child + 1;
                }

                if(array[child] < array[parent]) {
                    long tmp = array[child];
                    array[child] = array[parent];
                    array[parent] = tmp;
                    parent = child;
                } else {
                    return;
                }

                child = (parent << 1) + 1;
            }
        }

        void siftUp(int pos) {
            int child = pos;
            int parent = (child - 1) >> 1;
            while(child > 0) {
                if(array[parent] > array[child]) {
                    long tmp = array[child];
                    array[child] = array[parent];
                    array[parent] = tmp;
                    child = parent;
                } else {
                    return;
                }
                parent = (child - 1) >> 1;
            }
        }
    }
}
