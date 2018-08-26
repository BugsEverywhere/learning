package indi.simon.kafkalearn.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
@Component
public class KafkaConsumerListener {

    @KafkaListener(id = "foo", topics = "annotated1")
    public void listen(ConsumerRecord<String, String> record) {

        String topic = record.topic();
        String key = record.key();
        String value = record.value();
        long offset = record.offset();
        int partition = record.partition();

        System.out.println(topic);
        System.out.println(key);
        System.out.println(value);
        System.out.println(offset);
        System.out.println(partition);

    }
}
