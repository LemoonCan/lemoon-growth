package main.javabasic.multithread.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @date 2020-12-06
 */
public class Philosopher implements Runnable {
    private ChopStick left;
    private ChopStick right;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);

    public Philosopher(ChopStick left, ChopStick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor));
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " " + "thinking");
                pause();
                this.right.take();
                System.out.println(this + " " + "grabbing right");
                this.left.take();
                System.out.println(this + " " + "grabbing left");
                System.out.println(this + " " + "eating");
                pause();
                this.right.drop();
                this.left.drop();
            }
        } catch (InterruptedException e) {
            System.out.println(this + " " + "exiting via interrupt");
        }
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}
