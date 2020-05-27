package indi.simon.learning;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AirportProducer {

    private static Properties props = new Properties();
    private static List<AirportMessage> airPortList;

    static {
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        airPortList = new ArrayList<AirportMessage>() {
            {
                add(new AirportMessage("JFK", "John F. Kennedy International Airport", "New York"));
                add(new AirportMessage("LAX", "Los Angeles International Airport", "Los Angeles"));
                add(new AirportMessage("SEA", "Seattle-Tacoma International Airport", "Seattle"));
                add(new AirportMessage("SFO", "San Francisco International Airport", "San Francisco"));
            }
        };
    }

    public static void main(String[] args) {
        org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<>(props);
        try {
            while (true) {
                int num = (int) (Math.random() * (4));
                producer.send(new ProducerRecord<>("airport-topic", Integer.toString(num), JSON.toJSONString(airPortList.get(num))));
                Thread.sleep(10000L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }


}
