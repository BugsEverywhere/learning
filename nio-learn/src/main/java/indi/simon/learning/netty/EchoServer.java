package indi.simon.learning.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author chenzhuo(zhiyue)
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //server的Bootstrap
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    //指定使用NIO方式
                    .channel(NioServerSocketChannel.class)
                    //指定监听本地地址和端口
                    .localAddress(new InetSocketAddress(port))
                    //添加事件处理器
                    .childHandler(
                            new ChannelInitializer<SocketChannel>() {
                                @Override
                                public void initChannel(SocketChannel socketChannel) {
                                    socketChannel.pipeline().addLast(
                                            new EchoServerHandler());
                                }
                            });
            //开始bind，同步阻塞在此，直至bind成功
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println(EchoServer.class.getName() + "started and listen on " + channelFuture.channel().localAddress());
            //阻塞直至关闭成功
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println(
                    "Usage: " + EchoServer.class.getSimpleName() + "<port>");
        }
        int port = 8080;
        new EchoServer(port).start();
    }
}
