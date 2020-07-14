package main.javabasic.multithread;

/**
 * @author lee
 * @date 2020-07-13
 */
public class BlockedTest {
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        },"a");

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        },"b");

        a.start();
        b.start();
        //TIMED_WAITING
        System.out.println(a.getName() + ":" + a.getState());
        //BLOCKED
        System.out.println(b.getName() + ":" + b.getState());

        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        },"c");

        Thread d = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        },"d");

        c.start();
        c.join();
        d.start();
        //TERMINATED
        System.out.println(c.getName() + ":" + c.getState());
        //TIMED_WAITING、RUNNABLE、BLOCKED
        System.out.println(d.getName() + ":" + d.getState());
    }

    private static synchronized void testMethod(){
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
