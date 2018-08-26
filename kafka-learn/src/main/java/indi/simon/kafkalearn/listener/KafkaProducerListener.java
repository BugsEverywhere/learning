package indi.simon.kafkalearn.listener;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class KafkaProducerListener<K,V> implements ProducerListener<K,V> {

    @Override
    public void onSuccess(ProducerRecord<K, V> producerRecord, RecordMetadata recordMetadata) {
        System.out.println("hahaha");
    }

    @Override
    public void onSuccess(String topic, Integer partition, K key, V value, RecordMetadata recordMetadata) {
        System.out.println("hahaha");
    }

    @Override
    public void onError(ProducerRecord<K, V> producerRecord, Exception exception) {
        System.out.println("oops");
    }

    @Override
    public void onError(String topic, Integer partition, K key, V value, Exception exception) {
        System.out.println("oops");
    }
}
