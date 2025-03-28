package designpattern.behavior.observer;

/**
 * @author lee
 * @since 2022/10/12
 */
public class Message {
    private String msg;

    public Message(String msg) {
        this.msg = msg;
    }

    public String get() {
        return msg;
    }
}
