package designpattern.behavior.interpreter;

/**
 * @author lee
 * @since 2022/10/25
 */
public class NumberExpression implements Expression {
    private long number;

    public NumberExpression(long number) {
        this.number = number;
    }

    @Override
    public long interpret() {
        return this.number;
    }
}
