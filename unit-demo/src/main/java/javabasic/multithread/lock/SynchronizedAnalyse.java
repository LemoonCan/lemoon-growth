package javabasic.multithread.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author lee
 * @date 5/9/21
 */
public class SynchronizedAnalyse {
    public static void main(String[] args) throws InterruptedException {
        Calculate init = new Calculate();
        System.out.println("无锁状态：001 " + ClassLayout.parseInstance(init).toPrintable());

        //延迟开启偏向锁时间 -XX:BiasedLockingStartupDelay 默认 4000
        //是否禁止偏向锁 -XX:-UseBiasedLocking 默认 true
        //默认延迟 4s 开启偏向锁
        Thread.sleep(5000);
        Calculate calculate = new Calculate();
        System.out.println("启用偏向锁状态：101 " + ClassLayout.parseInstance(calculate).toPrintable());

        synchronized (calculate) {
            System.out.println("偏向锁状态(含线程ID)：101 " + ClassLayout.parseInstance(calculate).toPrintable());
        }
        System.out.println("偏向锁释放(含线程ID)：101 " + ClassLayout.parseInstance(calculate).toPrintable());

        new Thread(() -> {
            synchronized (calculate) {
                System.out.println("升级为轻量级锁：00 " + ClassLayout.parseInstance(calculate).toPrintable());
            }
        }).start();

        new Thread(() -> {
            synchronized (calculate) {
                System.out.println("升级为重量级锁：10 " + ClassLayout.parseInstance(calculate).toPrintable());
            }
        }).start();
    }
}

class Calculate {
    private int number = 0;

    public void add() {
        number++;
    }
}