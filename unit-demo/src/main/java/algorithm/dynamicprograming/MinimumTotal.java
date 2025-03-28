package algorithm.dynamicprograming;

import java.util.List;

/**
 * 三角形最小路径和
 * https://leetcode-cn.com/problems/triangle/
 *
 * @author lee
 * @since 2021/11/2
 */
public class MinimumTotal {
    public static void main(String[] args) {

    }

    public int minimumTotal(List<List<Integer>> triangle) {
        return minimumTotal(triangle, 0, 0, Integer.MAX_VALUE, 0);
    }

    public int minimumTotal(List<List<Integer>> triangle, int i, int j, int minTotal, int sum) {
        if (i == triangle.size()) {
            if (sum < minTotal) {
                minTotal = sum;
                return minTotal;
            }
        }

        sum += triangle.get(i).get(j);
        minTotal = minimumTotal(triangle, i + 1, j, minTotal, sum);
        minTotal = minimumTotal(triangle, i + 1, j + 1, minTotal, sum);
        return minTotal;
    }

    /**
     * f(i,j) 是当前位置到底边的最小距离
     * f(i,j) = min(f(i+1,j),f(i+1,j+1)) + f(i,j)
     *
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int[][] mem = new int[triangle.size()][triangle.size()];
        int i = triangle.size() - 1;
        for (int j = 0; j < triangle.get(i).size(); j++) {
            mem[i][j] = triangle.get(i).get(j);
        }
        for (i--; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                mem[i][j] = Math.min(mem[i + 1][j], mem[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return mem[0][0];
    }

    public int minimumTotal3(List<List<Integer>> triangle) {
        int[] mem = new int[triangle.size()];
        int i = triangle.size() - 1;
        for (int j = 0; j < triangle.get(i).size(); j++) {
            mem[j] = triangle.get(i).get(j);
        }
        for (i--; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                mem[j] = Math.min(mem[j], mem[j + 1]) + triangle.get(i).get(j);
            }
        }
        return mem[0];
    }
}
