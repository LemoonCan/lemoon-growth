package designpattern.behavior.command;

/**
 * @author lee
 * @date 2022/10/24
 */
public class BCommand implements Command{
    @Override
    public void execute() {
        System.out.println("执行 B...");
    }
}
