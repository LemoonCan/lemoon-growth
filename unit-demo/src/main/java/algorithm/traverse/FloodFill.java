package algorithm.traverse;

import java.util.*;

/**
 * 图像渲染：类似于油漆桶
 * 从初始点开始，上下左右与初始点值相同的都更新为新值，与初始点值不相同的不做更新；
 * 接着再将四个方向上更新过的点按上述重复过程...
 *
 * 输入:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * 输出: [[2,2,2],[2,2,0],[2,0,1]]
 *
 * 解释：
 * 1(0,0) 1(0,1) 1(0,2)
 * 1(1,0) 1(1,1) 0(1,2)
 * 1(2,0) 0(2,1) 1(2,2)
 *
 * 从(1,1)坐标点开始更新值为2
 * 第一步: 找(1,1)上下左右值=1的点(0,1)(1,0) 更新为2
 * 1 2 1
 * 2 2 0
 * 1 0 1
 * 第二步：找(0,1)(1,0)上下左右值=1的点(0,0)(0,2) 更新为2
 * 2 2 2
 * 2 2 0
 * 1 0 1
 *
 *
 *
 * @author lee
 * @date 2021/10/17
 */
public class FloodFill {
    public static void main(String[] args) {
        int[][] image = {{2, 2, 2}, {2, 1, 0}, {2, 0, 1}};
        System.out.println(image.length);
        System.out.println(image[0].length);
        System.out.println(image[1][2]);
        System.out.println(image[1][1]);

        int[][] image2 = {{0, 0, 0}, {0, 1, 1}};
        floodFill(image2, 1, 1, 1);
        for (int i = 0; i < image2.length; i++) {
            System.out.println(Arrays.toString(image2[i]));
        }

        int[][] image3 = {{1,1,1},{1,1,0},{1,0,1}};
        floodFill2(image3, 1, 1, 2);
        for (int i = 0; i < image3.length; i++) {
            System.out.println(Arrays.toString(image3[i]));
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) {
            return image;
        }
        DFS(image, sr, sc, newColor, oldColor);
        return image;
    }

    /**
     * 深度优先搜索
     * 先中间，再找完上，再找完下，再找完左，再找完右
     *
     * 先进后出
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @param oldColor
     */
    private static void DFS(int[][] image, int sr, int sc, int newColor, int oldColor) {
        if (image[sr][sc] == oldColor) {
            image[sr][sc] = newColor;
            ///上
            if (sr > 0) {
                DFS(image, sr - 1, sc, newColor, oldColor);
            }
            //下
            if (sr + 1 < image.length) {
                DFS(image, sr + 1, sc, newColor, oldColor);
            }
            //左
            if (sc > 0) {
                DFS(image, sr, sc - 1, newColor, oldColor);
            }
            //右
            if (sc + 1 < image[sr].length) {
                DFS(image, sr, sc + 1, newColor, oldColor);
            }
        }
    }

    public static int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if(oldColor==newColor){
            return image;
        }
        BFS(image, sr, sc, newColor, oldColor);
        return image;
    }

    /**
     * 广度优先搜索
     * 1.先中间，再找上，再找下，再找左，再找右；
     * 2.从上重复1步骤
     * 3.从下重复1步骤
     * ...
     *
     * 先进先出
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @param oldColor
     */
    private static void BFS(int[][] image, int sr, int sc, int newColor, int oldColor) {
        Queue<int[]> queue = new LinkedList<>();
        int[] xVector = new int[]{-1, 1, 0, 0};
        int[] yVector = new int[]{0, 0, -1, 1};
        if (image[sr][sc] == oldColor) {
            image[sr][sc] = newColor;
            queue.offer(new int[]{sr, sc});
        }

        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = position[0] + xVector[i];
                int y = position[1] + yVector[i];
                if (x >= 0 && x < image.length
                        && y >= 0 && y < image[x].length) {
                    if (image[x][y] == oldColor) {
                        image[x][y] = newColor;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
    }
}
