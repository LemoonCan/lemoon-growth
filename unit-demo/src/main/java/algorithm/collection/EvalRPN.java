package algorithm.collection;

import java.util.Stack;

/**
 * 逆波兰表达式求值
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 *
 * @author lee
 * @date 2022/1/20
 */
public class EvalRPN {
    public static void main(String[] args) {
        String[] tokens = {"2","1","+","3","*"};
        EvalRPN demo = new EvalRPN();
        System.out.println(demo.evalRPN(tokens));
        String[] tokens1 = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(demo.evalRPN(tokens1));
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i])) {
                int x = stack.pop();
                int y = stack.pop();
                stack.push(x + y);
            } else if ("-".equals(tokens[i])) {
                int x = stack.pop();
                int y = stack.pop();
                stack.push(y - x);
            } else if ("*".equals(tokens[i])) {
                int x = stack.pop();
                int y = stack.pop();
                stack.push(x * y);
            } else if ("/".equals(tokens[i])) {
                int x = stack.pop();
                int y = stack.pop();
                stack.push(y / x);
            } else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }
}
