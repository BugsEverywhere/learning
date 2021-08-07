package indi.simon.learning;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * 本类使用的是从1.7开始java nio 2
 * @author chenzhuo(zhiyue)
 */
public class PlainNio2EchoServer {

    public void serve(int port) throws IOException {
        System.out.println("Listening for connections on port " + port);
        final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(port);
        serverChannel.bind(address);
        final CountDownLatch latch = new CountDownLatch(1);
        //只有AsynchronousServerSocketChannel可以监听accept事件，在这里就是调用accept方法，一旦获取了某个accept事件，并且建立连接了，会执行第二个参数CompletionHandler的相应方法
        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(final AsynchronousSocketChannel channel, Object attachment) {
                //在serverChannel中成功建立了连接，在这一步让其监听下一个accept事件
                serverChannel.accept(null, this);
                ByteBuffer buffer = ByteBuffer.allocate(100);
                //从所建立的连接的channel读取内容到ByteBuffer中，读完之后会触发EchoCompletionHandler的相应方法的执行
                channel.read(buffer, buffer, new EchoCompletionHandler(channel));
            }

            @Override
            public void failed(Throwable throwable, Object attachment) {
                try {
                    serverChannel.close();
                } catch (IOException ignored) {

                } finally {
                    latch.countDown();
                }
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static final class EchoCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
        private final AsynchronousSocketChannel channel;

        EchoCompletionHandler(AsynchronousSocketChannel channel) {
            this.channel = channel;
        }

        @Override
        public void completed(Integer result, ByteBuffer buffer) {
            //读完之后开始回写到client连接的channel
            buffer.flip();
            //回写完执行所传入的CompletionHandler的相应方法
            channel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                    if (buffer.hasRemaining()) {
                        //如果buffer中还有残留，继续写完
                        channel.write(buffer, buffer, this);
                    } else {
                        //如果buffer中没有残留了，
                        buffer.compact();
                        //重新监听channel中的read事件，读完了就执行本EchoCompletionHandler实例中的相应方法
                        channel.read(buffer, buffer, EchoCompletionHandler.this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        channel.close();
                    } catch (IOException ignored) {

                    }
                }
            });
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            try {
                //如果失败，就关闭该客户端channel
                channel.close();
            } catch (IOException ignored) {

            }
        }
    }
}



