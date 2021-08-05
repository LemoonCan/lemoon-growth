package javabasic.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lee
 * @date 8/4/21
 */
public class MultiplexingClient {
    public static void main(String[] args) {
        SocketAddress remoteAddress = new InetSocketAddress("127.0.0.1", 20);

        SocketChannel client;
        Selector selector;
        try {
            client = SocketChannel.open();
            client.configureBlocking(false);
            selector = Selector.open();

            if (client.connect(remoteAddress)) {
                client.register(selector, SelectionKey.OP_READ);
            } else {
                client.register(selector, SelectionKey.OP_CONNECT);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isValid()) {
                        if (key.isConnectable()) {
                            if (client.finishConnect()) {
                                System.out.println("connected:" + client.isConnected() + " connectionPending:" + client.isConnectionPending());
                                SocketChannel sc = (SocketChannel) key.channel();

                                sc.configureBlocking(false);
                                SelectionKey writeKey = sc.register(selector, SelectionKey.OP_WRITE);
                                writeKey.attach(ByteBuffer.wrap("Hello Server!".getBytes(StandardCharsets.UTF_8)));
                                iterator.remove();
                            }
                        } else if (key.isReadable()) {
                            SocketChannel sc = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(64);
                            WritableByteChannel out = Channels.newChannel(System.out);
                            while (sc.read(buffer) != 0) {
                                buffer.flip();
                                out.write(buffer);
                                buffer.clear();
                            }

                            iterator.remove();
                        } else if (key.isWritable()) {
                            SocketChannel sc = (SocketChannel) key.channel();
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            while (buffer.hasRemaining() && sc.write(buffer) != -1) ;
                            key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
                            iterator.remove();

                            sc.register(selector, SelectionKey.OP_READ);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
