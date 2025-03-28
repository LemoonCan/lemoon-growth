package algorithm.backtracking;

import java.util.*;

/**
 * 电话号码的字母组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * @author lee
 * @since 2021/12/8
 */
public class LetterCombinations {
    public static void main(String[] args) {
        String digits = "23";
        LetterCombinations lc = new LetterCombinations();
        System.out.println(lc.letterCombinations(digits));
    }

    private Map<Character, char[]> map = new HashMap<Character, char[]>(){{
        put('2', new char[]{'a','b','c'});
        put('3', new char[]{'d','e','f'});
        put('4', new char[]{'g','h','i'});
        put('5', new char[]{'j','k','l'});
        put('6', new char[]{'m','n','o'});
        put('7', new char[]{'p','q','r','s'});
        put('8', new char[]{'t','u','v'});
        put('9', new char[]{'w','x','y','z'});
    }};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits.length()<1){
            return res;
        }
        dfs(digits,0,res,new StringBuilder());
        return res;
    }
    
    void dfs(String digits,int index,List<String> res,StringBuilder item){
        if(index>=digits.length()){
            res.add(item.toString());
            return;
        }

        char[] input = map.get(digits.charAt(index));
        for (int i = 0; i < input.length; i++) {
            item.append(input[i]);
            dfs(digits,index+1,res,item);
            item.deleteCharAt(item.length()-1);
        }
    }
}
