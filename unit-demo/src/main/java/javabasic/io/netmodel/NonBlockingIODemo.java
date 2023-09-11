package javabasic.io.netmodel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 无差别轮询所有通道，看是否就绪
 *
 * @author lee
 * @since 2023/9/11
 */
public class NonBlockingIODemo {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);

        List<SocketChannel> socketChannels = new ArrayList<>();

        while (true) {
            // 非阻塞，立即返回
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                socketChannel.configureBlocking(false);
                socketChannels.add(socketChannel);
            }

            Iterator<SocketChannel> iterator = socketChannels.iterator();
            while (iterator.hasNext()) {
                SocketChannel channel = iterator.next();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int bytesRead = channel.read(buffer);
                if (bytesRead == -1) {
                    iterator.remove();
                    channel.close();
                } else if (bytesRead > 0) {
                    buffer.flip();
                    channel.write(buffer);
                    buffer.clear();
                }
            }
        }
    }

}
