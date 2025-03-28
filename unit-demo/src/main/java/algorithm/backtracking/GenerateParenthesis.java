package algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * @author lee
 * @since 2021/12/9
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        GenerateParenthesis demo = new GenerateParenthesis();
        System.out.println(demo.generateParenthesis(4));
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res,new StringBuilder(),0,0,n);
        return res;
    }

    public void dfs(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            dfs(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            dfs(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
