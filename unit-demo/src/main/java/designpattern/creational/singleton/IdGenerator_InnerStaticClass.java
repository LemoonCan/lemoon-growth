package designpattern.creational.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 静态内部类
 *
 * @author lee
 * @date 2022/10/2
 */
public class IdGenerator_InnerStaticClass {
    private AtomicInteger id = new AtomicInteger();

    private static class IdGeneratorHolder {
        private static final IdGenerator_InnerStaticClass instance = new IdGenerator_InnerStaticClass();
    }

    public IdGenerator_InnerStaticClass getInstance() {
        return IdGeneratorHolder.instance;
    }

    public Integer getId() {
        return id.getAndIncrement();
    }
}
