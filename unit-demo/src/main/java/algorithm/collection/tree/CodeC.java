package algorithm.collection.tree;

import algorithm.TreeNode;

import java.util.*;

/**
 * 二叉树的序列化与反序列化
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 *
 * @author lee
 * @date 2022/3/13
 */
public class CodeC {
    public static void main(String[] args) {
        String data = "1,2,3,#,#,4,#,#,6";
        CodeC demo = new CodeC();
        TreeNode root = demo.deserialize(data);
        System.out.println(root.val);

        System.out.println(demo.serialize(root));
    }

    /**
     * Encodes a tree to a single string.
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        return bfs(root);
    }

    private String bfs(TreeNode root) {
        if (root == null) {
            return "#";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        sb.append(root.val);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            appendNode(cur.left, sb, queue);
            appendNode(cur.right, sb, queue);
        }
        return sb.toString();
    }

    private void appendNode(TreeNode node, StringBuilder sb, Queue queue) {
        if (node == null) {
            sb.append(",");
            sb.append("#");
        } else {
            sb.append(",");
            sb.append(node.val);
            queue.offer(node);
        }
    }

    /**
     * Decodes your encoded data to tree.
     *
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        if ("#".equals(data)) {
            return null;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        int end = data.indexOf(",", 0);
        TreeNode root = new TreeNode(Integer.parseInt(data.substring(0, end)));
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            PackageNode left = node(data, end);
            cur.left = left.getNode();
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            end = left.getEndIndex();

            PackageNode right = node(data, end);
            cur.right = right.getNode();
            if (cur.right != null) {
                queue.offer(cur.right);
            }
            end = right.getEndIndex();
        }

        return root;
    }

    private PackageNode node(String data, Integer end) {
        int begin = end + 1;
        if (begin >= data.length()) {
            return new PackageNode(null, begin);
        }
        end = data.indexOf(",", begin);
        if (end < 0) {
            end = data.length();
        }
        String leftVal = data.substring(begin, end);
        if (!"#".equals(leftVal)) {
            return new PackageNode(new TreeNode(Integer.parseInt(leftVal)), end);
        }
        return new PackageNode(null, end);
    }

    class PackageNode {
        private TreeNode node;
        private int endIndex;

        public PackageNode(TreeNode node, int endIndex) {
            this.node = node;
            this.endIndex = endIndex;
        }

        public TreeNode getNode() {
            return node;
        }

        public int getEndIndex() {
            return endIndex;
        }
    }
}
