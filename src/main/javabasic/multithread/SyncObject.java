package main.javabasic.multithread;

/**
 * @author lee
 * @date 2020-11-25
 * synchronized需给定一个进行同步的对象(也就是被锁定的对象，使用相同对象作为锁的synchronized块是互斥执行的)
 * 指定this的话，若获得了synchronized上的锁，当前对象的其他synchronized方法和临界区就不能被调用了
 */
public class SyncObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread(() -> ds.f()).start();
        ds.g();
    }
}

class DualSynch{
    private Object syncObject = new Object();
    public synchronized void f(){
        for (int i = 0; i < 5; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }

    public void g(){
        synchronized (syncObject){
            for (int i = 0; i < 5; i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}
