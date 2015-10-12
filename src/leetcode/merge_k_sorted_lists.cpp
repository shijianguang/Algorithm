/**
 * Problem link: https://leetcode.com/problems/merge-k-sorted-lists
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */

#include <stdio.h>
#include <vector>

using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
    public:
        ListNode* mergeKLists(vector<ListNode*>& lists) {
            if(lists.size() == 0) {
                return NULL;
            }
            init(lists);
            ListNode* result = pop();
            ListNode* iter = result;
            while(iter != NULL) {
                ListNode* node = pop();
                iter->next = node;
                iter = iter->next;
            }

            return result;
        }
    private:
        int* array;
        int array_size;
        vector<ListNode*> iterator;

        void init(vector<ListNode*>& lists) {
            int size = lists.size();
            array = new int[size];
            array_size = size;
            iterator = lists;

            int maxId = 0;
            for(int i = 1 ; i < size ; ++ i) {
                maxId = compare(iterator, maxId, i) < 0 ? maxId : i;
            }

            for(int i = 0 ; i < array_size ; ++ i) {
                array[i] = maxId;
            }

            for(int i = array_size - 1 ; i >= 0 ; -- i) {
                adjust(i);
            }
        }

        ListNode* pop() {
            ListNode* result = getNode(array[0]);
            if(result == NULL) {
                return result;
            }
            adjust(array[0]);
            return result;
        }

        void adjust(int index) {
            int t = (index + array_size) >> 1;
            while(t > 0) {
                if(compare(iterator, array[t], index) < 0) {
                    int tmp = index;
                    index = array[t];
                    array[t] = tmp;
                }

                t = t >> 1;
            }

            array[0] = index;
        }

        int compare(vector<ListNode*>& lists, int index1, int index2) {
            ListNode *node1 = lists[index1];
            ListNode *node2 = lists[index2];
            if(node1 == NULL && node2 == NULL) {
                return index1 < index2 ? -1 : 1;
            } else if (node1 == NULL && node2 != NULL) {
                return 1;
            } else if (node1 != NULL && node2 == NULL) {
                return -1;
            } else {
                int value1 = node1->val;
                int value2 = node2->val;
                if(value1 < value2) {
                    return -1;
                } else if(value1 > value2) {
                    return 1;
                } else {
                    return index1 < index2 ? -1 : 1;
                }
            }
        }

        ListNode* getNode(int index) {
            ListNode *result = iterator[index];
            if(result == NULL) {
                return NULL;
            } else {
                iterator[index] = result->next;
                return result;
            }
        }
};
