package designpattern.behavior.observer;

/**
 * @author lee
 * @since 2022/10/12
 */
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(Message message);
}
