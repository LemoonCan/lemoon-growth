package algorithm.dynamicprograming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lee
 * @date 2020-08-18
 *
 * 单词拆分
 * https://leetcode-cn.com/problems/word-break/
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 */
public class WordBreak {
    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();

        String s = "leetcode";
        System.out.println(wordBreak.wordBreak(s, Arrays.asList("leet", "code")));

        s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");

        boolean flag = wordBreak.wordBreak(s, wordDict);
        System.out.println(flag);

        s = "catsandog";
        wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        flag = wordBreak.wordBreak(s, wordDict);
        System.out.println(flag);

        s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        flag = wordBreak.wordBreak2(s, wordDict);
        System.out.println(flag);
    }

    /**
     * aaaa情况超时
     * @param s
     * @param wordDict
     * @return
     */
    int max = 0;
    Set<String> wordSet = new HashSet<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        for (int i = 0; i < wordDict.size(); i++) {
            if (wordDict.get(i).length() > max) {
                max = wordDict.get(i).length();
            }
            wordSet.add(wordDict.get(i));
        }

        return dfs(s, 0);
    }

    public boolean dfs(String s, int index) {
        if (index == s.length()) {
            return true;
        }

        int end = index + max;
        for (int i = index + 1; i <= end && i <= s.length(); i++) {
            if (wordSet.contains(s.substring(index, i))) {
                if (dfs(s, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 动态规划
     * s[i]是否可拆分等价于 s[0~i-x] 及 s[i-x~i]是否可拆分
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        int len = 0;
        for(String str : wordDict){
            len = Math.max(len, str.length());
        }
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if(i - j > len) j = i - len;
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
