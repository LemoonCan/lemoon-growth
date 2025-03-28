package designpattern.behavior.strategy.use;

import designpattern.behavior.strategy.StatefulStrategyFactory;
import designpattern.behavior.strategy.StatelessStrategyFactory;
import designpattern.behavior.strategy.Strategy;

/**
 * @author lee
 * @since 2022/10/14
 */
public class RuntimeDetermination {
    public static void main(String[] args) {
        Strategy strategy1 = StatefulStrategyFactory.getStrategy(args[0]);
        strategy1.algorithmInterface();

        Strategy strategy2 = StatelessStrategyFactory.getStrategy(args[1]);
        strategy2.algorithmInterface();
    }
}
