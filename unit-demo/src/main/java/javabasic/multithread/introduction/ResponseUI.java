package javabasic.multithread.introduction;

import java.io.IOException;

/**
 * @author lee
 * @date 2020-11-10
 */
public class ResponseUI extends Thread {
    private static volatile double d = 1;

    public ResponseUI() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (true) {
            d += (Math.PI + Math.E) / d;
        }
    }

    public static void main(String[] args) throws IOException {
        new ResponseUI();
        System.in.read();
        System.out.println(d);
    }
}
