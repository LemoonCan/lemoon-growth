package javabasic.multithread.communication.newcomponent;

import main.javabasic.generics.BasicGenerator;
import main.javabasic.generics.Generator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @date 2020-12-10
 * Exchanger#exchange 等待其他线程到达交换点
 * 可交换资源的信号 (来一次，往一次，可交换资源)
 * TODO 既然有同步队列，那么Exchanger存在的意义？？？
 */
public class ExchangerDemo {
    static int size = 10;
    static int delay = 1;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> xc = new Exchanger<>();
        List<Fat> producerList = new CopyOnWriteArrayList<>(),
                consumerList = new CopyOnWriteArrayList<>();
        exec.execute(new ExchangerProducer<>(BasicGenerator.create(Fat.class), xc, producerList));
        exec.execute(new ExchangerConsumer<>(xc, consumerList));
        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }
}

class ExchangerProducer<T> implements Runnable {
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;

    public ExchangerProducer(Generator<T> generator, Exchanger<List<T>> exchanger, List<T> holder) {
        this.generator = generator;
        this.exchanger = exchanger;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("producer holder add before" + holder);
                for (int i = 0; i < ExchangerDemo.size; i++) {
                    holder.add(generator.next());
                }
                System.out.println("producer holder add after" + holder);
                holder = exchanger.exchange(holder);
            }
        } catch (InterruptedException e) {
        }
    }
}

class ExchangerConsumer<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;

    public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder) {
        this.exchanger = exchanger;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("consumer begin remove");
                holder = exchanger.exchange(holder);
                System.out.println("consumer holder remove before" + holder);
                for (T x : holder) {
                    value = x;
                    holder.remove(x);
                }
                System.out.println("consumer holder remove after" + holder);
            }
        } catch (InterruptedException e) {
        }
        System.out.println("Final value:" + value);
    }
}
