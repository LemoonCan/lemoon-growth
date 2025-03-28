package designpattern.behavior.cor.array;

/**
 * @author lee
 * @since 2022/10/17
 */
public class HandlerChain {
    private Handler head;
    private Handler tail;

    public void addHandler(Handler handler) {
        if (head == null) {
            head = handler;
            tail = handler;
            return;
        }
        tail.setNext(handler);
        tail = handler;
    }

    public void handle() {
        if (head != null) {
            head.handle();
        }
    }
}
