package designpattern.structured.bridge.notifylevel;

import designpattern.structured.bridge.MsgSender;
import designpattern.structured.bridge.Notification;

/**
 * @author lee
 * @since 2022/10/8
 */
public class SevereNotification extends Notification {
    public SevereNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String msg) {

    }
}
