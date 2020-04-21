package indi.simons.learning.rabbitmqlearn.rpc;

import com.rabbitmq.client.*;
import indi.simons.learning.rabbitmqlearn.consumers.RPCServerConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RPCServer {

    private static final String RPC_QUEUE_NAME = "rpc_queue";

    public static void main(String[] argv) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.22.130");
        factory.setPort(5672);
        factory.setUsername("simons");
        factory.setPassword("12345678");

        Connection connection = null;
        try {
            connection = factory.newConnection();

            Channel channel = connection.createChannel();

            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);

            channel.basicQos(1);

            System.out.println(" [x] Awaiting RPC requests");

            Consumer consumer = new RPCServerConsumer(channel);

            channel.basicConsume(RPC_QUEUE_NAME, false, consumer);

            // Wait and be prepared to consume the message from RPC client.
//            while (true) {
//                synchronized(consumer) {
//                    try {
//                        consumer.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } finally {
//            if (connection != null)
//                try {
//                    connection.close();
//                } catch (IOException _ignore) {}
        }
    }
}