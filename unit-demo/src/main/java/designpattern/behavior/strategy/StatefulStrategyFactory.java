package designpattern.behavior.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 有状态的策略
 * 可采取反射创建的方式，这只是无参构造的一个简单示范
 *
 * @author lee
 * @since 2022/10/14
 */
public class StatefulStrategyFactory {
    private static final Map<String, Class<? extends Strategy>> strategys = new HashMap<>();

    static {
        strategys.put("A", ConcreteStrategyA.class);
        strategys.put("B", ConcreteStrategyB.class);
    }

    public static Strategy getStrategy(String type) {
        Class<? extends Strategy> clazz = strategys.get(type);
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
