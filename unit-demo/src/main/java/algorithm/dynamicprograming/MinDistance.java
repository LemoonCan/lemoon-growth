package algorithm.dynamicprograming;

/**
 * 两个字符串的删除操作
 * https://leetcode-cn.com/problems/delete-operation-for-two-strings/
 *
 * @author lee
 * @since 2021/12/24
 */
public class MinDistance {
    public static void main(String[] args) {
        MinDistance demo = new MinDistance();
        System.out.println(demo.minDistance("sea", "eat"));
    }

    public int minDistance(String word1, String word2) {
        int dp[][] = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        int max = dp[word1.length()][word2.length()];
        return word1.length() + word2.length() - 2 * max;
    }
}
