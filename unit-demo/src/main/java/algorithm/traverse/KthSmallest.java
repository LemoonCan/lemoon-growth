package algorithm.traverse;

import java.util.LinkedList;
import java.util.List;

/**
 * 有序矩阵中的第K小元素
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
 *
 * @author lee
 * @since 2022/1/13
 */
public class KthSmallest {
    /**
     * 广度遍历
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int cs = matrix.length;
        int rs = matrix[0].length;
        if (k == cs * rs) {
            return matrix[cs - 1][rs - 1];
        }

        boolean[][] see = new boolean[matrix.length][matrix[0].length];
        List<int[]> queue = new LinkedList<>();
        int count = 0;
        int[] select = new int[]{0, 0};
        queue.add(new int[]{0, 0});
        see[select[0]][select[1]] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int index = 0;
            int[] temp = queue.get(0);
            int min = matrix[temp[0]][temp[1]];
            for (int i = 1; i < size; i++) {
                temp = queue.get(i);
                if (matrix[temp[0]][temp[1]] < min) {
                    min = matrix[temp[0]][temp[1]];
                    index = i;
                }
            }

            select = queue.remove(index);
            count++;
            if (count == k) {
                return matrix[select[0]][select[1]];
            }
            int[] down = new int[]{select[0] + 1, select[1]};
            if (down[0] < cs && !see[down[0]][down[1]]) {
                queue.add(down);
                see[down[0]][down[1]] = true;
            }
            int[] right = new int[]{select[0], select[1] + 1};
            if (right[1] < rs && !see[right[0]][right[1]]) {
                queue.add(right);
                see[right[0]][right[1]] = true;
            }
        }
        return matrix[select[0]][select[1]];
    }

    /**
     * 二分查找
     */
}
