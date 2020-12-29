package indi.simon.learning.kafkalearn.service;

import indi.simon.learning.kafkalearn.KafkaProducerServer;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author chenzhuo(zhiyue)
 */
@Service
public class ServiceInterfaceImpl implements IServiceInterface {

    @Autowired
    private KafkaProducerServer kafkaProducerServer;

    private static final String[] COLORS = new String[]{
            "red",
            "blue",
            "yellow",
            "green",
            "white",
            "black",
            "grey"
    };


    @Override
    public String sayHelloAgain() {
        return "hello world!";
    }

    @Override
    public void startProduce(String topic) {
        List<PartitionInfo> partitionInfos = kafkaProducerServer.getPartitionsFromKafka(topic);
        for (PartitionInfo partitionInfo : partitionInfos) {
            Random random = new Random();
            int randomColorIndex = random.nextInt(COLORS.length);
            String colorStr = COLORS[randomColorIndex];
            kafkaProducerServer.sndMesForTemplate(topic, colorStr, String.valueOf(random.nextInt()), 1);
        }


    }
}
