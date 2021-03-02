package main.javabasic.multithread.communication.newcomponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @date 2020-12-07
 * CyclicBarrier 控制触发的次数及到达上限的回调线程
 * CyclicBarrier(触发次数,回调线程)（来多次，往一次并执行相应任务）
 */
public class HorseRace {
    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<>();

    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public HorseRace(int mHorses, final int pause) {
        barrier = new CyclicBarrier(mHorses, () -> {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < FINISH_LINE; i++) {
                s.append("=");
            }
            System.out.println(s);
            for (Horse horse : horses) {
                System.out.println(horse.tracks());
            }
            for (Horse horse : horses) {
                if (horse.getStrides() >= FINISH_LINE) {
                    System.out.println(horse + "won!");
                    exec.shutdownNow();
                    return;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                System.out.println("barrier-action sleep interrupted");
            }
        });

        for (int i = 0; i < mHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorses = 7;
        int pause = 600;
        new HorseRace(nHorses, pause);
    }
}

class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random rand = new Random(47);
    private final CyclicBarrier barrier;

    public Horse(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    /**
     * 跨步
     *
     * @return
     */
    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += rand.nextInt(3);
                }
                barrier.await();
            }
        } catch (InterruptedException e) {

        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Horse " + id + " ";
    }

    /**
     * 轨迹
     *
     * @return
     */
    public String tracks() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            s.append("*");
        }
        s.append(id);
        return s.toString();
    }
}