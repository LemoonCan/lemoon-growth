package designpattern.behavior.command;

/**
 * @author lee
 * @since 2022/10/25
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Application application = new Application();
        new Thread(()-> {
            try {
                application.mainloop();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        Thread.sleep(500);
        application.addTask(new ACommand());
        application.addTask(new BCommand());
    }
}
