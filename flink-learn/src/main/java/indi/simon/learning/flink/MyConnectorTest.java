package indi.simon.learning.flink;

import indi.simon.learning.models.MessageModel;
import indi.simon.learning.source.ShitHappenSource;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author chenzhuo(zhiyue)
 */
public class MyConnectorTest {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        DataStream<MessageModel> events = env.fromSource(new ShitHappenSource(), WatermarkStrategy.noWatermarks(), "mySource");

        events.print();

        env.execute();

    }
}
