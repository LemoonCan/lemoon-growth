package algorithm.collection.tree;

import algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 二叉搜索树中第K小的元素
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 *
 * (搜索树=查找树，当前节点的左节点小于它，右节点大于它)
 *
 * @author lee
 * @date 2022/2/13
 */
public class kthSmallest {
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, (x1, x2) -> x2 - x1);
        dfs(root, heap, k);
        return heap.peek();
    }

    void dfs(TreeNode node, PriorityQueue<Integer> heap, int k) {
        if (node == null) {
            return;
        }

        if (heap.size() < k) {
            heap.offer(node.val);
        } else {
            if (node.val < heap.peek()) {
                heap.poll();
                heap.offer(node.val);
            }
        }

        dfs(node.left, heap, k);
        dfs(node.right, heap, k);
    }

    public int kthSmallest2(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>(k);
        inOrderTraverse(root, list, k);
        return list.get(k-1);
    }

    /**
     * 中序遍历 左、中、右
     * @param node
     * @param list
     * @param k
     */
    void inOrderTraverse(TreeNode node, List<Integer> list, int k) {
        if (node == null) {
            return;
        }
        inOrderTraverse(node.left, list, k);
        if(list.size()>=k) {
            return;
        }
        list.add(node.val);

        inOrderTraverse(node.right, list, k);
    }

}
