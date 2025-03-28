package designpattern.creational.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 饿汉式
 * @author lee
 * @since 2022/10/2
 */
public class IdGenerator_Hungry {
    private final IdGenerator_Hungry INSTANCE = new IdGenerator_Hungry();

    private AtomicInteger id = new AtomicInteger();

    private IdGenerator_Hungry() {
    }

    public IdGenerator_Hungry getInstance(){
        return INSTANCE;
    }

    public Integer getId(){
        return id.getAndIncrement();
    }
}
