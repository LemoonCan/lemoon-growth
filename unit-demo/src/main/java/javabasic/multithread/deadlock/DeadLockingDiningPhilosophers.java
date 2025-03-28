package javabasic.multithread.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lee
 * @since 2020-12-06
 */
public class DeadLockingDiningPhilosophers {
    public static void main(String[] args) throws Exception {
        int ponder = 5;
        if (args.length > 0) {
            ponder = Integer.parseInt(args[0]);
        }
        int size = 5;
        if (args.length > 1) {
            size = Integer.parseInt(args[1]);
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        ChopStick[] sticks = new ChopStick[size];
        for (int i = 0; i < 5; i++) {
            sticks[i] = new ChopStick();
        }
        for (int i = 0; i < 5; i++) {
            exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size], i, ponder));
        }
        System.out.println("Press 'Enter' to quit");
        System.in.read();
        exec.shutdownNow();
    }
}
