package designpattern.behavior.mediator;

import java.awt.*;

/**
 * @author lee
 * @date 2022/10/26
 */
public interface Mediator {
    void handleEvent(Component component,String event);
}
