package indi.simon.learning.rabbitmqlearn.exchange.fanout;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv)
            throws java.io.IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.22.130");
        factory.setPort(5672);
        factory.setUsername("simons");
        factory.setPassword("12345678");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //此处声明的不是一个队列，而是一个fanout类型的exchange，然后再后面再把队列bind到这个exchange上去
        //如果没有队列被bind上去，则消息发布到exchange之后会丢失掉。
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = "Hello World";

        //这里发送消息就不是发送给队列了，而是给exchange
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
    //...
}
