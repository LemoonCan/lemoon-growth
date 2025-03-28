package algorithm.doublepointer;

/**
 * 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @author lee
 * @since 2021/12/15
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String s = "babad";
        LongestPalindrome demo = new LongestPalindrome();
        System.out.println(demo.longestPalindrome(s));
    }

    public String longestPalindrome(String s) {
        int start = 0, end = 1;
        for (int i = 0; i < s.length(); i++) {
            int len1 = aroundCenter(s, i, i);
            int len2 = aroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > (end - start)) {
                start = i - (len - 1) / 2;
                end = i + len / 2 + 1;
            }
        }
        return s.substring(start, end);
    }

    public int aroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
