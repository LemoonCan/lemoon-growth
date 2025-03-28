package designpattern.behavior.cor.list;

/**
 * @author lee
 * @since 2022/10/17
 */
public class HandlerB extends Handler {
    @Override
    protected boolean doHandle() {
        System.out.println("B executing...");
        return false;
    }
}
