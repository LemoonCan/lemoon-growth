package algorithm.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lee
 * @date 2020-08-18
 * <p>
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 */
public class WordBreak {
    public static void main(String[] args) {
        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");

        WordBreak wordBreak = new WordBreak();
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
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak(s, 0, new HashSet<>(wordDict));
    }

    public boolean wordBreak(String s, int begin, HashSet<String> wordDict) {
        if (begin >= s.length()) return true;
        for (int i = begin; i < s.length(); i++) {
            if (wordDict.contains(s.substring(begin, i + 1))) {
                if (wordBreak(s, i + 1, wordDict)) return true;
            }
        }
        return false;
    }

    public boolean wordBreak2(String s, List<String> wordDict){
        boolean[] dep = new boolean[s.length()+1];
        dep[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j=0;j<i;j++){
                if(dep[j]&&wordDict.contains(s.substring(j,i))){
                    dep[i]=true;
                    break;
                }
            }
        }
        return dep[s.length()];
    }

    /**
     * TODO ????
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
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
