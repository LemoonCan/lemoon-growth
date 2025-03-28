package algorithm.traverse;

/**
 * 岛屿的最大面积
 * https://leetcode-cn.com/problems/max-area-of-island/
 * @author lee
 * @since 2021/10/17
 */
public class MaxAreaOfIsland {
    public static void main(String[] args) {
        int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        System.out.println(maxAreaOfIsland(grid));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int max = 0, count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                max = Math.max(max, DFS(grid, count, i, j));
                count = 0;
            }
        }
        return max;
    }

    static int DFS(int[][] grid, int count, int x, int y) {
        if (grid[x][y] == 1) {
            count++;
            grid[x][y] = 2;
            //上
            if (x - 1 >= 0) {
                count = DFS(grid, count, x - 1, y);
            }
            //下
            if (x + 1 < grid.length) {
                count = DFS(grid, count, x + 1, y);
            }
            //左
            if (y - 1 >= 0) {
                count = DFS(grid, count, x, y - 1);
            }
            //右
            if (y + 1 < grid[x].length) {
                count = DFS(grid, count, x, y + 1);
            }
        }
        return count;
    }
}
