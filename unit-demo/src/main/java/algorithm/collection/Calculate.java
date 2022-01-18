package algorithm.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 基本计算器2
 * https://leetcode-cn.com/problems/basic-calculator-ii/
 * @author lee
 * @date 2022/1/18
 */
public class Calculate {
    public static void main(String[] args) {
        Calculate demo = new Calculate();
        System.out.println(demo.calculate("3 * 5 + 3 / 2"));
        System.out.println(demo.calculate("13 - 5 + 3 / 2"));
        System.out.println(demo.calculate("13 * 6 - 5 + 3 / 2"));
        System.out.println(demo.calculate("3+2*2"));
        System.out.println(demo.calculate1("3+2*2"));
        System.out.println(demo.calculate1(" 3+5 / 2 "));
        System.out.println(demo.calculate1("1*2-3/4+5*6-7*8+9/10"));
    }

    static final Map<Character, Integer> priority = new HashMap<>();

    {
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);
    }

    public int calculate(String s) {
        Stack<Integer> numberStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int x = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    x = x * 10 + s.charAt(i) - '0';
                    i++;
                }
                if (operatorStack.empty() || priority.get(operatorStack.peek()) != 2) {
                    numberStack.push(x);
                } else {
                    Character operator = operatorStack.pop();
                    if (operator == '*') {
                        x = numberStack.pop() * x;
                    } else {
                        x = numberStack.pop() / x;
                    }
                    numberStack.push(x);
                }
            }
            if (i < s.length() && s.charAt(i) != ' ') {
                if (!operatorStack.empty() && priority.get(operatorStack.peek()).equals(priority.get(s.charAt(i)))) {
                    Character operator = operatorStack.pop();
                    int x = numberStack.pop();
                    int y = numberStack.pop();
                    if (operator == '+') {
                        numberStack.push(x + y);
                    } else {
                        numberStack.push(y - x);
                    }
                }
                operatorStack.push(s.charAt(i));
            }

        }

        while (!operatorStack.isEmpty()) {
            Character operator = operatorStack.pop();
            int x = numberStack.pop();
            int y = numberStack.pop();
            if (operator == '+') {
                numberStack.push(x + y);
            } else {
                numberStack.push(y - x);
            }
        }

        return numberStack.pop();
    }

    /**
     * 前一个运算符优先级>=当前运算符，取数计算，计算所得数再重新压入栈
     * @param s
     * @return
     */
    public int calculate1(String s) {
        Stack<Integer> numberStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int number = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    number = number * 10 + s.charAt(i) - '0';
                    i++;
                }
                numberStack.push(number);
            }
            if (i < s.length() && s.charAt(i) != ' ') {
                while (!operatorStack.empty() && priority.get(operatorStack.peek()) >= priority.get(s.charAt(i))) {
                    cal(numberStack, operatorStack);
                }
                operatorStack.push(s.charAt(i));
            }
        }

        while (!operatorStack.empty()){
            cal(numberStack,operatorStack);
        }
        return numberStack.pop();
    }
    private void cal(Stack<Integer> numberStack,Stack<Character> operatorStack){
        int x = numberStack.pop();
        int y = numberStack.pop();
        char ops = operatorStack.pop();
        if (ops == '*') {
            numberStack.push(x * y);
        } else if (ops == '/') {
            numberStack.push(y / x);
        } else if (ops == '+') {
            numberStack.push(x + y);
        } else {
            numberStack.push(y - x);
        }
    }


    /**
     * 只有 + - * / 特殊处理
     * @param s
     * @return
     */
    public int calculate12(String s) {
        Stack<Integer> stack = new Stack<>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}
