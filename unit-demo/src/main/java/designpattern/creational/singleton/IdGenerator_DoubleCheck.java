package designpattern.creational.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 双重检查
 * @author lee
 * @since 2022/10/2
 */
public class IdGenerator_DoubleCheck {
    private volatile IdGenerator_DoubleCheck instance;

    private AtomicInteger id = new AtomicInteger();

    private IdGenerator_DoubleCheck() {
    }

    public IdGenerator_DoubleCheck getInstance(){
        if(instance == null) {
            //类级别的锁
            synchronized (IdGenerator_DoubleCheck.class) {
                if (instance == null) {
                    instance = new IdGenerator_DoubleCheck();
                }
            }
        }
        return instance;
    }

    public Integer getId(){
        return id.getAndIncrement();
    }
}
