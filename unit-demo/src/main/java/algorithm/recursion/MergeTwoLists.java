package algorithm.recursion;

import algorithm.ListNode;

/**
 * 合并有序链表
 * @author lee
 * @date 2021/10/22
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(),hTemp = head;
        while (l1!=null && l2!=null){
            if(l1.val <= l2.val){
                hTemp.next = l1;
                l1 = l1.next;
            }else{
                hTemp.next = l2;
                l2 = l2.next;
            }
            hTemp = hTemp.next;
        }

        if(l1!=null){
            hTemp.next = l1;
        }
        if(l2!=null){
            hTemp.next = l2;
        }

        return head.next;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        if(l1.val<=l2.val){
            l1.next = mergeTwoLists2(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoLists2(l1,l2.next);
            return l2;
        }
    }
}
