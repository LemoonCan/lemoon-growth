package algorithm.collection;

import algorithm.ListNode;

/**
 * 奇偶链表
 * https://leetcode-cn.com/problems/odd-even-linked-list/
 *
 * @author lee
 * @date 2022/2/9
 */
public class OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        if(head==null||head.next==null||head.next.next==null){ return head;}

        ListNode oddHead = new ListNode();
        ListNode evenHead = new ListNode();
        ListNode cur = head, curOdd = oddHead, curEven = evenHead;
        while (cur != null) {
            curOdd.next = cur;
            curOdd = curOdd.next;

            cur = cur.next;

            curEven.next = cur;
            curEven = curEven.next;

            if (cur != null) {
                cur = cur.next;
            }
        }
        curOdd.next = evenHead.next;
        if (curEven != null) {
            curEven.next = null;
        }
        ListNode newHead = oddHead.next;
        oddHead.next = null;
        evenHead.next = null;

        return newHead;
    }
}
