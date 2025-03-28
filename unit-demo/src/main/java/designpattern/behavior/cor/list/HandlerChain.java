package designpattern.behavior.cor.list;

import designpattern.behavior.cor.array.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lee
 * @since 2022/10/17
 */
public class HandlerChain {
    private List<Handler> handlers = new ArrayList<>();

    public void addHandler(Handler handler) {
        handlers.add(handler);
    }

    public void handle() {
        for (Handler handler : handlers) {
            boolean end = handler.handle();
            if (end) {
                break;
            }
        }
    }
}
