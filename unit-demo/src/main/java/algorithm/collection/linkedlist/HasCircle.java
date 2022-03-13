package algorithm.collection.linkedlist;

import algorithm.ListNode;

/**
 * 环形链表
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * @author lee
 * @date 2022/1/22
 */
public class HasCircle {
    public boolean hasCycle(ListNode head) {
        if(head==null&&head.next==null){
            return false;
        }

        ListNode slow = head,quick = head.next.next;
        while (slow!=null&&quick!=null){
            if(slow==quick){
                return true;
            }
            slow = slow.next;
            if(quick.next==null){
                return false;
            }
            quick = quick.next.next;
        }
        return false;
    }
}
