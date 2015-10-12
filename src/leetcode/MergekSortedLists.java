/**
 * Problem link: https://leetcode.com/problems/merge-k-sorted-lists
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */

import java.util.*;
public class MergekSortedLists {
    public static void main(String[] args) {
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null;
        MinHeap<ListNode> minHeap = new MinHeap<>(1024, new ListNodeComparator());
        int length = lists.length;
        for(int i = 0 ; i < length ; ++ i) {
            if(lists[i] != null) {
                minHeap.insert(lists[i]);
            }
        }

        ListNode result = null;

        while(!minHeap.isEmpty()) {
            ListNode top = minHeap.peek();
            top = minHeap.pop(top.next);
            if(result == null) {
                result = head = top;
            } else {
                head.next = top;
                head = head.next;
            }
        }

        return result;
    }

    class ListNodeComparator implements Comparator<ListNode> {
        public int compare(ListNode o1, ListNode o2) {
            if(o1.val < o2.val) {
                return -1;
            } else if (o1.val == o2.val) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    class MinHeap<T> {
        private Object[] array;
        private int capacity;
        private int size;
        private Comparator<T> comp;

        public MinHeap(int capacity, Comparator<T> comp) {
            array = new Object[capacity];
            this.capacity = capacity;
            this.size = 0;
            this.comp = comp;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void insert(T element) {
            ensure(size + 1);
            array[size] = element;
            siftUp(size);
            ++ size;
        }

        public T peek() {
            return (T)array[0];
        }

        public T pop() {
            T result = (T)array[0];
            array[0] = array[size - 1];
            -- size;
            siftDown(0);
            return result;
        }

        public T pop(T replace) {
            if(replace == null) {
                return pop();
            } else {
                T result = (T)array[0];
                array[0] = replace;
                siftDown(0);
                return result;
            }
        }

        void ensure(int size) {
            while(size >= capacity) {
                Object[] newArray = new Object[capacity + 128];
                System.arraycopy(array, 0, newArray, 0, array.length);
                capacity += 128;
                array = newArray;
            }
        }

        void siftUp(int pos) {
            int child = pos;
            int parent = (child - 1) >>  1;
            while(child > 0) {
                if(comp.compare((T)array[parent], (T)array[child]) > 0) {
                    swap(parent, child);
                } else {
                    return;
                }
                child = parent;
                parent = (child - 1) >> 1;
            }
        }

        void siftDown(int pos) {
            int parent = pos;
            int child = (parent << 1) + 1;

            while(child < size) {
                if(child < size - 1) {
                    child = comp.compare((T)array[child], (T)array[child + 1]) < 0 ? child : child + 1;
                }

                if(comp.compare((T)array[parent], (T)array[child]) > 0) {
                    swap(parent, child);
                } else {
                    return;
                }

                parent = child;
                child = (parent << 1) + 1;
            }
        }

        void swap(int pos1, int pos2) {
            Object tmp = array[pos1];
            array[pos1] = array[pos2];
            array[pos2] = tmp;
        }
    }
}
