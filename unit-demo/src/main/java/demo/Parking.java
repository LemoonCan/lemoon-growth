package demo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lemoon
 * @since 2025/12/4
 */
public class Parking {
    public static void main(String[] args) {
        Parking parking = new Parking();
        String carNo = "粤A12345";
        parking.in(carNo);
        parking.prepareOut(carNo);
        parking.pay(carNo);
    }

    private final static Map<String, ParkingSpace> park = new HashMap<>(16);
    private final static Map<String, Bill> bills = new HashMap<>(16);
    private final static ParkCapacity capacity = new ParkCapacity();

    public void in(String carNo) {
        Transaction.start();
        try {
            ParkingSpace space = new ParkingSpace();
            space.in(carNo);
            park.put(carNo, space);

            int retryTimes = 3;
            while (retryTimes > 0) {
                int oldCount = capacity.getCapacity();
                int newCount = capacity.decr(oldCount);
                synchronized (capacity) {
                    try {
                        capacity.update(newCount, oldCount);
                        break;
                    } catch (InconsistentException e) {
                        retryTimes--;
                        if (retryTimes == 0) {
                            throw new RuntimeException("更新停车场容量失败，达到最大重试次数");
                        }
                    }
                }
            }
        } catch (Exception e) {
            Transaction.rollback();
            throw new RuntimeException("无法进入停车场");
        }
        Transaction.commit();
    }

    public void prepareOut(String carNo) {
        Transaction.start();
        try {
            ParkingSpace space = park.get(carNo);
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

            Async.submit(3, () -> out(carNo));
        } catch (Exception e) {
            Transaction.rollback();
        }
        Transaction.commit();
    }

    public void out(String carNo) {
        Transaction.start();
        try {
            ParkingSpace space = park.get(carNo);
            space.out();
            int oldCount = capacity.getCapacity();
            int newCount = capacity.incr(oldCount);
            synchronized (capacity) {
                capacity.update(newCount, oldCount);
            }
        } catch (Exception e) {
            Transaction.rollback();
            throw new RuntimeException("无法离开停车场");
        }
        Transaction.commit();
    }
}

class ParkCapacity {
    private int capacity = 100;

    public void update(int value, int expect) {
        if (capacity != expect) {
            throw new InconsistentException("数据不一致");
        }
        capacity = value;
    }

    public int incr(int count) {
        count++;
        if (count > capacity) {
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

    public int getCapacity() {
        return capacity;
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
    }

    public void out() {
        this.outTime = LocalDateTime.now();
    }
}

class Bill {
    private final static AtomicInteger ID_GEN = new AtomicInteger(1);
    private int id;
    private String carNo;
    private long fee;
    private int state; //0-未支付，1-已支付

    public Bill(String carNo, long fee) {
        this.id = ID_GEN.getAndAdd(1);
        this.carNo = carNo;
        this.fee = fee;
        this.state = 0;
    }

    public void pay() {
        this.state = 1;
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
    private final static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void submit(int times, Runnable r) {
        executor.submit(new RetryTask(r, times));
    }

    private static class RetryTask implements Runnable {
        private final Runnable task;
        private int retriesLeft;

        RetryTask(Runnable task, int retries) {
            this.task = task;
            this.retriesLeft = retries;
        }

        @Override
        public void run() {
            try {
                task.run(); // 执行任务
            } catch (Exception e) {
                retriesLeft--;
                if (retriesLeft > 0) {
                    executor.submit(this); // 再提交一次自己
                } else {
                    // 记录日志、通知等
                    System.err.println("任务重试结束，但仍失败");
                }
            }
        }
    }
}