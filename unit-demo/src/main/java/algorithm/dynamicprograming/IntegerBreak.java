package algorithm.dynamicprograming;

/**
 * 整数拆分
 * https://leetcode-cn.com/problems/integer-break/
 *
 * @author lee
 * @since 2021/12/28
 */
public class IntegerBreak {
    public static void main(String[] args) {
        IntegerBreak demo = new IntegerBreak();
        System.out.println(demo.integerBreak(12));
    }

    /**
     * 若拆分成两个
     * S=x(n-x)
     * =-(x^2-nx)
     * =-(x-n/2)^2+n^2/4
     * 所以x=n/2取得最大值
     *
     * 但x还可以再拆分
     * 瞎猫碰到死耗子，解出来了
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int x = i / 2;
            for (int j = x; j >= 1; j--) {
                x = j;
                int y = i - x;
                x = Math.max(x, dp[x]);
                y = Math.max(y, dp[y]);
                int s = x * y;
                if (s > dp[i]) {
                    dp[i] = s;
                } else {
                    break;
                }
            }
        }
        return dp[n];
    }
}
