package main.javabasic.multithread.communication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @date 2020-12-01
 */
public class Restaurant {
    Meal meal;
    Chef chef = new Chef(this);
    WaitPerson waitPerson = new WaitPerson(this);

    ExecutorService exec = Executors.newCachedThreadPool();

    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}

class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal" + orderNum;
    }
}

class WaitPerson implements Runnable {
    private Restaurant restaurant;

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    if (restaurant.meal == null) {
                        wait();
                    }
                }
                System.out.println("WaitPerson got a restaurant.meal" + restaurant.meal);
                synchronized (restaurant.chef){
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson interrupted!");
        }
    }
}

class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            System.out.println("Chef doing");
            while (!Thread.interrupted()){
                synchronized (this) {
                    if (restaurant.meal != null) {
                        wait();
                    }
                }
                if(++count==10){
                    System.out.println("Out of food! Closing...");
                    restaurant.exec.shutdownNow();
                    //waitPerson interrupted?? 可以理解为chef结束了，但waitPerson还在等待chef，所以是waitPerson抛出异常
                    return;
                }
                System.out.println("Order up!");
                synchronized (restaurant.waitPerson){
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                //Sleep注释掉的话，waitPerson收不到上最后一份餐的通知，
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (InterruptedException e){
            System.out.println("Chef interrupted");
        }
    }
}