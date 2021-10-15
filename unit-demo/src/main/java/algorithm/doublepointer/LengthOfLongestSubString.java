package algorithm.doublepointer;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * @author lee
 * @date 2021/10/15
 */
public class LengthOfLongestSubString {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));

        s = "bbbbb";
        System.out.println(lengthOfLongestSubstring(s));

        s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));

        s = "tmmzuxt";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = 0, maxLength = 0;
        Map<Character, Integer> traverseChars = new HashMap<>(chars.length);
        while (right < chars.length) {
            if (traverseChars.containsKey(chars[right])) {
                Integer existIndex = traverseChars.get(chars[right]);
                if(existIndex>=left){
                    left = existIndex+1;
                }
//                if (existIndex > left) {
//                    traverseChars.remove(chars[right]);
//                } else {
//                    traverseChars.remove(chars[right]);
//                    left = existIndex + 1;
//                }
            }
//            else {
//                traverseChars.put(chars[right], right);
//            }
            traverseChars.put(chars[right], right);
            int length = ++right - left;
            maxLength = length > maxLength ? length : maxLength;
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring2(String s) {
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int start = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }
        return res;
    }
}
