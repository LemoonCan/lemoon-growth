package designpattern.behavior.cor.list;

/**
 * @author lee
 * @date 2022/10/17
 */
public class HandlerA extends Handler {
    @Override
    protected boolean doHandle() {
        System.out.println("A executing...");
        return false;
    }
}
