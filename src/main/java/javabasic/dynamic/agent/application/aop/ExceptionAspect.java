package javabasic.dynamic.agent.application.aop;

import javabasic.dynamic.annotation.application.inject.ServiceB;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lee
 * @date 6/6/21
 */
@Aspect({ServiceB.class})
public class ExceptionAspect {
    static void exception(Object obj, Method method, Object[] args, Throwable e) {
        System.err.println("exception when calling: " + method.getName() +
                "," + Arrays.toString(args));
    }
}
