package algorithm.collection.linkedlist;

import algorithm.ListNode;

import java.util.Stack;

/**
 * 回文链表
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 *
 * @author lee
 * @since 2022/2/8
 */
public class IsPalindrome {
    /**
     * 快慢指针加栈
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head.next;
        Stack<Integer> stack = new Stack<>();
        while (fast != null && fast.next != null) {
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            stack.push(slow.val);
        }

        ListNode cur = slow.next;
        while (!stack.isEmpty()) {
            if (cur.val != stack.pop()) {
                return false;
            }
            cur = cur.next;
        }

        return true;
    }

    /**
     * 快慢指针+改变链表前半部分指向(依据回文性质)
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        if (head.next == null) {
            return true;
        }

        ListNode slow = head, fast = head.next, prev = null;
        while (fast != null && fast.next != null) {
            ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
            fast = fast.next.next;
        }

        ListNode right = slow.next;
        ListNode left;
        if (fast != null) {
            left = slow;
            slow.next = prev;
        } else {
            left = prev;
        }

        while (left != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }
}
