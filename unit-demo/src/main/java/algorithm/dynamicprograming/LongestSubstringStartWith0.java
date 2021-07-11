package algorithm.dynamicprograming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 * @date 2020-06-03
 * 以0为起始位至少有K个重复字符的最长子串
 * 1.子串起始位下标为0
 * 2.子串所有字符出现次数>=K
 * 3.最长的子串位置
 *
 *
 * 已知关系[字符出现次数>=K] C1;
 * 找到满足C1的首个字符作为当前右侧边界B1；
 * 向左遍历，直到满足题目条件的下标B0为止，B0起始值设为-1；
 * 若字符无法满足C1，则结束；
 * 若所有字符都满足C1，且满足C1的首位下标都<=B1，那么当前字符满足题目条件，并将BO置为当前字符下标；
 * 若存在字符char(下标index)满足C1，有满足C1的首位下标X；若index>X，char满足题目条件，后B0设为index，否则若X>B1，那么当前右侧边界B1变为X；再重复向左遍历步骤；
 * BO+1就是题目要求的子串长度
 *
 * 维护满足C1的集合moreThanMap，并存储首位满足C1的字符及下标;
 * 维护满足C1、当前遍历的基准字符下标curMoreThanIndex;（右侧边界）
 * 维护遍历的左侧边界longestIndex，起始值设为-1；（同时也是最长的子串位置）
 * 从>=K个重复字符的起始位curMoreThanIndex开始，向左遍历，直到longestIndex为止； 当前遍历位为index，当前字符curChar；
 * 若moreThanMap不包含curChar，跳出内部循环；若
 *
 */
public class LongestSubstringStartWith0 {
    public static void main(String[] args) {
        LongestSubstringStartWith0 ls = new LongestSubstringStartWith0();
        //adbbbacaccdadadae 3
        //bbaaacbd 3
        //ababbc 2
        //bbaaacbd 3
        System.out.println("最长子串位置：" + ls.longestSubstring("bbaaacbd", 3));
    }

    public int longestSubstring(String s, int k) {
        if (k == 0 || k == 1) {
            return s.length();
        }
        char[] chars = s.toCharArray();
        //>=K个重复字符的集合及首位
        Map<Character, Integer> moreThanFirstIndexMap = new HashMap<>();

        //字符计数
        Map<Character, Integer> countMap = new HashMap<>();

        //大于K个重复字符的当前访问位
        Integer curMoreThanIndex = -1;

        int longestIndex = -1;
        //取得>=K个重复字符的集合首位
        for (int i = 0; i < chars.length; i++) {
            if (countMap.containsKey(chars[i])) {
                Integer curLength = countMap.get(chars[i]) + 1;
                countMap.put(chars[i], curLength);
                if (curLength >= k) {
                    countMap.remove(chars[i]);
                    moreThanFirstIndexMap.put(chars[i], i);
                    if (moreThanFirstIndexMap.size() == 1) {
                        curMoreThanIndex = i;
                    }
                }
            } else {
                countMap.put(chars[i], 1);
            }
        }
        //记录当前访问的元素
        Integer index = curMoreThanIndex;

        while (index > -1 && index < chars.length && moreThanFirstIndexMap.containsKey(chars[index])) {
            for (; index > longestIndex; index--) {
                if (moreThanFirstIndexMap.containsKey(chars[index])) {
                    Integer curIndexMoreThanFirst = moreThanFirstIndexMap.get(chars[index]);
                    if (index > curIndexMoreThanFirst) {
                        curMoreThanIndex = index;
                    } else {
                        if (curIndexMoreThanFirst > curMoreThanIndex) {
                            curMoreThanIndex = curIndexMoreThanFirst;
                            index = curMoreThanIndex;
                        }
                    }
                } else {
                    break;
                }
            }
            if (index.equals(longestIndex)) {
                longestIndex = curMoreThanIndex;
                index = curMoreThanIndex + 1;
            }
        }

        return longestIndex + 1;
    }
}
