package javabasic.dynamic.annotation.application.inject;

/**
 * @author lee
 * @date 6/6/21
 */
@SimpleSingleton
public class ServiceB implements InterfaceB{
    @Override
    public void action(){
        System.out.println("I'm B");
    }
}
