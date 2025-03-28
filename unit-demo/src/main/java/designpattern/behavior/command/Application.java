package designpattern.behavior.command;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lee
 * @since 2022/10/24
 */
public class Application {
    private Queue<Command> queue = new LinkedList<>();

    public void mainloop() throws InterruptedException {
        while (true){
            if(queue.isEmpty()){
                System.out.println("稍等1s再查询");
                Thread.sleep(1000);
            }
            Command command = queue.poll();
            command.execute();
        }
    }

    public void addTask(Command command){
        queue.add(command);
    }
}
