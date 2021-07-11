package javabasic.dynamic.annotation.application.inject;

/**
 * @author lee
 * @date 6/6/21
 */
public class Test {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        ServiceA serviceA = SimpleContainer.getInstance(ServiceA.class);
        serviceA.callB();
    }
}
