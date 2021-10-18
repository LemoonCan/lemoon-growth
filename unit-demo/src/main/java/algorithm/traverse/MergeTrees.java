package algorithm.traverse;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 合并二叉树
 * 合并相同位置的数据，两侧都有，相加
 * 否则取有数据的一侧
 *
 * 输入:
 *  Tree 1                     Tree 2
 *     1                         2
 *    / \                       / \
 *   3   2                     1   3
 *  /                           \   \
 * 5                             4   7
 * 输出:
 *  合并后的树:
 *     3
 *    / \
 *   4   5
 *  / \   \
 * 5   4   7
 *
 * @author lee
 * @date 2021/10/18
 */
public class MergeTrees {
    public static void main(String[] args) {

    }

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        DFS(root1, root2, null, null);
        return root1;
    }

    /**
     * 深度优先搜索
     *
     * @param root1
     * @param root2
     * @param root1Parent
     * @param left
     */
    static void DFS(TreeNode root1, TreeNode root2, TreeNode root1Parent, Boolean left) {
        if (root1 != null) {
            if (root2 != null) {
                root1.val = root1.val + root2.val;
                DFS(root1.left, root2.left, root1, true);
                DFS(root1.right, root2.right, root1, false);
            }
        } else {
            if (root2 != null) {
                if (left) {
                    root1Parent.left = root2;
                } else {
                    root1Parent.right = root2;
                }
            }
        }
    }

    /**
     * 深度优先搜索(前序遍历)
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode DFS2(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;
    }

    /**
     * 广度优先搜索
     *
     * @param root1
     * @param root2
     */
    static TreeNode BFS(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        Queue<TreeNode> queue1 = new LinkedList<>();
        queue1.add(root1);
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue2.add(root2);
        while (!queue1.isEmpty()) {
            TreeNode r1 = queue1.poll();
            TreeNode r2 = queue2.poll();
            r1.val = r1.val + r2.val;

            if (r1.left == null) {
                r1.left = r2.left;
            } else {
                if (r2.left != null) {
                    queue1.offer(r1.left);
                    queue2.offer(r2.left);
                }
            }

            if (r1.right == null) {
                r1.right = r2.right;
            } else {
                if (r2.right != null) {
                    queue1.offer(r1.left);
                    queue2.offer(r2.left);
                }
            }
        }
        return root1;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}