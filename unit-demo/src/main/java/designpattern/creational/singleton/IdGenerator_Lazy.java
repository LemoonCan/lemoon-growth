package designpattern.creational.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 懒汉式
 * @author lee
 * @since 2022/10/2
 */
public class IdGenerator_Lazy {
    private IdGenerator_Lazy instance;

    private AtomicInteger id = new AtomicInteger();

    private IdGenerator_Lazy() {
    }

    public synchronized IdGenerator_Lazy getInstance(){
        if(instance == null){
            instance = new IdGenerator_Lazy();
        }
        return instance;
    }

    public Integer getId(){
        return id.getAndIncrement();
    }
}
