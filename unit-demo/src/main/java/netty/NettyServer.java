package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lee
 * @since 7/22/21
 */
public class NettyServer {
    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            //设置 eventGroup 线程组
            bootstrap.group(bossGroup, workerGroup)
                    //设置服务端通道实现类型
                    .channel(NioServerSocketChannel.class)
                    //设置主线程组连接参数(对应bossGroup，处理接收进来的连接)
                    //ChannelOption.SO_BACKLOG,128 设置服务端接受连接的队列长度为128，如果队列已满，客户端连接将被拒绝
                    .option(ChannelOption.SO_BACKLOG,128)
                    //设置子线程组连接参数(对应workerGroup，处理父管道接收进来的连接)
                    //ChannelOption.SO_KEEPALIVE,true 设置为保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    //初始化通道对象
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            //服务端添加处理器
                            channel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("服务端准备就绪🐤");
            //绑定端口号，启动服务端
            ChannelFuture channelFuture = bootstrap.bind(6000).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            //关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
