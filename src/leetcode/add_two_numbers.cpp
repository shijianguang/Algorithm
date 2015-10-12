/**
 * Problem link: https://leetcode.com/problems/add-two-numbers
 *
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */

#include <iostream>

using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
    public:
        ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
            ListNode* result = NULL;
            ListNode* ptr = result;
            int flag = 0;
            while(l1 != NULL || l2 != NULL || flag != 0) {
                int tmp = (l1 == NULL ? 0 : l1->val) + (l2 == NULL ? 0 : l2->val) + flag;
                if(tmp >= 10) {
                    tmp -= 10;
                    flag = 1;
                } else {
                    flag = 0;
                }

                if(result == NULL) {
                    result = ptr = new ListNode(tmp);
                } else {
                    ptr->next = new ListNode(tmp);
                    ptr = ptr->next;
                }

                l1 = (l1 == NULL ? NULL : l1->next);
                l2 = (l2 == NULL ? NULL : l2->next);
            }

            return result;
        }
};

int main(int argc, char **argv) {
    ListNode *l1_head = new ListNode(0);
    ListNode *l2_head = new ListNode(0);

    Solution solution;
    ListNode *result = solution.addTwoNumbers(l1_head, l2_head);
    while(result != NULL) {
        cout << result->val << " ";
        result = result->next;
    }

    cout << endl;
    return 0;
}
