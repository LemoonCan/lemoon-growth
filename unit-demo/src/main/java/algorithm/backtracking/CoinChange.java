package algorithm.backtracking;

import java.util.Arrays;

/**
 * 零钱兑换
 * https://leetcode-cn.com/problems/coin-change/
 *
 * @author lee
 * @since 2021/12/26
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        CoinChange demo = new CoinChange();
        System.out.println(demo.coinChange(coins, 11));

        int[] coins2 = {411,412,413,414,415,416,417,418,419,420,421,422};
        System.out.println(demo.coinChange2(coins2, 9864));

    }

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        return dfs(coins, coins.length - 1, amount);
    }

    public int dfs(int[] coins, int index, int amount) {
        if (index < 0 || amount <= 0) {
            return -1;
        }

        int max = amount / coins[index];
        int min = -1;
        for (int i = max; i >= 0; i--) {
            int remain = amount - i * coins[index];
            if (remain == 0) {
                if (min == -1 || i < min) {
                    min = i;
                }
            } else {
                int remainCount = dfs(coins, index - 1, remain);
                if (remainCount != -1) {
                    int count = i + remainCount;
                    if (min == -1 || count < min) {
                        min = count;
                    }
                }
            }
        }

        return min;
    }

    public int coinChange2(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
