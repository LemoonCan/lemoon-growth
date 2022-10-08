package designpattern.structured.bridge.notifylevel;

import designpattern.structured.bridge.MsgSender;
import designpattern.structured.bridge.Notification;

/**
 * @author lee
 * @date 2022/10/8
 */
public class NormalNotification extends Notification {
    public NormalNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String msg) {

    }
}
