package javabasic.dynamic.agent.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lee
 * @since 5/28/21
 *
 * vm options:-Dsun.misc.ProxyGenerator.saveGeneratedFiles=true 可保存生成的代理类
 */
public class SimpleJdkDemo {
    public static void main(String[] args) {
        InvocationHandler handler = new MyInvocationHandler(new RealSubject());

        //JDK提供的动态代理方法
        Subject subject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, handler);
        System.out.println(subject.say("Moon", 0));

        //JDK提供的动态代理方法 相当于JVM提供了SubjectDynamicProxy，say方法内部调用InvocationHandler#invoke()
        SubjectDynamicProxy subjectDynamicProxy = new SubjectDynamicProxy(handler);
        System.out.println(subjectDynamicProxy.say("Moon",1));
    }
}

class MyInvocationHandler implements InvocationHandler{
    private Subject realObject;

    public MyInvocationHandler(Subject subject) {
        this.realObject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Entering agent "+method.getName());
        return method.invoke(realObject,args);
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

interface Subject {
    /**
     * 介绍
     *
     * @param name
     * @param age
     * @return
     */
    String say(String name, int age);
}

class RealSubject implements Subject {
    @Override
    public String say(String name, int age) {
        return name + " " + age;
    }
}