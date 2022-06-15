package algorithm.dynamicprograming;

import algorithm.TreeNode;

/**
 * 二叉树的最大路径和
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 *
 * @author lee
 * @date 2022/6/15
 */
public class MaxPathSum {
    public static void main(String[] args) {

    }

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    /**
     * 递归计算
     * Max(左+cur+...parent, 右+cur+...parent, 左+右+cur)
     *
     * @param node
     * @return
     */
    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }
}
