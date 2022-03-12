package algorithm.collection.tree;

import algorithm.TreeNode;

import java.util.*;

/**
 * 二叉树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * @author lee
 * @date 2022/2/16
 */
public class LowestCommonAncestor {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(37);
        TreeNode t1 = new TreeNode(-34);
        TreeNode t2 = new TreeNode(-48);
        root.left = t1;
        root.right = t2;

        TreeNode t3 = new TreeNode(100);
        TreeNode t4 = new TreeNode(-101);
        TreeNode t5 = new TreeNode(48);
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;

        TreeNode t6 = new TreeNode(-54);
        TreeNode t7 = new TreeNode(-71);
        TreeNode t8 = new TreeNode(-22);
        t5.left = t6;
        t6.left = t7;
        t6.right = t8;

        TreeNode t9 = new TreeNode(8);
        t8.right = t9;

        LowestCommonAncestor ancestor = new LowestCommonAncestor();
        System.out.println(ancestor.lowestCommonAncestor(root, t4, t7).val);
        System.out.println(ancestor.lowestCommonAncestor1(root, t4, t7).val);
    }

    /**
     * 1
     * 2           3
     * 4    5      6      7
     * 8- 9 10 11  12 13 14  15
     * <p>
     * (left)
     * (right)
     * cur=p||cur=q parent=
     *
     * 后序遍历找公共父节点
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode parent = null;
        int count = 0;
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            //当前节点存在左节点，则继续入栈
            if (cur.left != null) {
                stack.push(cur.left);
                continue;
            }

            //连续出栈，直到当前节点有右节点且未被访问为止，将右节点入栈
            while (!stack.isEmpty()) {
                cur = stack.peek();
                if (prev != cur.right && cur.right != null) {
                    stack.push(cur.right);
                    break;
                }

                cur = stack.pop();
                prev = cur;
                //记录最近可能的父节点
                if (cur == p || cur == q) {
                    count++;
                    if (count == 2) {
                        return parent;
                    }
                    parent = stack.peek();
                } else {
                    if (cur == parent) {
                        parent = stack.peek();
                    }
                }
            }
        }
        return null;
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }
}
