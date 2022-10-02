package designpattern.creational.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 双重检查
 * @author lee
 * @date 2022/10/2
 */
public class IdGenerator_DubboCheck {
    private volatile IdGenerator_DubboCheck instance;

    private AtomicInteger id = new AtomicInteger();

    private IdGenerator_DubboCheck() {
    }

    public IdGenerator_DubboCheck getInstance(){
        if(instance == null) {
            //类级别的锁
            synchronized (IdGenerator_DubboCheck.class) {
                if (instance == null) {
                    instance = new IdGenerator_DubboCheck();
                }
            }
        }
        return instance;
    }

    public Integer getId(){
        return id.getAndIncrement();
    }
}
