package indi.simons.learning.rabbitmqlearn.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import indi.simons.learning.rabbitmqlearn.enums.LogSeverityEnum;

import java.util.concurrent.TimeoutException;

public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv)
            throws java.io.IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //此处声明了一个direct类型的exchange，此exchange的功能是能按照不同的routingkey来对消息进行分发
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        LogSeverityEnum severity = LogSeverityEnum.debug;
        String message = "this info is for debug";

        //发送消息的时候指定routingkey是哪个，这样消息就能路由到指定的队列
        //以日志等级作为routingkey，填到第二个参数，此时第一个参数已经填了，所以第二个参数就是
        //routingkey的意思了，而不是queue名字。
        //routingkey和队列在后头需要一起bind到该exchange上
        channel.basicPublish(EXCHANGE_NAME, severity.toString(), null, message.getBytes());
        System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

        channel.close();
        connection.close();
    }
}
