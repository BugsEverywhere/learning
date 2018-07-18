package indi.simons.rabbitmqlearn.exchange.topic;

import com.rabbitmq.client.*;
import indi.simons.rabbitmqlearn.consumers.MyRoutingkeyConsumer;
import indi.simons.rabbitmqlearn.enums.AnimalColorEnum;
import indi.simons.rabbitmqlearn.enums.AnimalGenderEnum;
import indi.simons.rabbitmqlearn.enums.AnimalSpeciesEnum;

public class ReceiveLogsTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.22.130");
        factory.setPort(5672);
        factory.setUsername("simons");
        factory.setPassword("12345678");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        String bindingKey = new StringBuilder()
                .append(AnimalColorEnum.Black.getColor()+".")
                .append("#").toString();

        channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new MyRoutingkeyConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

    }
}
