package algorithm.traverse;

/**
 * 另一棵树的子树
 * https://leetcode-cn.com/problems/subtree-of-another-tree/
 *
 * @author lee
 * @since 2021/11/19
 */
public class SubTree {
    public static void main(String[] args) {

    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return dfs(root, subRoot);
    }

    Boolean dfs(TreeNode s, TreeNode t) {
        return check(s, t) || dfs(s.left, t) || dfs(s.right, t);
    }

    Boolean check(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null || s.val != t.val) {
            return false;
        }
        return check(s.left, t.left) && check(s.right, t.right);
    }

    /**
     * 存储两棵树的深度优先遍历顺序
     * 若查询树包含子树的遍历顺序，则认为包含子树
     *
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtree2(TreeNode root, TreeNode subRoot) {
        return false;
    }
}
