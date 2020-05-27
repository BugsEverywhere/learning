package indi.simon.learning;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.*;

public class FactProducer {

    private static Properties props = new Properties();
    private static List<FactMessage> factList;

    static {
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        factList = new ArrayList<FactMessage>() {
            {
                add(new FactMessage((long) (Math.random() * Integer.MAX_VALUE), UUID.randomUUID().toString(), (long) (Math.random() * Integer.MAX_VALUE), "SEA", "JFK", "DL", 418, "7:00"));
                add(new FactMessage((long) (Math.random() * Integer.MAX_VALUE), UUID.randomUUID().toString(), (long) (Math.random() * Integer.MAX_VALUE), "SFO", "LAX", "AA", 1250, "7:05"));
                add(new FactMessage((long) (Math.random() * Integer.MAX_VALUE), UUID.randomUUID().toString(), (long) (Math.random() * Integer.MAX_VALUE), "SFO", "JFK", "VX", 418, "7:00"));
                add(new FactMessage((long) (Math.random() * Integer.MAX_VALUE), UUID.randomUUID().toString(), (long) (Math.random() * Integer.MAX_VALUE), "SEA", "JFK", "DL", 418, "7:00"));
                add(new FactMessage((long) (Math.random() * Integer.MAX_VALUE), UUID.randomUUID().toString(), (long) (Math.random() * Integer.MAX_VALUE), "SEA", "JFK", "DL", 418, "7:00"));
            }
        };
    }

    public static void main(String[] args) {
        org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<>(props);
        try {
            while (true) {
                int num = (int) (Math.random() * (4));
                producer.send(new ProducerRecord<>("fact-topic", Integer.toString(num), JSON.toJSONString(factList.get(num))));
                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }


}
