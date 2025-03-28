package designpattern.behavior.observer;

/**
 * @author lee
 * @since 2022/10/12
 */
public class Demo {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer o1 = new ConcreteObserver1();
        Observer o2 = new ConcreteObserver2();
        subject.registerObserver(o1);
        subject.registerObserver(o2);

        subject.notifyObservers(new Message("Hello world~"));
    }
}
