package algorithm.traverse;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/01-matrix/
 * 找1离最近0的最短距离
 * @author lee
 * @since 2021/10/19
 */
public class UpdateMatrix {
    public static void main(String[] args) {
        int[][] mat = {{1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {0, 0, 0}};
        int[][] distance = updateMatrix2(mat);
        for (int i = 0; i < distance.length; i++) {
            System.out.println(Arrays.toString(distance[i]));
        }

    }
    //[[0,1,0,1,1],[1,1,0,0,1],[0,0,0,1,0],[1,0,1,1,1],[1,0,0,0,1]]
    //[[1,1,0,0,1,0,0,1,1,0],
    // [1,0,0,1,0,1,1,1,1,1],
    // [1,1,1,0,0,1,1,1,1,0],
    // [0,1,1,1,0,1,1,1,1,1],
    // [0,0,1,1,1,1,1,1,1,0],
    // [1,1,1,1,1,1,0,1,1,1],
    // [0,1,1,1,1,1,1,0,0,1],
    // [1,1,1,1,1,0,0,1,1,1],
    // [0,1,0,1,1,0,1,1,1,1],
    // [1,1,1,0,1,0,1,1,1,1]]


    //[[1,1,1,1,1,0,1,0,1,0,1,0,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1],
    // [1,1,1,0,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,0,1],
    // [1,1,1,1,0,1,0,0,1,1,0,1,1,0,1,1,1,0,1,0,1,0,0,1,0,1,0,1,1,1],
    // [1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,0,0,0,1,0,0,0,0,1,1,1,1,0,0,1],
    // [0,1,0,0,1,0,0,1,1,1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1],
    // [1,0,1,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,1,0,1,1,0,1,0,1,0,0,1,0],[1,1,0,1,1,0,0,0,1,1,0,0,0,1,0,1,1,1,1,1,0,1,0,1,1,0,1,1,1,1],[1,1,1,0,0,0,1,0,0,1,1,1,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,0,1],[0,1,1,0,1,1,1,0,1,0,1,1,0,1,1,1,1,0,1,0,1,1,1,1,1,0,0,1,0,1],[1,1,0,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,0,0,0,0,1,1],[1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,0,1,0,1,1,1,0],[1,1,1,1,0,1,0,0,0,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1],[0,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,0,1,0,0,0,1],[0,1,1,0,0,0,1,1,0,0,0,0,1,1,0,1,1,1,1,1,1,1,0,1,0,0,1,1,1,1],[1,1,1,1,0,0,1,1,1,0,0,1,1,0,1,1,1,0,0,1,1,0,1,0,0,0,0,1,1,1],[1,1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,0,0,0,0,1,1,0,0,1,0,0,0],[1,1,0,1,1,0,0,1,1,0,0,1,0,1,1,1,1,0,1,1,1,0,1,1,0,1,0,1,0,1],[1,0,0,0,1,1,1,0,1,1,1,1,0,0,1,1,1,0,1,1,0,1,0,0,1,1,1,1,1,0],[1,1,0,1,0,1,1,0,0,1,1,0,0,1,0,1,1,1,1,1,1,0,1,0,0,0,0,1,1,1],[1,1,1,0,1,0,1,0,1,0,1,0,1,1,0,1,0,0,1,1,1,0,1,1,0,1,0,1,1,0],[1,0,0,1,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,1],[0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,0,1,0,1,1,0,1,1,0,1,0,1],[1,1,1,0,1,1,1,0,0,1,0,0,0,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1],[1,1,0,0,1,1,1,1,0,0,1,0,0,1,1,0,0,1,1,1,1,0,1,1,0,1,1,1,1,1],[0,0,0,1,1,1,1,1,1,0,1,1,1,0,0,1,1,1,1,1,1,1,0,1,0,0,0,1,1,0],[1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,0,1,1,1,1,0,1,0,0,0,0,0,1,1,1],[1,0,1,1,0,1,1,0,1,0,1,1,1,0,1,1,1,1,1,0,1,0,0,1,1,0,0,1,1,0],[1,1,1,0,0,1,1,1,1,0,1,0,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,0,1,1,1],[0,1,1,0,1,1,0,0,1,0,1,1,1,1,0,0,1,1,1,1,1,1,0,0,0,1,1,0,1,0]]

    //从1开始扩散的错误答案
    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    Integer min = mat.length + mat[0].length;
                    queue.offer(new int[]{i, j});

                    int accumulate = 0;
                    while (!queue.isEmpty()) {
                        accumulate++;
                        int size = queue.size();
                        for (int k = 0; k < size; k++) {
                            int[] center = queue.poll();
                            min = upAndLeftMinCount(mat, center[0], center[1], accumulate, min);
                            if (min <= accumulate) {
                                queue.clear();
                                break;
                            }
                            if (rotate0(mat, center[0], center[1])) {
                                min = accumulate;
                                queue.clear();
                                break;
                            }
                            offerDownAndRight(mat, queue, center[0], center[1]);
                        }
                    }

                    mat[i][j] = min;
                }
            }
        }
        return mat;
    }

    public static Integer upAndLeftMinCount(int[][] mat, int i, int j, int accumalate, int min) {
        //上
        int up = i - 1;
        if (up >= 0) {
            min = Math.min(mat[up][j] + accumalate, min);
        }
        //左
        int left = j - 1;
        if (left >= 0) {
            min = Math.min(mat[i][left] + accumalate, min);
        }
        return min;
    }

    public static Boolean rotate0(int[][] mat, int i, int j) {
        //下
        int down = i + 1;
        if (down < mat.length && mat[down][j] == 0) {
            return true;
        }
        //右
        int right = j + 1;
        if (right < mat[i].length && mat[i][right] == 0) {
            return true;
        }

        return false;
    }


    public static void offerDownAndRight(int[][] mat, Queue<int[]> queue, int i, int j) {
        //下
        int down = i + 1;
        if (down < mat.length) {
            queue.offer(new int[]{down, j});
        }
        //右
        int right = j + 1;
        if (right < mat[i].length) {
            queue.offer(new int[]{i, right});
        }
    }

    /**
     * 从0出发 广度优先搜索
     * @param mat
     * @return
     */
    public static int[][] updateMatrix2(int[][] mat) {
        int[][] distance = new int[mat.length][mat[0].length];
        boolean[][] complete = new boolean[mat.length][mat[0].length];
        Queue<int[]> traverse = new LinkedList<>();

        int[][] rotate = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    distance[i][j] = 0;
                    traverse.offer(new int[]{i, j});
                    complete[i][j] = true;
                }
            }
        }

        while (!traverse.isEmpty()) {
            int[] center = traverse.poll();
            for (int i = 0; i < 4; i++) {
                int x = center[0] + rotate[i][0];
                int y = center[1] + rotate[i][1];
                if (x >= 0 && x < mat.length
                        && y >= 0 && y < mat[x].length
                        && !complete[x][y]) {
                    distance[x][y] = distance[center[0]][center[1]]+1;
                    complete[x][y] = true;
                    traverse.offer(new int[]{x,y});
                }
            }
        }

        return distance;
    }

    /**
     * 动态规划解题
     * 0的话 f(i,j)=0
     * 1的话 f(i,j)=min{f(i-1,j),f(i+1,j),f(i,j-1),f(i,j+1)}+1
     * 继续求解f(i-1,j),f(i+1,j),f(i,j-1),f(i,j+1)
     */
}
