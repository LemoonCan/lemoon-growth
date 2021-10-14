package algorithm.doublepointer;

/**
 * 头结点是第一个结点
 * 移除从后往前数第n个结点
 *
 * 示例：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * @author lee
 * @date 2021/10/14
 */
public class RemoveNthFromEnd {
    /**
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode result = new ListNode();
        result.next = head;
        ListNode fast = result;
        ListNode slow = result;
        int i=0;

        while (i<n+1){
            fast = fast.next;
            i++;
        }
        while (fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        ListNode next = slow.next.next;
        slow.next = next;
        return result.next;
    }

    /**
     * 先入栈再出栈
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        return null;
    }
}

