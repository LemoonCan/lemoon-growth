package designpattern.behavior.strategy;

/**
 * @author lee
 * @date 2022/10/14
 */
public class ConcreteStrategyB implements Strategy{
    @Override
    public void algorithmInterface() {
        System.out.println("B algorithm");
    }
}
