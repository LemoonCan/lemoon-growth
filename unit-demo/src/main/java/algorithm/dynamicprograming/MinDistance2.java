package algorithm.dynamicprograming;

/**
 * 编辑距离
 * https://leetcode-cn.com/problems/edit-distance/
 *
 * @author lee
 * @date 2021/12/24
 */
public class MinDistance2 {
    public static void main(String[] args) {
        MinDistance2 demo = new MinDistance2();
        System.out.println(demo.minDistance("sea", "eat"));
        System.out.println(demo.minDistance("horse","ros"));
    }


    /**
     * → 插入
     * ↓ 删除
     * ↘ 替换
     *   '' r o s
     * '' 0 1 2 3
     * h  1 1 2 3
     * o  2 2 1 3
     * r  3 2 2 2
     * s  4 3 3 2
     * e  5 4 4 3
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int dp[][] = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < word2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]),dp[i][j])+1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
