package main.javabasic.multithread.terminate;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @date 2020-11-26
 */
public class CloseResource {
    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InputStream socketInput = new Socket("localhost",8080).getInputStream();

        exec.execute(new IOBlocked(socketInput));
        exec.execute(new IOBlocked(System.in));

        TimeUnit.MILLISECONDS.sleep(100);

        System.out.println("Shutting down all threads");
        exec.shutdownNow();

        System.out.println("Closing "+socketInput.getClass().getName());
        socketInput.close();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("Closing "+System.in.getClass().getName());
        System.in.close();
    }
}
