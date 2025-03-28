package designpattern.behavior.interpreter;

/**
 * @author lee
 * @since 2022/10/25
 */
public class SubstractExpression implements Expression {
    private Expression exp1;
    private Expression exp2;

    public SubstractExpression(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public long interpret() {
        return exp1.interpret() - exp2.interpret();
    }
}
