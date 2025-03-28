package algorithm.dynamicprograming;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/letter-case-permutation/
 * 字母大小写全排列
 *
 * @author lee
 * @since 2021/10/28
 */
public class LetterCasePermutation {
    public static void main(String[] args) {
        LetterCasePermutation lc = new LetterCasePermutation();
        System.out.println(lc.letterCasePermutation("a1b2"));
    }

    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        bfs(s,0,res);
        return res;
    }

    /**
     * 大写 65-90
     * 小写 97-122
     *
     * 遇到字母就要变换大小写加入作选择
     *
     * @param s
     * @param begin
     * @param res
     */
    void bfs(String s, int begin, List<String> res) {
        for (int i = begin; i < s.length(); i++) {
            char ch = s.charAt(i);
            StringBuilder sb = new StringBuilder(s);
            if (ch >= 65 && ch <= 90) {
                sb.setCharAt(i,(char)(ch+32));
                bfs(sb.toString(),i+1,res);
            }
            if(ch >= 97 && ch <= 122){
                sb.setCharAt(i,(char)(ch-32));
                bfs(sb.toString(),i+1,res);
            }
        }
        res.add(s);
    }
}
