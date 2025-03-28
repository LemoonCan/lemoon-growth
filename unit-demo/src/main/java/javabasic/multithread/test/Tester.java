package javabasic.multithread.test;

import javabasic.container.array.RandomGenerator;
import javabasic.dynamic.generics.Generated;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lee
 * @since 12/29/20
 */
public abstract class Tester<C> {
    static int testReps = 10;
    static int testCycles = 1000;
    static int containerSize = 1000;

    /**
     * 容器初始化
     *
     * @return
     */
    abstract C containerInitializer();

    /**
     * 开启读写任务
     */
    abstract void startReaderAndWriters();

    C testContainer;
    String testId;
    int nReaders;
    int nWriters;

    volatile long readResult = 0;
    volatile long readTime = 0;
    volatile long writeTime = 0;

    CountDownLatch endLatch;
    static ExecutorService exec = Executors.newCachedThreadPool();
    Integer[] writeData;

    public Tester(String testId, int nReaders, int nWriters) {
        this.testId = testId + " " + nReaders + "r " + nWriters + "w";
        this.nReaders = nReaders;
        this.nWriters = nWriters;
        //构造写的数据
        this.writeData = Generated.array(Integer.class, new RandomGenerator.Integer(), containerSize);
        for (int i = 0; i < testReps; i++) {
            runTest();
            readTime = 0;
            writeTime = 0;
        }
    }

    void runTest() {
        endLatch = new CountDownLatch(nReaders + nWriters);
        testContainer = containerInitializer();
        startReaderAndWriters();
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            System.out.println("endLatch interrupted");
        }
        System.out.printf("%-27s %14d %14d\n", testId, readTime, writeTime);

        if (readTime != 0 && writeTime != 0) {
            System.out.printf("%-27s %14d\n", "readTime+WriteTime=", readTime + writeTime);
        }
    }

    abstract class TestTask implements Runnable {
        abstract void test();

        abstract void putResults();

        long duration;

        @Override
        public void run() {
            long startTime = System.nanoTime();
            test();
            duration = System.nanoTime() - startTime;
            synchronized (Tester.this) {
                putResults();
            }
            endLatch.countDown();
        }
    }

    public static void initMain(String[] args) {
        if (args.length > 0) {
            testReps = new Integer(args[0]);
        }
        if (args.length > 1) {
            testCycles = new Integer(args[1]);
        }
        if (args.length > 2) {
            containerSize = new Integer(args[2]);
        }

        System.out.printf("%-27s %14s %14s\n", "Type", "Read Time", "Write Time");
    }

}
