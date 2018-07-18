package indi.simons.rabbitmqlearn.exchange.direct;

import com.rabbitmq.client.*;
import indi.simons.rabbitmqlearn.enums.LogSeverityEnum;
import indi.simons.rabbitmqlearn.consumers.MyRoutingkeyConsumer;

public class ReceiveLogsDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();

        //此处bind队列的时候按同样的routingkey（第三个参数）bind
        //routingkey和队列的关系既可以是一对多，也可以是多对一。
        channel.queueBind(queueName, EXCHANGE_NAME, LogSeverityEnum.debug.toString());

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new MyRoutingkeyConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
    }
}
