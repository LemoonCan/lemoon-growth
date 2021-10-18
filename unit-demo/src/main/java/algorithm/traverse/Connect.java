package algorithm.traverse;

/**
 * 填充每个节点的下一个节点
 * 完全二叉树，填充下一个节点为右侧节点
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 *
 * @author lee
 * @date 2021/10/18
 */
public class Connect {
    /**
     * 深度遍历
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if(root == null){
        }

        if(root.left!=null) {
            root.left.next = root.right;
            connect(root.left);
        }
        if(root.right!=null){
            if(root.next!=null){
                root.right.next = root.next.left;
            }else{
                root.right.next = null;
            }
            connect(root.right);
        }
        connect(root.next);
        return root;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
