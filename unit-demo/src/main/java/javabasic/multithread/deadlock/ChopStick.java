package javabasic.multithread.deadlock;

/**
 * @author lee
 * @since 2020-12-06
 */
public class ChopStick {
    private boolean taken = false;

    public synchronized void take() throws InterruptedException{
        while (taken){
            wait();
        }
        taken = true;
    }

    public synchronized void drop() throws InterruptedException{
        taken = false;
        notifyAll();
    }
}
