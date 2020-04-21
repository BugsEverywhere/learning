package indi.simons.learning.rabbitmqlearn.consumers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class RPCClientConsumer extends DefaultConsumer{

    private BlockingQueue<String> response;

    private String corrId;

    public RPCClientConsumer(Channel channel, BlockingQueue<String> response, String corrId) {
        super(channel);
        this.response = response;
        this.corrId = corrId;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws
            IOException {
        if (properties.getCorrelationId().equals(corrId)) {
            response.offer(new String(body, "UTF-8"));
        }
    }


}
