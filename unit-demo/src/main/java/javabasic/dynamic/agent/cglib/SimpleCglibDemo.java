package javabasic.dynamic.agent.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib代理的类需声明为public，方法为final
 *
 * @author lee
 * @since 6/3/21
 */
public class SimpleCglibDemo {
    public static class RealService {
        public void sayHello() {
            System.out.println("say hello");
        }
    }

    public static class SimpleInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("entering " + method.getName());
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("leaving " + method.getName());
            return result;
        }
    }

    private static <T> T getProxy(Class<T> cls) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(new SimpleInterceptor());
        return (T) enhancer.create();
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "unit-demo/target/cglib");
        RealService proxyService = getProxy(RealService.class);
        proxyService.sayHello();
    }
}
