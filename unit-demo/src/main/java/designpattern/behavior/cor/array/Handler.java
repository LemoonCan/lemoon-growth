package designpattern.behavior.cor.array;

/**
 * @author lee
 * @date 2022/10/17
 */
public abstract class Handler {
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public boolean handle() {
        boolean end = doHandle();
        if (!end && next != null) {
            next.handle();
        }
        return end;
    }

    protected abstract boolean doHandle();
}
