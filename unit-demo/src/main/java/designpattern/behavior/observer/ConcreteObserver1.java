package designpattern.behavior.observer;

/**
 * @author lee
 * @date 2022/10/12
 */
public class ConcreteObserver1 implements Observer{
    @Override
    public void notify(Message message) {
        System.out.println("One is received..."+message.get());
    }
}
