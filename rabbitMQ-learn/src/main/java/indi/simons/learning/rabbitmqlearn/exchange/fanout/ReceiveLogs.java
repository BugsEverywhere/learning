package indi.simons.learning.rabbitmqlearn.exchange.fanout;

import com.rabbitmq.client.*;
import indi.simons.learning.rabbitmqlearn.consumers.MySimpleConsumer;

public class ReceiveLogs {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.22.130");
        factory.setPort(5672);
        factory.setUsername("simons");
        factory.setPassword("12345678");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //此处声明一个和生产者那边一样的exchange，fanout exchange的功能是将消息广播给所有绑定的队列
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        //这样是随机生成一个队列名
        String queueName = channel.queueDeclare().getQueue();
        //将队列绑定到exchange，注意此处并没有指定routingkey
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new MySimpleConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        System.out.println("main thread ends here");
    }

}
