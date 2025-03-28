package javabasic.dynamic.agent.application.aop;

import javabasic.dynamic.annotation.application.inject.ServiceA;
import javabasic.dynamic.annotation.application.inject.ServiceB;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lee
 * @since 6/6/21
 */
@Aspect({ServiceA.class, ServiceB.class})
public class ServiceLogAspect {
    static void before(Object obj, Method method, Object[] args) {
        System.out.println("entering " + method.getDeclaringClass().getSimpleName() +
                "::" + method.getName() +
                ",args:" + Arrays.toString(args));
    }
    static void after(Object obj, Method method, Object[] args,Object result) {
        System.out.println("leaving " + method.getDeclaringClass().getSimpleName() +
                "::" + method.getName() +
                ",result:" + result);
    }
}
