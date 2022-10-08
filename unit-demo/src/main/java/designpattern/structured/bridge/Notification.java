package designpattern.structured.bridge;

/**
 * 消息发送存在两个维度的区别：
 * 1.发送渠道
 * 2.消息级别
 *
 * 桥接模式通过内部组合方式封装此二维度，抽象的是一组接口，各接口可分别实现
 * 使用时可由使用方自由指定
 *
 * @author lee
 * @date 2022/10/8
 */
public abstract class Notification {
    private MsgSender msgSender;

    public Notification(MsgSender msgSender) {
        this.msgSender = msgSender;
    }

    public abstract void notify(String msg);
}
