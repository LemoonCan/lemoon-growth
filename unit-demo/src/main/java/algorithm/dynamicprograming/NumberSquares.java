package algorithm.dynamicprograming;

/**
 * 完全平方数
 * https://leetcode.cn/problems/perfect-squares/
 *
 * 记录 背包问题：https://mp.weixin.qq.com/s/xmgK7SrTnFIM3Owpk-emmg
 *
 * @author lee
 * @date 2022/7/12
 */
public class NumberSquares {
//    int[] nums = {1, 4, 9, 16, 25, 36, 49, 64, 81, 10 ^ 2};
    //            result[1] = 1;
    //            result[2] = 2;
    //            result[3] = 3;
    //            result[4] = 1;
    //            result[5] = 2;
    //            result[6] = 3;
    //            result[7] = 4;
    //            result[8] = 2;
    //            result[9] = 1;
    //            result[10] = 2;
    //            result[11] = 3;
    //            result[12] = 3;

    public static void main(String[] args) {
        NumberSquares demo = new NumberSquares();
        System.out.println(demo.numSquares(3));
        System.out.println(demo.numSquares(12));
        //81+18 1+2=3
        System.out.println(demo.numSquares(999));
    }

    public int numSquares(int n) {
        // 12、13
        int max = (int) Math.sqrt(n);
        max = max*max;
        if (max == n) {
            return 1;
        } else {
            int[] dp = new int[n + 1];
            dp[1] = 1;
            int cur = 2;
            for (int i = 2; i < dp.length; i++) {
                if (i > cur * cur) {
                    cur++;
                }
                if (i == cur * cur) {
                    dp[i] = 1;
                } else {
                    dp[i] = dp[i - 1] + 1;
                    for (int j = i - 2; j >= i/2; j--) {
                        int sum = dp[j] + dp[i - j];
                        if (sum < dp[i]) {
                            dp[i] = sum;
                        }
                    }
                }
            }
            return dp[n];
        }
    }

    public int numSquares2(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;

            //i=3 j=1 j*j=1 i-j*j=2 minn=2
            //i=3 j=2 j*j=4  f[3]=3

            //i=4 j=1 j*j=1 i-j*j=3 minn=3
            //i=4 j=2 j*j=4 i-j*j=0 minn=1 f[4]=1

            //i=5 j=1 j*j=1 i-j*j=4 minn=1
            //i=5 j=2 j*j=4 i-j*j=1 minn=1

            //i=8 j=1 j*j=1 i-j*j=7 minn=4
            //i=8 j=2 j*j=4 i-j*j=4 minn=1
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            //+1 代表选了某个j*j一次
            f[i] = minn + 1;
        }
        return f[n];
    }
}
