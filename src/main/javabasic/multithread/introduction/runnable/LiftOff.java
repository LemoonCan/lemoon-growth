package main.javabasic.multithread.introduction.runnable;

/**
 * @author lee
 * @date 2020-11-05
 */
public class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff(){

    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "),";
    }

    @Override
    public void run() {
        while (countDown-->0){
            System.out.println(status());
            //告知CPU我的事情做完了，可以切换到其他线程执行任务
            Thread.yield();
        }
    }
}
