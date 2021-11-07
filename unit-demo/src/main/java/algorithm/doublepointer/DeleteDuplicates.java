package algorithm.doublepointer;

import algorithm.ListNode;

/**
 * 删除排序列表中的重复元素
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 * @author lee
 * @date 2021/11/7
 */
public class DeleteDuplicates {
    public static void main(String[] args) {
        //[1,1]
        ListNode head = new ListNode();
        head.val = 1;
        ListNode next = new ListNode();
        next.val = 1;
        head.next = next;

        print(deleteDuplicates(head));
    }

    /**
     * [1,2,3,3,4,4,5]
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode empty = new ListNode();
        empty.next = head;
        ListNode prev = empty, cur = head, possibleDelete = head;
        while (cur != null && (cur = cur.next) != null) {
            if (cur.val == possibleDelete.val) {
                while (cur.next != null && cur.next.val == possibleDelete.val) {
                    cur = cur.next;
                }
                prev.next = cur.next;
                possibleDelete = cur.next;
                cur = cur.next;
            } else {
                prev = prev.next;
                possibleDelete = cur;
            }
        }
        return empty.next;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if(head==null){
            return head;
        }
        ListNode empty = new ListNode();
        empty.next = head;
        ListNode prev = empty, cur = head.next, possibleDelete = head;
        while (cur != null) {
            if (cur.val == possibleDelete.val) {
                cur = cur.next;
                while (cur != null && cur.val == possibleDelete.val) {
                    cur = cur.next;
                }
                prev.next = cur;
                if (cur == null) {
                    break;
                }
            } else {
                prev = prev.next;
            }
            possibleDelete = cur;
            cur = cur.next;
        }
        return empty.next;
    }

    public static void print(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }

        StringBuilder sb = new StringBuilder("[");
        ListNode cur = head;
        while (cur != null) {
            sb.append(cur.val);
            sb.append(",");
            cur = cur.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        System.out.println(sb.toString());
    }
}
