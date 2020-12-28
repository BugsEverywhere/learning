package indi.simon.learning.rabbitmqlearn.queue;

import com.rabbitmq.client.*;
import indi.simon.learning.rabbitmqlearn.consumers.MyAckConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv)
            throws java.lang.InterruptedException, TimeoutException, IOException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.22.130");
        factory.setPort(5672);
        factory.setUsername("simons");
        factory.setPassword("12345678");
        Connection connection = null;
        Consumer consumer = null;

        try {
            connection = factory.newConnection();
            final Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicQos(1);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            consumer = new MyAckConsumer(channel);

            //此处第二个参数设为false意思是在handleDelivery回调方法中，在执行完
            //这个打印任务后，需要手动发送确认信息给RabbitMQ，也就是在consumer的
            //handleDelivery方法的finally块中调用basicAck方法
            //设置成true的话，这里就是自动发送了。
            channel.basicConsume(QUEUE_NAME, false, consumer);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
