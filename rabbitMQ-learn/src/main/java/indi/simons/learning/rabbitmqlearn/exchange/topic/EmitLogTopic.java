package indi.simons.learning.rabbitmqlearn.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import indi.simons.learning.rabbitmqlearn.enums.AnimalColorEnum;
import indi.simons.learning.rabbitmqlearn.enums.AnimalGenderEnum;
import indi.simons.learning.rabbitmqlearn.enums.AnimalSpeciesEnum;

public class EmitLogTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv)
            throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.22.130");
        factory.setPort(5672);
        factory.setUsername("simons");
        factory.setPassword("12345678");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String routingKey = new StringBuilder()
                .append(AnimalColorEnum.Black.getColor()+".")
                .append(AnimalGenderEnum.Female.getGender() + ".")
                .append(AnimalSpeciesEnum.Monkey.getSpecies()).toString();
        String message = "gong xi fa cai";

        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
        System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

        connection.close();
    }
    //...
}
