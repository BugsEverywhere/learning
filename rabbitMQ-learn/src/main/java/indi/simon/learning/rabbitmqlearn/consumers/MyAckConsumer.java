package indi.simon.learning.rabbitmqlearn.consumers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class MyAckConsumer extends DefaultConsumer {

    public MyAckConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope,
                               AMQP.BasicProperties properties, byte[] body)
            throws IOException {
        try {
            String message = new String(body, "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

        } finally {
            //此处发送任务执行完毕的信号
            this.getChannel().basicAck(envelope.getDeliveryTag(), false);
        }
    }

}
