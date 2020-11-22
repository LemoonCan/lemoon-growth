package main.javabasic.multithread.introduction;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lee
 * @date 2020-11-22
 */
public class AtomicIntegerTest implements Runnable{
    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        },5000);
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicIntegerTest ait = new AtomicIntegerTest();
        executorService.execute(ait);
        while (true){
            int val = ait.getValue();
            if(val%2!=0){
                System.out.println(val);
                System.exit(0);
            }
        }
    }

    private AtomicInteger i = new AtomicInteger(0);
    public int getValue(){
        return i.get();
    }

    public void evenIncrement(){
        i.addAndGet(2);
    }

    @Override
    public void run() {
        while(true){
            evenIncrement();
        }
    }
}
