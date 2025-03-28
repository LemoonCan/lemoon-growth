package algorithm.collection.linkedlist;

import algorithm.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 相交链表
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * @author lee
 * @since 2022/2/7
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode curA = headA, curB = headB;
        while (curA != null) {
            set.add(curA);
            curA = curA.next;
        }
        while (curB != null) {
            if (set.contains(curB)) return curB;
            curB = curB.next;
        }

        return null;
    }

    /**
     * 两个链表分别有 a、b 个元素；相交的元素一共 c 个(可能为0)
     * 存在：
     * x + c = a
     * y + c = b
     * b>a
     *
     * 则 y-x = b-a
     *
     * 指针A a+b-a = b
     * 指针B b
     * 指针B在A走完a+y-x步时，指针B走完b步，指针B指向链表A的起点，指针A走完链表B的 y-x 个起点；
     * 故到达相交点的距离一定是一致的
     * 指针A再走x y-x+y = y
     * 指针B再走x x
     *
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode curA = headA, curB = headB;
        while (curA != curB) {
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }
        return curA;
    }
}
