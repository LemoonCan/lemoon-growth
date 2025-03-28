package designpattern.behavior.interpreter;

/**
 * @author lee
 * @since 2022/10/25
 */
public class Demo {
    public static void main(String[] args) {
        ExpressionInterpreter ei = new ExpressionInterpreter();
        System.out.println(ei.interpret("8 5 3 7 - + *"));
    }
}
