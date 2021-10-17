package algorithm.doublepointer;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的"排列"
 *
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 *
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 *
 * @author lee
 * @date 2021/10/16
 */
public class CheckInclusion {
    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2));

        s1 = "adc";
        s2 = "dcda";
        System.out.println(checkInclusion(s1, s2));
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        HashMap<Character, Integer> c1 = new HashMap<>();
        HashMap<Character, Integer> c2 = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            Character c = s1.charAt(i);
            if (c1.containsKey(c)) {
                c1.put(c, c1.get(c) + 1);
            } else {
                c1.put(c, 1);
            }
        }

        int left = 0, right = s1.length() - 1;
        for (; right < s2.length(); ) {
            for (int i = left; i <= right; i++) {
                Character c = s2.charAt(i);
                if (c2.containsKey(c)) {
                    c2.put(c, c2.get(c) + 1);
                } else {
                    c2.put(c, 1);
                }
            }

            c1.forEach((key, value) -> {
                Integer count = c2.get(key);
                if (value.equals(count)) {
                    c2.remove(key);
                }
            });
            if (c2.isEmpty()) {
                return true;
            } else {
                c2.clear();
            }
            left++;
            right++;
        }
        return false;
    }

    public boolean checkInclusion2(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }
}
