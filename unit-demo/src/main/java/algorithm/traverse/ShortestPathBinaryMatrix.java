package algorithm.traverse;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二进制矩阵中的最短路径
 * https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/
 *
 * @author lee
 * @since 2021/11/22
 */
public class ShortestPathBinaryMatrix {
    public static void main(String[] args) {
        int[][] grid = {{0, 1, 1, 0, 1}, {0, 1, 0, 1, 0}, {0, 1, 0, 1, 0}, {1, 0, 1, 1, 0}, {1, 1, 1, 1, 0}};
        System.out.println(shortestPathBinaryMatrix(grid));

    }

    /**
     * BFS 8个方向，广度优先搜索
     * @param grid
     * @return
     */
    public static int shortestPathBinaryMatrix(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int length = grid.length;
        if (grid[0][0] != 0 || grid[length - 1][length - 1] != 0) {
            return -1;
        }

        if (length == 1) {
            return 1;
        }

        queue.offer(new int[]{0, 0});
        grid[0][0] = 1;
        int count = 1;

        int[][] rotate = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {0, 1}, {1, 1}, {1, 0}, {-1, 1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int[] item = queue.poll();
                for (int i = 0; i < rotate.length; i++) {
                    int x = item[0] + rotate[i][0];
                    int y = item[1] + rotate[i][1];
                    if (x < 0 || x >= length || y < 0 || y >= length) {
                        continue;
                    }

                    if (grid[x][y] == 0) {
                        if (x == length - 1 && y == length - 1) {
                            return ++count;
                        } else {
                            //(x,y)点如果是必经点，无论哪路过来的都没关系，可以设置为已经走过
                            queue.offer(new int[]{x, y});
                            grid[x][y] = 1;
                        }
                    }
                }
                count++;
            }
        }

        return -1;
    }
}
