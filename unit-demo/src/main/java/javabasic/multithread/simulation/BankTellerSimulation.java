package javabasic.multithread.simulation;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @date 2020-12-11
 */
public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));
        exec.execute(new TellerManager(exec, customers, ADJUSTMENT_PERIOD));

        if (args.length > 0) {
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        } else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}

class Customer {
    private final int serviceTime;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return this.serviceTime;
    }

    @Override
    public String toString() {
        return "[" + serviceTime + "]";
    }
}

class CustomerLine extends ArrayBlockingQueue<Customer> {

    public CustomerLine(int capacity) {
        super(capacity);
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return "[Empty]";
        }
        StringBuilder result = new StringBuilder();
        for (Customer customer : this) {
            result.append(customer);
        }
        return result.toString();
    }
}

/**
 * 控制每隔一段时间队列进入一位客户
 */
class CustomerGenerator implements Runnable {
    private CustomerLine customers;
    private static Random rand = new Random(47);

    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
                customers.put(new Customer(rand.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.out.println("CustomerGenerator interrupted");
        }
        System.out.println("CustomerGenerator terminating");
    }
}

/**
 * 柜员从排队队列中取一位客户服务
 */
class Teller implements Runnable, Comparable<Teller> {
    private static int counter = 0;
    private final int id = counter++;

    private int customersServed = 0;
    private CustomerLine customers;
    private boolean servingCustomerLine = true;

    public Teller(CustomerLine customers) {
        this.customers = customers;
    }

    @Override
    public synchronized int compareTo(Teller o) {
        return customersServed < o.customersServed ? -1 :
                (customersServed == o.customersServed ? 0 : -1);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Customer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                //管理员可能与当前柜员竞争customersServed、servingCustomerLine
                synchronized (this) {
                    customersServed++;
                    while (!servingCustomerLine) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + "terminating");
        }
    }

    public synchronized void doSomethingElse() {
        customersServed = 0;
        servingCustomerLine = false;
    }

    public synchronized void serveCustomerLine() {
        assert !servingCustomerLine : "already serving: " + this;
        servingCustomerLine = true;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Teller " + id + " ";
    }

    public String shortString() {
        return "T" + id;
    }
}

/**
 * 柜员管理：当排队人数多时，增加服务柜员；当排队人数少时，调整柜员做其他事情
 */
class TellerManager implements Runnable {
    private ExecutorService exec;
    private CustomerLine customers;
    private int adjustmentPeriod;

    /**
     * 优先级队列 add==offer
     */
    private PriorityQueue<Teller> workingTellers = new PriorityQueue<>();
    private Queue<Teller> tellerDoingOtherThings = new LinkedList<>();

    public TellerManager(ExecutorService exec, CustomerLine customers, int adjustmentPeriod) {
        this.exec = exec;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;

        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.println(customers + " { ");
                for (Teller teller : workingTellers) {
                    System.out.println(teller.shortString());
                }
                System.out.println("}");
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + "terminating");
    }

    /**
     * 调整出纳员人数
     */
    public void adjustTellerNumber() {
        if (customers.size() / workingTellers.size() > 2) {
            if (tellerDoingOtherThings.size() > 0) {
                Teller teller = tellerDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return;
            }
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
        }
        if (workingTellers.size() > 1
                && customers.size() / workingTellers.size() < 2) {
            reassignOneTeller();
        }
        if (customers.size() == 0) {
            while (workingTellers.size() > 1) {
                reassignOneTeller();
            }
        }
    }

    /**
     * 放飞服务人数最多的一位出纳员
     */
    private void reassignOneTeller() {
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellerDoingOtherThings.offer(teller);
    }

    @Override
    public String toString() {
        return "TellerManager ";
    }
}
