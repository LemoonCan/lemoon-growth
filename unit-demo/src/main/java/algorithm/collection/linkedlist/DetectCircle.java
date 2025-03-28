package algorithm.collection.linkedlist;

import algorithm.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表2
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 * @author lee
 * @since 2022/1/22
 */
public class DetectCircle {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> exist = new HashSet<>();
        ListNode cur = head;
        while (cur!=null){
            if(exist.contains(cur)){
                return cur;
            }
            exist.add(cur);
            cur = cur.next;
        }
        return null;
    }
}
