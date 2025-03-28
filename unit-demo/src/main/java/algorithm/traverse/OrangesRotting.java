package algorithm.traverse;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/rotting-oranges/
 * 橘子全部腐烂的最短时间
 * 腐烂橘子四周正常橘子1分钟会腐烂
 *
 * @author lee
 * @since 2021/10/20
 */
public class OrangesRotting {
    public static void main(String[] args) {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(orangesRotting(grid));

        int[][] grid2 = {
                {2, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        System.out.println(orangesRotting2(grid2));
    }

    public static int orangesRotting(int[][] grid) {
        int[][] distance = new int[grid.length][grid[0].length];
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] complete = new boolean[grid.length][grid[0].length];

        int[][] rotate = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                    distance[i][j] = 0;
                    complete[i][j] = true;
                }
            }
        }

        int minute = 0;
        while (!queue.isEmpty()) {
            int[] center = queue.poll();

            for (int i = 0; i < rotate.length; i++) {
                int x = center[0] + rotate[i][0];
                int y = center[1] + rotate[i][1];
                if (x >= 0 && x < grid.length
                        && y >= 0 && y < grid[x].length
                        && !complete[x][y]
                        && grid[x][y] != 0) {
                    distance[x][y] = distance[center[0]][center[1]] + 1;
                    minute = Math.max(minute, distance[x][y]);
                    complete[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
        }

        /**
         * 空架子 返回0
         * 无腐烂橘子 返回0
         * 存在未腐烂的橘子 返回-1
         */
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!complete[i][j] && grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return minute;
    }

    /**
     * 凉凉！
     * @param grid
     * @return
     */
    public static int orangesRotting2(int[][] grid) {
        Integer[][] distance = new Integer[grid.length][grid[0].length];
        boolean[][] complete = new boolean[grid.length][grid[0].length];

        int[][] rotateLU = {{-1, 0}, {0, -1}};
        int[][] rotateRD = {{1, 0}, {0, 1}};

        int minute = 0;
        boolean flag = true;
        int uMargin = 0, dMargin = grid.length, lMargin = 0, rMargin = grid[0].length;
        while (flag) {
            for (int i = uMargin; i < dMargin; i++) {
                for (int j = lMargin; j < rMargin; j++) {
                    if (grid[i][j] == 2) {
                        distance[i][j] = 0;
                        complete[i][j] = true;
                    } else if (grid[i][j] == 1) {
                        for (int k = 0; k < 2; k++) {
                            int x = i + rotateLU[k][0];
                            int y = j + rotateLU[k][1];
                            if (x >= 0 && y >= 0) {
                                if (grid[x][y] != 0 && complete[x][y]) {
                                    int dis = distance[x][y] + 1;
                                    distance[i][j] = distance[i][j] == null ? dis : Math.min(dis, distance[i][j]);
                                    complete[i][j] = true;
                                }
                            }
                        }
                    }
                }
            }

            for (int i = dMargin-1; i >= uMargin; i--) {
                for (int j = rMargin-1; j >= lMargin; j--) {
                    if (grid[i][j] == 1) {
                        for (int k = 0; k < 2; k++) {
                            int x = i + rotateRD[k][0];
                            int y = j + rotateRD[k][1];
                            if (x < grid.length && y < grid[x].length) {
                                if (grid[x][y] != 0 && complete[x][y]) {
                                    int dis = distance[x][y] + 1;
                                    distance[i][j] = distance[i][j] == null ? dis : Math.min(dis, distance[i][j]);
                                    complete[i][j] = true;
                                }
                            }
                        }
                    }
                }
            }

            flag = false;
            for (int i = uMargin; i < dMargin; i++) {
                for (int j = lMargin; j < rMargin; j++) {
                    if (complete[i][j]) {
                        minute = Math.max(minute, distance[i][j]);
                    } else {
                        //周围是0可以结束
                        //0包裹着多个1可以结束 不一定！
                        //0 0 0 0
                        //0 1 1 0
                        //0 0 0 0
                        if (grid[i][j] == 1) {
                            if (rotateAll0(grid, i, j)) {
                                return -1;
                            } else {
                                flag = true;
                                break;
                            }
                        }
                    }
                }
                if(flag){
                    break;
                }
            }
            uMargin++;
            lMargin++;
            dMargin--;
            rMargin--;
        }

        return minute;
    }

    public static Boolean rotateAll0(int[][] mat, int i, int j) {
        //上
        int up = i - 1;
        while (up>=-1) {
            if (mat[up][j] == 0) {
                return false;
            }
            up--;
        }
        //下
        int down = i + 1;
        while(down < mat.length) {
            if ( mat[down][j] != 0) {
                return false;
            }
            down --;
        }
        //右
        int left = j - 1;
        if (left >= 0 && mat[i][left] != 0) {
            return false;
        }
        //右
        int right = j + 1;
        if (right < mat[i].length && mat[i][right] != 0) {
            return false;
        }

        return true;
    }
}
