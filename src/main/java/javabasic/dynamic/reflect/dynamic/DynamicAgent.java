package javabasic.dynamic.reflect.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lee
 * @date 5/28/21
 */
public class DynamicAgent {
    public static void main(String[] args) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return args[0] + " " + args[1];
            }
        };

        //JDK提供的动态代理方法
        Subject subject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, handler);
        System.out.println(subject.say("Moon", 0));

        //JDK提供的动态代理方法 相当于 JVM提供了SubjectDynamicProxy
        SubjectDynamicProxy subjectDynamicProxy = new SubjectDynamicProxy(handler);
        System.out.println(subjectDynamicProxy.say("Moon",1));
    }
}

class SubjectDynamicProxy implements Subject {
    InvocationHandler handler;

    public SubjectDynamicProxy(InvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public String say(String name, int age) {
        //调用含基本类型参数的方法，类型可以填Integer.TYPE 或 int.class
        try {
            return (String) handler.invoke(this,
                    Subject.class.getMethod("say", String.class, Integer.TYPE),
                    new Object[]{name, age}
            );
        } catch (Throwable throwable) {
            System.err.println(throwable);
            throw new RuntimeException();
        }
    }
}


//public class HelloDynamicProxy implements Hello {
//    InvocationHandler handler;
//    public HelloDynamicProxy(InvocationHandler handler) {
//        this.handler = handler;
//    }
//    public void morning(String name) {
//        handler.invoke(
//                this,
//                Hello.class.getMethod("morning", String.class),
//                new Object[] { name });
//    }
//}