package designpattern.behavior.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 无状态的策略
 * @author lee
 * @date 2022/10/14
 */
public class StatelessStrategyFactory {
    private static final Map<String,Strategy> strategys = new HashMap<>();

    static {
        strategys.put("A",new ConcreteStrategyA());
        strategys.put("B",new ConcreteStrategyB());
    }

    public static Strategy getStrategy(String type){
        return strategys.get(type);
    }
}
