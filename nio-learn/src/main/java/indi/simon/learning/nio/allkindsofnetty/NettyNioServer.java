package indi.simon.learning.nio.allkindsofnetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author chenzhuo(zhiyue)
 */
public class NettyNioServer {

    public void server(int port) throws Exception {
        final ByteBuf buf = Unpooled.copiedBuffer("Hi!\r\n", StandardCharsets.UTF_8);
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(
//                                    new ChannelStateHandlerAdapter() {
//                                        @Override
//                                        public void channelActive(
//                                                ChannelHandlerContext ctx) throws Exception {
//                                            ctx.write(buf.duplicate())
//                                                    .addListener(ChannelFutureListener.CLOSE);
//                                        }
//
//                                        @Override
//                                        public void inboundBufferUpdated(
//                                                ChannelHandlerContext ctx) {
//                                            czx.fireInboundBufferUpdated();
//                                        }
//                                    }
                            );
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }


}
