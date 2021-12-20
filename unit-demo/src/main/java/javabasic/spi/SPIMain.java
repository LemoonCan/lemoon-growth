package javabasic.spi;

import java.util.ServiceLoader;

/**
 * @author lee
 * @date 2021/12/16
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout shout : shouts) {
            shout.shout();
        }
    }
}
