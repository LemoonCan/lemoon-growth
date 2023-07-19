package javabasic.dynamic.reflect.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 * @since 2023/4/24
 */
public class RuleFactory {
    private final static Map<String, Class<?>> ruleMap = new HashMap<>(4);

    static {
        ruleMap.put(FileQuantityRule.ID, FileQuantityRule.class);
    }

    public static Rule<AbstractDataBlock<?>> createRule(String identity, Object[] params) {
        Class<?> clazz = ruleMap.get(identity);
        if(clazz == null){
            throw new RuntimeException("rule not found");
        }
        try {
            Constructor<?>[] constructors = clazz.getConstructors();
            //查找匹配的构造函数
            for(Constructor<?> constructor : constructors){
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                if(parameterTypes.length == params.length){
                    boolean match = true;
                    for(int i = 0; i < parameterTypes.length; i++){
                        if(!parameterTypes[i].isAssignableFrom(params[i].getClass())){
                            match = false;
                            break;
                        }
                    }
                    if(match){
                        return (Rule<AbstractDataBlock<?>>) constructor.newInstance(params);
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException();
    }
}
