package javabasic.multithread.pool;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @since 2020-12-08
 *
 * ScheduledThreadPoolExecutor 控制第一次执行延迟时间，执行间隔时间
 */
public class GreenHouseScheduler {
    public static void main(String[] args) {
        GreenHouseScheduler gh = new GreenHouseScheduler();
        gh.schedule(gh.new Terminate(),5000);
        //第一次执行延迟0毫秒，之后每隔1000毫秒执行一次
        gh.repeat(gh.new Bell(),0,1000);
        gh.repeat(gh.new ThermostatNight(),0,2000);
        gh.repeat(gh.new LightOn(),0,200);
        gh.repeat(gh.new LightOff(),0,400);
        gh.repeat(gh.new WaterOn(),0,600);
        gh.repeat(gh.new WaterOff(),0,800);
        gh.repeat(gh.new ThermostatDay(),0,1400);
        gh.repeat(gh.new CollectData(),500,500);
    }

    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";

    public synchronized String getThermostat() {
        return this.thermostat;
    }

    public synchronized void setThermostat(String thermostat) {
        this.thermostat = thermostat;
    }

    ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(10);

    public void schedule(Runnable event, long delay) {
        schedule.schedule(event, delay, TimeUnit.MILLISECONDS);
    }

    public void repeat(Runnable event, long initialDelay, long period) {
        schedule.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    class LightOn implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning on lights");
            light = true;
        }
    }

    class LightOff implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning off lights");
            light = false;
        }
    }

    class WaterOn implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning greenhouse water on");
            water = true;
        }
    }

    class WaterOff implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning greenhouse water off");
            water = false;
        }
    }

    class ThermostatNight implements Runnable {

        @Override
        public void run() {
            System.out.println("Thermostat to night setting");
            setThermostat("night");
        }
    }

    class ThermostatDay implements Runnable {

        @Override
        public void run() {
            System.out.println("Thermostat to day setting");
            setThermostat("day");
        }
    }

    class Bell implements Runnable {
        @Override
        public void run() {
            System.out.println("Bing!");
        }
    }

    class Terminate implements Runnable {
        @Override
        public void run() {
            System.out.println("Terminating");
            schedule.shutdownNow();
            new Thread() {
                @Override
                public void run() {
                    for (DataPoint d:data){
                        System.out.println(d);
                    }
                }
            }.start();
        }
    }

    static class DataPoint {
        final Calendar time;
        final float temperature;
        final float humidity;

        public DataPoint(Calendar time, float temperature, float humidity) {
            this.time = time;
            this.temperature = temperature;
            this.humidity = humidity;
        }

        @Override
        public String toString() {
            return time.getTime() + String.format("temperature:%1$.1f humidity:%2$.2f", temperature, humidity);
        }
    }

    private Calendar lastTime = Calendar.getInstance();
    {
        lastTime.set(Calendar.MINUTE, 30);
        lastTime.set(Calendar.SECOND, 00);
    }

    private float lastTemp = 65.0f;
    private int tempDirection = +1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = +1;
    Random rand = new Random(47);
    List<DataPoint> data = Collections.synchronizedList(new ArrayList<>());

    class CollectData implements Runnable {
        @Override
        public void run() {
            System.out.println("Collection data");
            synchronized (GreenHouseScheduler.this) {
                lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE) + 30);
                if (rand.nextInt(5) == 4) {
                    tempDirection = -tempDirection;
                }
                lastTemp += lastTemp + tempDirection * (1.0f + rand.nextFloat());
                if (rand.nextInt(5) == 4) {
                    humidityDirection = -humidityDirection;
                }
                lastHumidity += lastHumidity + humidityDirection * (1.0f + rand.nextFloat());
                data.add(new DataPoint((Calendar) lastTime.clone(), lastTemp, lastHumidity));
            }
        }
    }
}
