package algorithm.dynamicprograming;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lee
 * @date 2020-06-12
 *
 * 至少有K个重复字符的最长子串
 * 1.子串所有字符出现次数>=K
 * 2.求最长的子串长度
 *
 * https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/
 *
 */
public class LongestSubstring {
    public static void main(String[] args) {
        //adbbbacaccdadadae 3
        //bbaaacbd 3
        //ababbc 2
        //bbaaacbd 3
        LongestSubstring ls = new LongestSubstring();
        System.out.println("最长串大小：" + ls.longestSubString("adbbbacaccdadadae", 3));
        System.out.println("最长串大小：" + ls.longestSubString("bbaaacbd", 3));
        System.out.println("最长串大小：" + ls.longestSubString("ababbc", 2));
        System.out.println("最长串大小：" + ls.longestSubString("bbaaacbd", 3));
        System.out.println("最长串大小：" + ls.longestSubString("aaaaaaaaaaaabcdefghijklmnopqrstuvwzyz", 3));
    }

    /**
     * 等价于遍历字符串，找出所有出现次数< K的字符集合
     * 出现次数< K的字符(下标mid)必然不在子串范围内
     * 所以变成递归求mid左边字符串最长子串与mid右边字符串最长子串 相比较的最大值
     * 直到无次数< K的字符，此子串是至少有K个字符的子串
     *
     * 最坏时间复杂度：O(n^2)
     * 空间复杂度：O(26n)
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubString(String s, int k) {
        return longestSubString(s, k, 0, s.length() - 1);
    }

    int longestSubString(String s, int k, int l, int r) {
        if (r - l + 1 < k) {
            return 0;
        }
        int[] count = new int[26];
        for (int i = l; i <= r; i++) {
            count[s.charAt(i) - 'a'] += 1;
        }

        Set<Character> less = new HashSet<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0 && count[i] < k) {
                less.add((char) (i + 'a'));
            }
        }

        if (less.isEmpty()) {
            return r - l + 1;
        } else {
            int max = 0;
            for (int i = l; i <= r; i++) {
                if (less.contains(s.charAt(i))) {
                    max = Math.max(
                            longestSubString(s, k, l, i - 1),
                            longestSubString(s, k, i + 1, r)
                    );
                    break;
                }
            }
            return max;
        }
    }

    /**
     * 找出小于k的元素，做分隔
     *
     * bbcadeadebadecfff 3
     * c=2
     * 所以分隔成 bb | adeadebade | fff
     * 再对分隔后的数据分别找出小于k的元素做分隔
     * 直到子串中的元素都满足>=k位置
     *
     * 时间复杂度：O(n) 26*n*3
     * 空间复杂度：O(26^2)
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring2(String s, int k) {
        int n = s.length();
        return dfs(s, 0, n -1, k);
    }
    public int dfs(String s, int l, int r, int k){
        if(r-l+1<k){
            return 0;
        }
        int[] cnt = new int[26];
        for(int i = l; i <= r; i++){
            cnt[s.charAt(i) - 'a']++;
        }
        char split = 0;
        for(int i = 0; i < 26; i++){
            if(cnt[i] > 0 && cnt[i] < k){
                split = (char)(i + 'a');
                break;
            }
        }
        if(split == 0){
            return r - l + 1;
        }
        int i = l;
        int ret = 0;
        while(i <= r){
            while(i <= r && s.charAt(i) == split){
                i++;
            }
            if(i > r){
                break;
            }
            int start = i;
            while(i <= r && s.charAt(i) != split){
                i++;
            }
            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }
        return ret;
    }
}
