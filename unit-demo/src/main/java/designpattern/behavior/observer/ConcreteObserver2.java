package designpattern.behavior.observer;

/**
 * @author lee
 * @date 2022/10/12
 */
public class ConcreteObserver2 implements Observer{
    @Override
    public void notify(Message message) {
        System.out.println("Two is received..."+message.get());
    }
}
