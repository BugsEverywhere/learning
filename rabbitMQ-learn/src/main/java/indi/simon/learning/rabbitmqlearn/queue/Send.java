package indi.simon.learning.rabbitmqlearn.queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 *
 */
public class Send
{

    private final static String QUEUE_NAME = "hello";

    public static void main( String[] args ) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.22.130");
        factory.setPort(5672);
        factory.setUsername("simons");
        factory.setPassword("12345678");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //设置这个参数是为了让MQ server同时分配给某个接受者的最大任务数，此处为一个，也就是若接受者没有
        //返回对上一个任务的确认信息，则server不会派发下一个任务给该接受者
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        //第二个参数代表是否持久化该队列，设为true可保证MQ server不丢失该队列，哪怕重启MQ server
        //声明队列的所有参数，在发送者和接受者两端都要保持一致
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";

        //第三个参数代表将该消息设为持久化，意即MQ server会将其写入磁盘，但是并不是绝对的，可能会在写的过程中因为
        //server挂掉而失败
        //其实使用队列的方式而不使用exchange，本质上还是使用了一个系统自带的默认的exchange。
        channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();

    }
}
