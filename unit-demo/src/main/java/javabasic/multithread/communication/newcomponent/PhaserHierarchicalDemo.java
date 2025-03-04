package javabasic.multithread.communication.newcomponent;

import java.util.concurrent.Phaser;

/**
 * @author lee
 * @since 2024/9/12
 */
public class PhaserHierarchicalDemo {

    public static void main(String[] args) {
        // 创建一个主Phaser，初始注册一个参与者（主线程）
        Phaser masterPhaser = new Phaser(1);

        // 创建两个子Phaser，每个子Phaser初始注册两个参与者
        Phaser subPhaser1 = new Phaser(masterPhaser, 2);
        Phaser subPhaser2 = new Phaser(masterPhaser, 2);

        // 启动第一个子Phaser的工作线程
        for (int i = 0; i < 2; i++) {
            new Thread(new Worker(subPhaser1, "Group1-Worker" + i)).start();
        }

        // 启动第二个子Phaser的工作线程
        for (int i = 0; i < 2; i++) {
            new Thread(new Worker(subPhaser2, "Group2-Worker" + i)).start();
        }

        // 主线程等待所有子Phaser完成
        masterPhaser.arriveAndAwaitAdvance();

        System.out.println("All groups have finished their tasks.");

        // 主线程注销
        masterPhaser.arriveAndDeregister();
    }

    static class Worker implements Runnable {
        private final Phaser phaser;
        private final String workerName;

        Worker(Phaser phaser, String workerName) {
            this.phaser = phaser;
            this.workerName = workerName;
        }

        @Override
        public void run() {
            System.out.println(workerName + " is working on phase 1");
            phaser.arriveAndAwaitAdvance();  // 完成阶段并等待其他线程

            System.out.println(workerName + " is working on phase 2");
            phaser.arriveAndAwaitAdvance();  // 完成阶段并等待其他线程

            phaser.arriveAndDeregister();  // 完成并注销
        }
    }
}
