package algorithm.collection.linkedlist;

import algorithm.ListNode;

/**
 * 链表排序
 * https://leetcode-cn.com/problems/7WHec2/
 *
 * @author lee
 * @date 2022/1/23
 */
public class SortList {
    /**
     * 插入排序
     * 超出时间
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = head;
        ListNode insert = head.next;
        while (insert != null) {
            ListNode cur = head;
            ListNode temp = insert.next;
            while (cur != insert) {
                if (cur.val >= insert.val) {
                    head = insert;
                    insert.next = cur;
                    prev.next = temp;
                    break;
                }
                ListNode next = cur.next;
                if (next != insert && next.val >= insert.val) {
                    cur.next = insert;
                    insert.next = next;
                    prev.next = temp;
                    break;
                }
                cur = cur.next;
            }
            if (cur == insert) {
                prev = cur;
            }
            insert = temp;
        }
        return head;
    }

    /**
     * 归并排序
     */
    public ListNode sortList2(ListNode head) {
        if (head == null) {
            return head;
        }
        return mergeSort(head);
    }

    ListNode mergeSort(ListNode left) {
        if (left.next == null) {
            return left;
        }

        ListNode slow = left, quick = left.next;
        while (quick != null && quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        ListNode lhead = mergeSort(left);
        ListNode rhead = mergeSort(right);

        ListNode head = new ListNode(), cur = head, l = lhead, r = rhead;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                cur.next = l;
                l = l.next;
            } else {
                cur.next = r;
                r = r.next;
            }
            cur = cur.next;
        }
        if (l != null) {
            cur.next = l;
        } else {
            cur.next = r;
        }
        return head.next;
    }

    /**
     * 快速排序
     * 超出时间
     */
    public ListNode sortList3(ListNode head) {
        ListNode prev = new ListNode();
        prev.next = head;
        quickSort(prev, null);
        return prev.next;
    }

    public void quickSort(ListNode prev, ListNode tail) {
        if (prev.next == tail) {
            return;
        }

        ListNode pivot = prev.next;
        ListNode cur = pivot.next,l = prev, r = pivot;
        pivot.next = tail;
        while (cur != tail) {
            if (cur.val <= pivot.val) {
                //放在pivot左边
                ListNode temp = cur.next;
                l.next = cur;
                cur.next = pivot;

                cur = temp;
                l = l.next;
            } else {
                //放在pivot右边
                ListNode temp = cur.next;
                r.next = cur;
                cur.next = tail;

                cur = temp;
                r = r.next;
            }
        }

        quickSort(prev, pivot);
        quickSort(pivot, tail);
    }

    /**
     * 快速排序判断一下是否有序
     */
}
