package designpattern.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lee
 * @date 2022/10/12
 */
public class ConcreteSubject implements Subject{
    List<Observer> observers = new ArrayList<>();
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Message message) {
        for (Observer o:observers){
            o.notify(message);
        }
    }
}
