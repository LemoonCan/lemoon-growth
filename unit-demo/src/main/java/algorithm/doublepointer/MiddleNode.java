package algorithm.doublepointer;

import algorithm.ListNode;

/**
 * 头结点是第一个结点
 * 找链表的中间结点
 * 示例：
 * 输入：[1,2,3,4,5]
 * 输出：[3,4,5]
 *
 * 输入：[1,2,3,4]
 * 输出：[3,4]
 *
 * @author lee
 * @since 2021/10/14
 */
public class MiddleNode {
    public ListNode middleNode(ListNode head) {
        int count = 0;
        while (head.next!=null){
            count++;
        }
        int middle = (count+1)/2;
        ListNode result = head;
        for (int i = 0; i < middle; i++) {
            result = result.next;
        }
        return result;
    }
    public ListNode middleNode2(ListNode head) {
        ListNode slow = head,fast = head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
