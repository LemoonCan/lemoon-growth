package main.algorithm.dynamicprograming;

/**
 * @author lee
 * @date 2020-06-12
 * <p>
 * 至少有K个重复字符的最长子串
 * 1.子串所有字符出现次数>=K
 * 2.求最长的子串长度
 * <p>
 * 等价于遍历字符串，找出所有出现次数< K的字符集合
 * 出现次数< K的字符(下标mid)必然不在子串范围内
 * 所以变成递归求mid左边字符串最长子串与mid右边字符串最长子串 相比较的最大值
 * 直到无次数< K的字符，此子串是至少有K个字符的子串
 */
public class LongestSubString {
    public static void main(String[] args) {
        //adbbbacaccdadadae 3
        //bbaaacbd 3
        //ababbc 2
        //bbaaacbd 3
        LongestSubString ls = new LongestSubString();
        System.out.println("最长串大小："+ls.longestSubString("adbbbacaccdadadae", 3));
    }

    private final char beginLetter = 'a';

    public int longestSubString(String s, int k) {
        char[] chars = s.toCharArray();
        if (k == 1) return s.length();
        if (k == 0 || k > s.length()) return 0;
        return longestSubCharArray(chars, k, 0, s.length() - 1);
    }

    private int longestSubCharArray(char[] chars, int k, int p, int q) {
        if (p < 0 || q >= chars.length || (q - p + 1) < k) return 0;
        int[] charsCount = new int[26];
        for (int i = p; i <= q; i++) {
            charsCount[chars[i] - beginLetter]++;
        }
        while ((q - p + 1)>=k && charsCount[chars[p] - beginLetter] < k) {
            p++;
        }
        while ((q - p + 1)>=k && charsCount[chars[q] - beginLetter] < k) {
            q--;
        }
        if ((q - p + 1) < k) return 0;

        for (int index = p; index <= q; index++) {
            if (charsCount[chars[index] - beginLetter] < k) {
                return Math.max(longestSubCharArray(chars, k, p, index - 1),
                        longestSubCharArray(chars, k, index + 1, q));
            }
        }
        return q - p + 1;
    }
}
