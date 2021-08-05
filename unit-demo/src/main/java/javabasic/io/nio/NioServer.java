package javabasic.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lee
 * @date 8/4/21
 */
public class NioServer {
    public static void main(String[] args) {
        ServerSocketChannel server;
        Selector selector;
        try {
            server = ServerSocketChannel.open();
            InetSocketAddress socketAddress = new InetSocketAddress(20);
            server.bind(socketAddress);
            // OS NON-BLOCKING
            server.configureBlocking(false);

            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel sc = (ServerSocketChannel) key.channel();
                        SocketChannel client = sc.accept();
                        //设置客户端连接非阻塞，保证客户端不发数据时不阻塞
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    } else if (key.isWritable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                        while (byteBuffer.hasRemaining() && sc.write(byteBuffer) != -1) ;
                        key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
                        sc.close();
                    } else if(key.isReadable()){
                        SocketChannel client = (SocketChannel) key.channel();
                        client.configureBlocking(false);
                        ByteBuffer buffer = ByteBuffer.allocate(64);
                        WritableByteChannel out = Channels.newChannel(System.out);
                        while (client.read(buffer)!=0) {
                            buffer.flip();
                            out.write(buffer);
                            buffer.clear();
                        }

                        ByteBuffer byteBuffer = ByteBuffer.wrap("Hello Client!".getBytes(StandardCharsets.UTF_8));
                        SelectionKey writeKey = client.register(selector, SelectionKey.OP_WRITE);
                        writeKey.attach(byteBuffer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {
                        cex.printStackTrace();
                    }
                }
            }
        }
    }
}

