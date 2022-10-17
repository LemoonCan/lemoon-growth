package designpattern.behavior.cor.list;

/**
 * @author lee
 * @date 2022/10/17
 */
public abstract class Handler {
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public void handle() {
        boolean end = doHandle();
        if (!end && next != null) {
            next.handle();
        }
    }

    protected abstract boolean doHandle();
}
