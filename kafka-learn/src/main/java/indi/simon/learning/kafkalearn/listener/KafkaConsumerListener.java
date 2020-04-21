package indi.simon.learning.kafkalearn.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
@Component
public class KafkaConsumerListener implements ConsumerSeekAware {

    @KafkaListener(topics = "test123")
    void listen(ConsumerRecord<String, String> record) {

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

    @Override
    public void registerSeekCallback(ConsumerSeekCallback callback) {

    }

    /**
     * 如果需要在consumer启动时seek到指定位置，需要在此方法中调用callback的seek方法
     * @param assignments
     * @param callback
     */
    @Override
    public void onPartitionsAssigned(Map<org.apache.kafka.common.TopicPartition, Long> assignments, ConsumerSeekCallback callback) {

    }

    @Override
    public void onIdleContainer(Map<org.apache.kafka.common.TopicPartition, Long> assignments, ConsumerSeekCallback callback) {

    }
}
