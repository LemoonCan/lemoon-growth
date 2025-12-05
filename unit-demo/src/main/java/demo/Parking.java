package demo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lemoon
 * @since 2025/12/4
 */
public class Parking {
    public static void main(String[] args) {
        int threadCount = 20; // 并发数
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(threadCount);
        char[] chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    // 等待统一开始
                    start.await();
                    int idx = new Random().nextInt(chars.length);
                    testCarPark("粤" + chars[idx] + new Random().nextInt(10000));
                } catch (Exception e) {
                    System.err.println(e);
                } finally {
                    end.countDown();
                }
            }).start();
        }
        start.countDown();
        try {
            end.await();
            System.out.println("所有车辆处理完毕");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testCarPark(String carNo) {
        Parking parking = new Parking();
        parking.in(carNo);
        parking.prepareOut(carNo);
        parking.pay(carNo);
    }

    private final static Map<String, ParkingSpace> park = new HashMap<>(16);
    private final static Map<String, Bill> bills = new HashMap<>(16);
    private final static ParkCapacity capacity = new ParkCapacity();

    /**
     * 车辆进入停车场
     *
     * @param carNo 车牌号
     */
    public void in(String carNo) {
        Transaction.start();
        try {
            ParkingSpace space = new ParkingSpace();
            space.in(carNo);
            park.put(carNo, space);

            int retryTimes = 3;
            while (retryTimes > 0) {
                int[] oldCount = capacity.getCapacity();
                int newCount = capacity.decr(oldCount[0]);
                try {
                    System.out.printf("车辆%s进入停车场%n", carNo);
                    capacity.update(newCount, oldCount);
                    break;
                } catch (InconsistentException e) {
                    retryTimes--;
                    if (retryTimes == 0) {
                        throw new RuntimeException();
                    }
                    System.out.printf("车辆%s进入失败，准备重试，剩余重试次数：%s%n", carNo, retryTimes);
                }
            }
        } catch (Exception e) {
            Transaction.rollback();
            throw new RuntimeException(String.format("车辆%s无法进入停车场", carNo));
        }
        Transaction.commit();
    }

    public void prepareOut(String carNo) {
        Transaction.start();
        try {
            ParkingSpace space = park.get(carNo);
            space.out();
            long fare = space.calFee();
            System.out.println("停车费用：" + fare + "元");
            Bill bill = new Bill(carNo, fare);
            bills.put(carNo, bill);
        } catch (Exception e) {
            Transaction.rollback();
        }
        Transaction.commit();
    }

    public void pay(String carNo) {
        Transaction.start();
        try {
            Bill bill = bills.get(carNo);
            bill.pay();
            Async.submit(() -> out(carNo));
        } catch (Exception e) {
            Transaction.rollback();
        }
        Transaction.commit();
    }

    public void out(String carNo) {
        Transaction.start();
        try {
            Bill bill = bills.get(carNo);
            if (!bill.payed()) {
                throw new RuntimeException("停车费未支付，无法离开停车场");
            }
            bills.remove(carNo);
            park.remove(carNo);
            int[] oldCount = capacity.getCapacity();
            int newCount = capacity.incr(oldCount[0]);
            System.out.printf("车辆%s离开%n", carNo);
            capacity.update(newCount, oldCount);
        } catch (Exception e) {
            Transaction.rollback();
            System.out.printf("车辆%s离开失败%n", carNo);
            throw new RuntimeException("无法离开停车场");
        }
        Transaction.commit();
    }
}

class ParkCapacity {
    private int capacity = 100;
    private int version = 0;

    public synchronized void update(int value, int[] expect) {
        if (capacity != expect[0] || version != expect[1]) {
            throw new InconsistentException("数据不一致");
        }
        capacity = value;
        version++;
        System.out.println("更新停车场容量，当前剩余车位：" + capacity);
    }

    public int incr(int count) {
        count++;
        if (count > 100) {
            throw new RuntimeException("停车场容量计数有误");
        }
        return count;
    }

    public int decr(int count) {
        count--;
        if (count < 0) {
            throw new RuntimeException("停车场已满");
        }
        return count;
    }

    public int[] getCapacity() {
        return new int[]{capacity, version};
    }
}

class ParkingSpace {
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private String carNo;
    private long fee;

    public long calFee() {
        int unitPrice = 2000; //每小时20元，单位分
        int fareCap = 10000; //封顶100元，单位分
        this.fee = Math.min(Duration.between(inTime, outTime).toHours() * unitPrice, fareCap);
        return this.fee;
    }

    public void in(String carNo) {
        this.carNo = carNo;
        this.inTime = LocalDateTime.now();
        System.out.println("车辆" + carNo + "准备进入停车场，时间：" + inTime);
    }

    public void out() {
        this.outTime = LocalDateTime.now();
        System.out.println("车辆" + carNo + "离开停车场，时间：" + outTime);
    }
}

class Bill {
    private final static AtomicInteger ID_GEN = new AtomicInteger(1);
    private int id;
    private String carNo;
    private long fee;
    private volatile int state; //0-未支付，1-已支付

    public Bill(String carNo, long fee) {
        this.id = ID_GEN.getAndAdd(1);
        this.carNo = carNo;
        this.fee = fee;
        this.state = 0;
        System.out.println("生成停车账单，车辆：" + carNo + "，费用：" + fee + "元");
    }

    public void pay() {
        if (this.state == 0) {
            this.state = 1;
        } else {
            throw new RuntimeException("该账单已支付");
        }
        System.out.println("车辆" + carNo + "支付停车费用：" + fee + "元");
    }

    public boolean payed() {
        return this.state == 1;
    }
}

class InconsistentException extends RuntimeException {
    public InconsistentException(String message) {
        super(message);
    }
}

class Transaction {
    public static void start() {
        System.out.println("事务开始");
    }

    public static void commit() {
        System.out.println("事务结束");
    }

    public static void rollback() {
        System.out.println("事务回滚");
    }
}

class Async {
    private final static ExecutorService executor = Executors.newFixedThreadPool(5);
    private final static ExecutorService queuedExecutor = Executors.newSingleThreadExecutor();

    public static void submit(Runnable r) {
        executor.submit(new RetryTask(r, 2));
    }

    public static void submit(int times, Runnable r) {
        executor.submit(new RetryTask(r, times));
    }

    private static class RetryTask implements Runnable {
        private final Runnable task;
        private int retryLeft;

        RetryTask(Runnable task, int retries) {
            this.task = task;
            this.retryLeft = retries;
        }

        @Override
        public void run() {
            try {
                task.run(); // 执行任务
            } catch (Exception e) {
                retryLeft--;
                if (retryLeft == 0) {
                    System.err.println("任务重试达最大次数，仍失败" + e);
                    return;
                }
                queuedExecutor.submit(this);
            }
        }
    }
}