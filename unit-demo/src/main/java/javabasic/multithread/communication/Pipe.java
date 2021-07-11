package javabasic.multithread.communication;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author lee
 * @date 2020-07-14
 */
public class Pipe {
    public static void main(String[] args) throws IOException, InterruptedException {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        writer.connect(reader);

        new Thread(new ReaderThread(reader)).start();
        Thread.sleep(1000);
        new Thread(new WriterThread(writer)).start();
    }

    static class ReaderThread implements Runnable{
        private PipedReader reader;

        public ReaderThread(PipedReader reader){
            this.reader = reader;
        }

        @Override
        public void run() {
            System.out.println("this is reader");
            int receive;
            try{
                while ((receive = reader.read())!=-1){
                    System.out.print((char)receive);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    static class WriterThread implements Runnable{
        private PipedWriter writer;

        public WriterThread(PipedWriter writer){
            this.writer = writer;
        }
        @Override
        public void run() {
            System.out.println("this is writer");
            try {
                writer.write("test");
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try{
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
