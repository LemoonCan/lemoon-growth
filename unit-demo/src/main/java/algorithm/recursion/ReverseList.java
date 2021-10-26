package algorithm.recursion;

import algorithm.ListNode;

/**
 * 反转链表
 * @author lee
 * @date 2021/10/25
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    public ListNode reverseList2(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }

        ListNode newHead = reverseList2(head.next);
        head.next.next = head.next;
        head.next = null;
        return newHead;
    }
}
