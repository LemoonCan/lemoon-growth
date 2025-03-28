package algorithm.sliding;

import java.util.*;

/**
 * 找到字符串中所有字母的异位词
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 *
 * @author lee
 * @since 2021/11/11
 */
public class FindAnagrams {

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        System.out.println(findAnagrams(s, p));

        s = "abaacbabc";
        System.out.println(findAnagrams(s, p));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> pf = new HashMap<>();
        int pl = p.length();
        for (int i = 0; i < pl; i++) {
            Character ele = p.charAt(i);
            if (pf.containsKey(ele)) {
                pf.put(ele, pf.get(ele) + 1);
            } else {
                pf.put(ele, 1);
            }
        }

        int start = 0, end = pl;
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> traverse = new HashMap<>(pf);
        while (end <= s.length()) {
            for (int i = start; i < end; i++) {
                Character ele = s.charAt(i);
                if (traverse.containsKey(ele)) {
                    int count = traverse.get(ele);
                    if (--count == 0) {
                        traverse.remove(ele);
                    } else {
                        traverse.put(ele, count);
                    }
                } else {
                    if (pf.containsKey(ele)) {
                        int x = end-3;
                        start = i+1;
                        int oEnd = end;
                        while (x < oEnd) {
                            Character ele2 = s.charAt(x);
                            if (ele2.equals(ele)) {
                                end++;
                                break;
                            }
                            traverse.put(ele2, traverse.containsKey(ele2) ? traverse.get(ele2) + 1 : 1);
                            x++;
                            end++;
                        }
                    } else {
                        start = i + 1;
                        end = start + pl;
                        traverse = new HashMap<>(pf);
                    }
                    break;
                }
            }
            if (traverse.isEmpty()) {
                int index = end - pl;
                res.add(index);
                traverse.put(s.charAt(index), 1);
                end++;
                start = end - 1;
            }
        }
        return res;
    }


    /**
     * 字符可用26位存储
     * 超过p的同一字符数量或者不是p中的字符，都有sCnt[i]>pCnt[i]，此时就应该移动left使得sCnt[i]>pCnt[i]
     * 检测left -> right的长度 等于 p的长度
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int l1 = s.length(), l2 = p.length();
        if(l1 < l2){
            return res;
        }
        int[] sCnt = new int[26];
        int[] pCnt = new int[26];
        for(int i = 0; i < l2; i++){
            pCnt[p.charAt(i) - 'a'] ++;
        }

        int left = 0;
        for(int right = 0; right < l1; right++){
            int curRight = s.charAt(right) - 'a';
            sCnt[curRight] ++;
            while(sCnt[curRight] > pCnt[curRight]){
                int curLeft = s.charAt(left) - 'a';
                sCnt[curLeft] --;
                left ++;
            }
            if(right - left + 1 == l2){
                res.add(left);
            }
        }
        return res;
    }
}
