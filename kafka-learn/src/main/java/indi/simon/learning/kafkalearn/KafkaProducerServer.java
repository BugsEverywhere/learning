package indi.simon.learning.kafkalearn;

import com.alibaba.fastjson.JSON;
import indi.simon.learning.kafkalearn.constants.KafkaMesConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
@Component
public class KafkaProducerServer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * kafka发送消息模板
     *
     * @param topic 主题
     * @param value messageValue
     */
    public Map<String, Object> sndMesForTemplate(String topic, String key, String value) {
        String valueString = JSON.toJSONString(value);
        //表示使用分区
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, 0, key, valueString);
        return checkProRecord(result);
    }


    private Map<String, Object> checkProRecord(ListenableFuture<SendResult<String, String>> res) {
        Map<String, Object> m = new HashMap<String, Object>();
        if (res != null) {
            try {
                SendResult<String, String> r = res.get();//检查result结果集
                /*检查recordMetadata的offset数据，不检查producerRecord*/
                long offsetIndex = r.getRecordMetadata().offset();
                if (offsetIndex >= 0) {
                    m.put("code", KafkaMesConstant.SUCCESS_CODE);
                    m.put("message", KafkaMesConstant.SUCCESS_MES);
                    return m;
                } else {
                    m.put("code", KafkaMesConstant.KAFKA_NO_OFFSET_CODE);
                    m.put("message", KafkaMesConstant.KAFKA_NO_OFFSET_MES);
                    return m;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                m.put("code", KafkaMesConstant.KAFKA_SEND_ERROR_CODE);
                m.put("message", KafkaMesConstant.KAFKA_SEND_ERROR_MES);
                return m;
            }
        } else {
            m.put("code", KafkaMesConstant.KAFKA_NO_RESULT_CODE);
            m.put("message", KafkaMesConstant.KAFKA_NO_RESULT_MES);
            return m;
        }
    }


}
