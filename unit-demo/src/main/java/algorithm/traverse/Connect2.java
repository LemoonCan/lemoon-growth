package algorithm.traverse;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
 *
 * @author lee
 * @since 2021/11/18
 */
public class Connect2 {
    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node parent = queue.poll();
            if (parent.left == null && parent.right == null) {
                continue;
            }
            Node current = parent.left;
            if (current != null) {
                queue.add(current);
                if (parent.right != null) {
                    current.next = parent.right;
                }
            }
            if (parent.right != null) {
                current = parent.right;
                queue.add(current);
            }
            while ((parent = parent.next) != null) {
                if (parent.left != null) {
                    current.next = parent.left;
                    break;
                }
                if (parent.right != null) {
                    current.next = parent.right;
                    break;
                }
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        } else if (root.left == null) {
            root.right.next = nextNode(root.next);
        } else if (root.right == null) {
            root.left.next = nextNode(root.next);
        } else {
            root.left.next = root.right;
            root.right.next = nextNode(root.next);
        }
        connect2(root.right);
        connect2(root.left);
        return root;
    }

    private Node nextNode(Node root) {
        if (root == null) {
            return null;
        } else if (root.left != null) {
            return root.left;
        } else if (root.right != null) {
            return root.right;
        } else {
            return nextNode(root.next);
        }
    }
}
