package javabasic.multithread.introduction.daemon;

import java.util.concurrent.ThreadFactory;

/**
 * @author lee
 * @since 2020-11-09
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
