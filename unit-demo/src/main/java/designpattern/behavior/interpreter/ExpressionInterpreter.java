package designpattern.behavior.interpreter;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lee
 * @date 2022/10/25
 */
public class ExpressionInterpreter {
    private Deque<Expression> nums = new LinkedList<>();

    public long interpret(String expression) {
        String[] elements = expression.split(" ");
        for (int i = 0; i < (elements.length + 1) / 2; i++) {
            nums.addLast(new NumberExpression(Long.parseLong(elements[i])));
        }

        for (int i = (elements.length + 1) / 2; i < elements.length; i++) {
            Expression x;
            Expression a = nums.poll();
            Expression b = nums.poll();

            if ("+".equals(elements[i])) {
                x = new AdditionExpression(a, b);
            } else if ("-".equals(elements[i])) {
                x = new SubstractExpression(a, b);
            } else if ("*".equals(elements[i])) {
                x = new MultiplicationExpression(a, b);
            } else if ("/".equals(elements[i])) {
                x = new DivisionExpression(a, b);
            }else{
                throw new RuntimeException("运算符错误");
            }
            long res = x.interpret();
            nums.addFirst(new NumberExpression(res));
        }
        return nums.pop().interpret();
    }
}
