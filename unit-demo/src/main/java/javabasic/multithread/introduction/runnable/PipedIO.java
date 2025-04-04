package javabasic.multithread.introduction.runnable;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @since 2020-12-04
 */
public class PipedIO {
    public static void main(String[] args) throws Exception{
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
}

class Sender implements Runnable{
    private Random random = new Random(47);
    private PipedWriter out = new PipedWriter();

    public PipedWriter getOut() {
        return out;
    }

    @Override
    public void run() {
        try{
            while (true){
                for (char c='A';c<='z';c++){
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                }
            }
        }catch (IOException e){
            System.out.println(e+"Sender Write IOException");
        }catch (InterruptedException e){
            System.out.println(e+"Sender Sleep interrupted");
        }
    }
}

class Receiver implements Runnable{
    private PipedReader in;

    public Receiver(Sender sender) throws IOException {
        this.in = new PipedReader(sender.getOut());
    }

    @Override
    public void run() {
        try{
            while(true){
                System.out.println("Read:"+(char)in.read()+",");
            }
        }catch (IOException e){
            System.out.println(e+"Receiver read exception");
        }
    }
}