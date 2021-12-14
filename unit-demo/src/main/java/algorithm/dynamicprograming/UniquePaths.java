package algorithm.dynamicprograming;

/**
 * 不同路径
 * https://leetcode-cn.com/problems/unique-paths/
 *
 * @author lee
 * @date 2021/12/14
 */
public class UniquePaths {
    public static void main(String[] args) {
        UniquePaths demo = new UniquePaths();
        System.out.println(demo.uniquePaths(3, 7));

        System.out.println(demo.uniquePaths2(3, 7));
    }

    public int uniquePaths(int m, int n) {
        return dfs(m, n, 0, 0);
    }

    public int dfs(int m, int n, int x, int y) {
        if (x == m - 1 || y == n - 1) {
            return 1;
        }

        return dfs(m, n, x + 1, y) + dfs(m, n, x, y + 1);
    }

    public int uniquePaths2(int m, int n) {
        int[][] his = new int[m][n];
        return dfs(m, n, 0, 0, his);
    }

    public int dfs(int m, int n, int x, int y, int[][] his) {
        if (x == m - 1 || y == n - 1) {
            return 1;
        }
        if (his[x][y] != 0) {
            return his[x][y];
        }

        his[x][y] = dfs(m, n, x + 1, y, his) + dfs(m, n, x, y + 1, his);
        return his[x][y];
    }

    /**
     *      m-1
     * 组合 Cm+n-2
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths3(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }
}
