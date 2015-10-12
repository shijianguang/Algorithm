/**
 * Problem link: https://leetcode.com/problems/add-two-numbers
 *
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */

public class AddTwoNumbers {
    public static void main(String[] args) {
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = null;
        ListNode result = null;
        boolean flag = false;
        while(l1 != null && l2 != null) {
            int tmp = l1.val + l2.val + (flag ? 1 : 0);
            if(tmp >= 10) {
                tmp -= 10;
                flag = true;
            } else {
                flag = false;
            }

            ListNode node = new ListNode(tmp);
            if(result == null) {
                result = p = node;
            } else {
                p.next = node;
                p = node;
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null) {
            int tmp = l1.val + (flag ? 1 : 0);
            if(tmp >= 10) {
                tmp -= 10;
                flag = true;
            } else {
                flag = false;
            }

            ListNode node = new ListNode(tmp);
            if(result == null) {
                result = p = node;
            } else {
                p.next = node;
                p = node;
            }
            l1 = l1.next;
        }

        while(l2 != null) {
            int tmp = l2.val + (flag ? 1 : 0);
            if(tmp >= 10) {
                tmp -= 10;
                flag = true;
            } else {
                flag = false;
            }

            ListNode node = new ListNode(tmp);
            if(result == null) {
                result = p = node;
            } else {
                p.next = node;
                p = node;
            }

            l2 = l2.next;
        }

        if(flag) {
            p.next = new ListNode(1);
        }

        return result;
    }
}
