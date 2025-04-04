package javabasic.dynamic.annotation.application.inject;

/**
 * @author lee
 * @since 6/6/21
 */
public class ServiceA implements InterfaceA{
    @SimpleInject
    ServiceB serviceB;

    @Override
    public void callB(){
        serviceB.action();
    }
}
