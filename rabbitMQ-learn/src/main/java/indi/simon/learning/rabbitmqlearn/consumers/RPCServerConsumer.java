package indi.simon.learning.rabbitmqlearn.consumers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class RPCServerConsumer extends DefaultConsumer {

    public RPCServerConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                .Builder()
                .correlationId(properties.getCorrelationId())
                .build();

        String response = "";

        try {
            String message = new String(body,"UTF-8");
            int n = Integer.parseInt(message);

            System.out.println(" [.] fib(" + message + ")");
            response += fib(n);
        }
        catch (RuntimeException e){
            System.out.println(" [.] " + e.toString());
        }
        finally {
            this.getChannel().basicPublish( "", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));

            this.getChannel().basicAck(envelope.getDeliveryTag(), false);

            // RabbitMq consumer worker thread notifies the RPC server owner thread
//            synchronized(this) {
//                this.notify();
//            }
        }
    }

    private int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n-1) + fib(n-2);
    }
}
