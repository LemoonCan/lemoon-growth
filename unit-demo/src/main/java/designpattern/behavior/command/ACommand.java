package designpattern.behavior.command;

/**
 * @author lee
 * @since 2022/10/24
 */
public class ACommand implements Command{
    @Override
    public void execute() {
        System.out.println("执行 A...");
    }
}
