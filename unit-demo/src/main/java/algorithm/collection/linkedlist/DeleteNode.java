package algorithm.collection.linkedlist;

import algorithm.ListNode;

/**
 * 删除链表中的节点
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 *
 * @author lee
 * @since 2022/2/9
 */
public class DeleteNode {
    /**
     *
     * @param node 待删节点
     */
    public void deleteNode(ListNode node) {
        if(node.next==null) return;

        node.val = node.next.val;
        node.next = node.next.next;
    }
}
