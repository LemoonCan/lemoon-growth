package algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 * @author lee
 * @since 2021/10/29
 */
public class ClimbStairs {
    public static void main(String[] args) {
        ClimbStairs cs = new ClimbStairs();
        System.out.println(cs.climbStairs(35));
        System.out.println(cs.climbStairs2(35));
        System.out.println(cs.climbStairs3(35));
        System.out.println(cs.climbStairs4(35));
        System.out.println(cs.climbStairs5(35));
    }

    /**
     * 超出内存限制...😠😔
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        List<Boolean> sort = new ArrayList<>();
        dfs(n, 0, sort, 0);
        return sort.size();
    }

    void dfs(int n, int step, List<Boolean> sort, int sum) {
        sum += step;
        if (sum > n) {
            return;
        }
        if (sum == n) {
            sort.add(true);
        }

        dfs(n, 1, sort, sum);
        dfs(n, 2, sort, sum);
    }

    /**
     * 执行44时 long类型也不够存储被除数了
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int countTwoMax = n / 2;
        int sort = 0;
        for (int countTwo = countTwoMax; countTwo >= 0; countTwo--) {
            int countOne = n - countTwo * 2;
            if (countOne == 0 || countTwo == 0) {
                sort++;
            } else {
                int sum = countOne + countTwo;
                int select = countOne;
                if (countOne > countTwo) {
                    select = countTwo;
                }
                long dividend = 1;
                long divisor = 1;
                for (int j = 0; j < select; j++) {
                    dividend *= (sum - j);
                    divisor *= (select - j);
                }
                sort += dividend / divisor;
            }
        }
        return sort;
    }

    /**
     * f(x) = f(x-1) + f(x-2)
     *
     * 1(1种) (1)
     * 2(2种) (1)(1) (2)
     * 3(3种) 1+2 2+1 f(3)=f(2)+f(1)
     * 4(5种) 2+2 3+1 f(4)=f(3)+f(2)
     *
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs3(n - 1) + climbStairs3(n - 2);
    }

    public int climbStairs4(int n) {
        return climbStairs4(n, new int[n + 1]);
    }

    int climbStairs4(int n, int[] record) {
        if (record[n] != 0) {
            return record[n];
        }
        if (n == 1) {
            record[n] = 1;
            return 1;
        }
        if (n == 2) {
            record[n] = 2;
            return 2;
        }
        record[n] = climbStairs3(n - 1) + climbStairs3(n - 2);
        return record[n];
    }

    /**
     * f(x)=f(x-1)+f(x-2) 转换成动态数组实现
     * @param n
     * @return
     */
    public int climbStairs5(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
