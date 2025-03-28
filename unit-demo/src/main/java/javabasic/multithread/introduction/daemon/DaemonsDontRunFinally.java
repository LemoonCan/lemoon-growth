package javabasic.multithread.introduction.daemon;

import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @since 2020-11-09
 */
public class DaemonsDontRunFinally{
    public static void main(String[] args) {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }
}
class ADaemon implements Runnable {
    @Override
    public void run() {
        try{
            System.out.println("Starting ADaemon");
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            System.out.println("Exiting via InterruptedException");
        }finally {
            System.out.println("This should always run?");
        }
    }
}
