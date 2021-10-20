package algorithm.traverse;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/rotting-oranges/
 * 橘子全部腐烂的最短时间
 * 腐烂橘子四周正常橘子1分钟会腐烂
 *
 * @author lee
 * @date 2021/10/20
 */
public class OrangesRotting {
    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(grid));
    }

    public static int orangesRotting(int[][] grid) {
        int[][] distance = new int[grid.length][grid[0].length];
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] complete = new boolean[grid.length][grid[0].length];

        int[][] rotate = {{-1,0},{1,0},{0,-1},{0,1}};

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j]==2) {
                    queue.offer(new int[]{i, j});
                    distance[i][j] = 0;
                    complete[i][j] = true;
                }
            }
        }

        int minute = 0;
        while (!queue.isEmpty()){
            int[] center = queue.poll();

            for (int i = 0; i < rotate.length; i++) {
                int x = center[0]+rotate[i][0];
                int y = center[1]+rotate[i][1];
                if(x>=0 && x<grid.length
                        && y>=0 && y<grid[x].length
                        && !complete[x][y]
                        && grid[x][y]!=0){
                    distance[x][y]=distance[center[0]][center[1]]+1;
                    minute = Math.max(minute,distance[x][y]);
                    complete[x][y] = true;
                    queue.offer(new int[]{x,y});
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
                if(!complete[i][j]&&grid[i][j]==1){
                    return -1;
                }
            }
        }
        return minute;
    }
}
