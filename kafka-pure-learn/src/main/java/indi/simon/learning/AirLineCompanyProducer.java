package indi.simon.learning;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AirLineCompanyProducer {
    private static Properties props = new Properties();
    private static List<AirLineCompanyMessage> airLineCompanyList;

    static {
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        airLineCompanyList = new ArrayList<AirLineCompanyMessage>() {
            {
                add(new AirLineCompanyMessage("AA", "American Airlines"));
                add(new AirLineCompanyMessage("DL", "Delta Airlines"));
                add(new AirLineCompanyMessage("VX", "Virgin America"));
            }
        };
    }

    public static void main(String[] args) {
        org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<>(props);
        try {
            while (true) {
                int num = (int) (Math.random() * (3));
                producer.send(new ProducerRecord<>("company-topic", Integer.toString(num), JSON.toJSONString(airLineCompanyList.get(num))));
                Thread.sleep(10000L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }


}
