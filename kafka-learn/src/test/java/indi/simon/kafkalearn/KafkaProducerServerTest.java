package indi.simon.kafkalearn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context.xml")
public class KafkaProducerServerTest {

    @Autowired
    private KafkaProducerServer kafkaProducerServer;

    @Test
    public void sndMesForTemplate() {

        String topic = "orderTopic";
        String value = "test";
        String ifPartition = "0";
        String role = "test";//用来生成key
        Map<String,Object> res = kafkaProducerServer.sndMesForTemplate
                (topic, value, ifPartition, role);

        System.out.println("测试结果如下：===============");
        String message = (String)res.get("message");
        String code = (String)res.get("code");

        System.out.println("code:"+code);
        System.out.println("message:"+message);




    }
}