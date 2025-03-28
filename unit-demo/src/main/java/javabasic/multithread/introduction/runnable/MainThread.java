package javabasic.multithread.introduction.runnable;

/**
 * @author lee
 * @since 2020-11-05
 */
public class MainThread {
    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
        System.out.println("Waiting for LiftOff 1");

        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff 2");
    }
}
