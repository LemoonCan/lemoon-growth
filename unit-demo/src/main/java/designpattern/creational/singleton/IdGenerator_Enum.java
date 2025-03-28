package designpattern.creational.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 枚举
 * @author lee
 * @since 2022/10/2
 */
public enum IdGenerator_Enum {
    INSTANCE;
    private AtomicInteger id = new AtomicInteger();

    public Integer getId(){
        return id.getAndIncrement();
    }
}
