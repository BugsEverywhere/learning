package indi.simon.learning.rabbitmqlearn.rpc;

import com.rabbitmq.client.*;
import indi.simon.learning.rabbitmqlearn.consumers.RPCClientConsumer;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class RPCClient {

    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";
    private String replyQueueName;

    public RPCClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.22.130");
        factory.setPort(5672);
        factory.setUsername("simons");
        factory.setPassword("12345678");

        connection = factory.newConnection();
        channel = connection.createChannel();

        replyQueueName = channel.queueDeclare().getQueue();
    }

    public String call(String message) throws IOException, InterruptedException {
        String corrId = UUID.randomUUID().toString();

        //此处添加了若干条properties，其中replyTo方法指定了对面RPC server发送返回
        //所使用的queue名字，correlationId方法指定了一个correlation id，这样在client这边
        //的消费者中可以对获取到的返回来判断这个id是不是本次请求的id
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

        BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);

        Consumer consumer = new RPCClientConsumer(channel,response,corrId);

        channel.basicConsume(replyQueueName, true, consumer);

        return response.take();
    }

    public void close() throws IOException {
        connection.close();
    }

    //...
}
