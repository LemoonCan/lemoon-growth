package algorithm.traverse;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 省份数量
 * https://leetcode-cn.com/problems/number-of-provinces/
 * @author lee
 * @date 2021/11/17
 */
public class FindCircleNum {
    public static void main(String[] args) {
        int[][] isConnected = {{1,0,0},{0,1,0},{0,0,1}};
        FindCircleNum fcn = new FindCircleNum();
        System.out.println(fcn.findCircleNum(isConnected));

        int[][] isConnected2 = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(fcn.findCircleNum(isConnected2));

    }

    public int findCircleNum(int[][] isConnected) {
        int count = 0;
        for (int x = 0; x < isConnected.length; x++) {
            for (int y = 0; y < isConnected[x].length; y++) {
                if(isConnected[x][y]==1) {
                    count++;
                    dfs(isConnected, y, x);
                }
            }
        }
        return count;
    }

    void dfs(int[][] isConnected, int x, int y) {
        if (x >= isConnected.length) {
            return;
        }

        for (int i = 0; i < isConnected[x].length; i++) {
            if (isConnected[x][i] == 1) {
                isConnected[x][i] = 0;
                if (x != i && i != y) {
                    dfs(isConnected, i, 0);
                }
            }
        }
    }

    /**
     * 正方形
     * 若 i,j 相邻，此时第 i、j行都认为是判定过了，后需再看第i,j是否还有其他相邻的；
     * 判定过的整行只需遍历一次，所以第一层遍历无需遍历整个矩阵
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum2(int[][] isConnected) {
        int res = 0;
        boolean[] used = new boolean[isConnected.length];
        for(int i = 0;i < isConnected.length;i++) {
            if(!used[i]) {
                res++;
                used[i] = true;
                dfs2(isConnected, used, i);
            }
        }
        return res;
    }

    private void dfs2(int[][] isConnected, boolean[] used, int city) {
        for(int i = 0;i < isConnected.length;i++) {
            if(!used[i] && isConnected[city][i] == 1) {
                used[i] = true;
                dfs2(isConnected, used, i);
            }
        }
    }

    /**
     * BFS
     * @param isConnected
     * @return
     */
    public int findCircleNum3(int[][] isConnected) {
        int proviences = isConnected.length;
        boolean[] visited = new boolean[proviences];
        int circle = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < proviences; i++){
            if(!visited[i]){
                queue.offer(i);
                while(!queue.isEmpty()){
                    int j = queue.poll();
                    visited[j] = true;
                    for(int k = 0; k < proviences; k++){
                        if(isConnected[j][k] == 1 && !visited[k]){
                            queue.offer(k);
                        }
                    }
                }
                circle++;
            }
        }
        return circle;
    }
}
