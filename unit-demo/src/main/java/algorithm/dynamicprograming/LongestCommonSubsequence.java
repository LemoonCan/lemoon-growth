package algorithm.dynamicprograming;

/**
 * 最长公共子序列
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 *
 * @author lee
 * @since 2021/12/23
 */
public class LongestCommonSubsequence {

    /**
     *
     * dp[i+1][j+1] 表示 text1[0,i] 和 text2[0,j]的最长公共子序列
     * dp[0][y]=0,dp[x][0]=0 无字符自然无公共子串
     *
     * 若text1[i] = text2[j]，则dp[i+1][j+1]=dp[i][j]+1
     * 若text1[i] ！= text2[j]，则dp[i+1][j+1]=max(dp[i+1][j],dp[i][j+1])
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if(text1.charAt(i)==text2.charAt(j)){
                    dp[i+1][j+1]=dp[i][j]+1;
                }else{
                    dp[i+1][j+1]=Math.max(dp[i][j+1],dp[i+1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
