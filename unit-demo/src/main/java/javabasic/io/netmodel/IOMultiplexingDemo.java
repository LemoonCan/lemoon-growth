package javabasic.io.netmodel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 操作系统提供的多路复用器，轮询所有通道，看是否就绪
 * selector取到的是就绪的通道事件(就绪意味着通常是不会出现阻塞的IO操作)
 * 相比于线程无差别轮询，性能更高
 *
 * @author lee
 * @since 2023/9/11
 */
public class IOMultiplexingDemo {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // 阻塞等待就绪事件
            selector.select();

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(16);
                    int bytesRead = channel.read(buffer);
                    if (bytesRead == -1) {
                        channel.close();
                    } else if (bytesRead > 0) {
                        buffer.flip();
                        channel.write(buffer);
                        System.out.println(new String(buffer.array()));
                        buffer.clear();
                    }
                }
            }
        }
    }
}
