package algorithm.collection.linkedlist;

/**
 * 复制带随机指针的链表
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 *
 * @author lee
 * @date 2022/1/21
 */
public class CopyRandomList {
    public static void main(String[] args) {
        Integer[][] list = {{7, null}, {13, 0}, {11, 4}, {10, 2}, {1, 0}};
        CopyRandomList demo = new CopyRandomList();
        Node head = demo.contructLinkedList(list);
        Node newHead = demo.copyRandomList(head);
        Node cur = newHead;
        while (cur != null) {
            System.out.println(cur.val + "," + (cur.random == null ? null : cur.random.val));
            cur = cur.next;
        }
    }

    public Node contructLinkedList(Integer[][] list) {
        Node[] nodes = new Node[list.length];
        for (int i = 0; i < list.length; i++) {
            nodes[i] = new Node(list[i][0]);
        }
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i][1] != null) {
                nodes[i].random = nodes[list[i][1]];
            }
            nodes[i].next = nodes[i + 1];
        }
        if (list[list.length - 1][1] != null) {
            nodes[list.length - 1].random = nodes[list[list.length - 1][1]];
        }
        return nodes[0];
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node cur = head;
        while (cur != null) {
            Node curCopy = new Node(cur.val);
            curCopy.random = cur.random;

            Node next = cur.next;
            cur.next = curCopy;
            curCopy.next = next;
            cur = next;
        }
        cur = head.next;
        Node headNew = cur;
        while (cur != null) {
            if (cur.random != null) {
                cur.random = cur.random.next;
            }
            cur = cur.next;
            if (cur != null) {
                cur = cur.next;
            }
        }
        cur = head;
        while (cur != null) {
            Node change = cur.next;
            if (change != null) {
                cur.next = change.next;
                if (change.next != null) {
                    change.next = change.next.next;
                }
            }
            cur = cur.next;
        }
        return headNew;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
