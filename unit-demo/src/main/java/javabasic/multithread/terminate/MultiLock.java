package javabasic.multithread.terminate;

/**
 * @author lee
 * @date 2/25/21
 */
public class MultiLock {
    synchronized void f1(int count) {
        if (count-- > 0) {
            System.out.println("f1 calling f2() with count " + count);
            f2(count);
        }else {
            while (true){
                System.out.println("t1 finish");
            }
        }
    }

    synchronized void f2(int count) {
        if (count-- > 0) {
            System.out.println("f2 calling f1() with count " + count);
            f1(count);
        }else {
            while (true){
                System.out.println("t1 finish");
            }
        }
    }

    synchronized void f3() {
        while(true) {
            System.out.println("calling f3()");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MultiLock multiLock = new MultiLock();
        Thread t1 = new Thread(() ->
            multiLock.f1(10)
        );
        Thread t2 = new Thread(() -> multiLock.f3());
        t1.start();
        t2.start();
        Thread.sleep(10);
    }
}
