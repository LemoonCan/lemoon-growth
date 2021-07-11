package javabasic.multithread.simulation;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @date 2020-12-16
 * 机壳构造器创建 机壳队列
 * 取机壳队列的车 组装引擎、轮胎、驾驶系统
 * 引擎、轮胎、驾驶系统各有自己的制造机器人，通过机器人池管理(目前各组件的机器人只有一个)
 * 报告队列打印完成的车
 */
public class CarBuilder {
    public static void main(String[] args) throws Exception {
        CarQueue chassisQueue = new CarQueue(),
                finishQueue = new CarQueue();
        ExecutorService executorService = Executors.newCachedThreadPool();
        RobotPool robotPool = new RobotPool();
        executorService.execute(new EngineRobot(robotPool));
        executorService.execute(new DriveTrainRobot(robotPool));
        executorService.execute(new WheelRobot(robotPool));
        executorService.execute(new Assembler(chassisQueue, finishQueue, robotPool));
        executorService.execute(new Reporter(finishQueue));
        executorService.execute(new ChassisBuilder(chassisQueue));

        TimeUnit.SECONDS.sleep(7);
        executorService.shutdownNow();
    }
}

class Car {
    private final int id;
    private boolean engine = false, driveTrain = false, wheels = false;

    public Car(int id) {
        this.id = id;
    }

    public Car() {
        this.id = -1;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void addEngine() {
        this.engine = true;
    }

    public synchronized void addDriveTrain() {
        this.driveTrain = true;
    }

    public synchronized void addWheels() {
        this.wheels = true;
    }

    @Override
    public String toString() {
        return "Car " + id + "[engine:" + engine + ",driveTrain:" + driveTrain + ",wheels:" + wheels + "]";
    }
}

class CarQueue extends LinkedBlockingDeque<Car> {
}

/**
 * 机壳构造器
 */
class ChassisBuilder implements Runnable {
    private CarQueue carQueue;
    private int counter = 0;

    public ChassisBuilder(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                Car c = new Car(counter++);
                System.out.println("ChassisBuilder created " + c);
                carQueue.put(c);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted ChassisBuilder");
        }
        System.out.println("ChassisBuilder off");
    }
}

/**
 * 组装器
 */
class Assembler implements Runnable {
    private CarQueue chassisQueue, finishingQueue;
    private Car car;
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;

    public Assembler(CarQueue chassisQueue, CarQueue finishingQueue, RobotPool robotPool) {
        this.chassisQueue = chassisQueue;
        this.finishingQueue = finishingQueue;
        this.robotPool = robotPool;
    }

    public Car car() {
        return car;
    }

    public CyclicBarrier barrier() {
        return barrier;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car = chassisQueue.take();
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(WheelRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                barrier.await();
                finishingQueue.put(car);
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting assembler via interrupting ");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println("assembler off ");
    }
}

class Reporter implements Runnable {
    private CarQueue carQueue;

    public Reporter(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(carQueue.take());
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting reporter via interrupted");
        }
        System.out.println("Reporter Off");
    }
}

abstract class Robot implements Runnable {
    private RobotPool pool;

    public Robot(RobotPool robotPool) {
        this.pool = robotPool;
    }

    protected Assembler assembler;

    public Robot assignAssembler(Assembler assembler) {
        this.assembler = assembler;
        return this;
    }

    private boolean engage = false;

    /**
     * 雇佣
     */
    public synchronized void engage() {
        this.engage = true;
        notifyAll();
    }

    abstract protected void performService();

    @Override
    public void run() {
        try {
            powerdown();
            while (!Thread.interrupted()) {
                performService();
                assembler.barrier().await();
                powerdown();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this + " off");
    }

    /**
     * 断电
     * @throws InterruptedException
     */
    private synchronized void powerdown() throws InterruptedException {
        engage = false;
        assembler = null;
        pool.release(this);
        while (!engage) {
            wait();
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

class EngineRobot extends Robot {

    public EngineRobot(RobotPool robotPool) {
        super(robotPool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " install engine ");
        assembler.car().addEngine();
    }
}

class DriveTrainRobot extends Robot {
    public DriveTrainRobot(RobotPool robotPool) {
        super(robotPool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " install driveTrain");
        assembler.car().addDriveTrain();
    }
}

class WheelRobot extends Robot {
    public WheelRobot(RobotPool robotPool) {
        super(robotPool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " install wheels");
        assembler.car().addWheels();
    }
}

/**
 * 机器人池(引擎机器人、轮胎机器人、驾驶系统机器人)
 */
class RobotPool {
    private Set<Robot> pool = new HashSet<>();

    public synchronized void add(Robot r) {
        pool.add(r);
        notifyAll();
    }

    synchronized void hire(Class<? extends Robot> robotType, Assembler d) throws InterruptedException {
        for (Robot r : pool) {
            if (r.getClass().equals(robotType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage();
                return;
            }
        }
        wait();
        hire(robotType, d);
    }

    public void release(Robot robot) {
        add(robot);
    }
}
