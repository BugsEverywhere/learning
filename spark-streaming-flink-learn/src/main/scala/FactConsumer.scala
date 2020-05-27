import org.apache.commons.codec.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object FactConsumer {

  def main(args: Array[String]) {

    //setStreamingLogLevels()
    val sparkConf = new SparkConf().setAppName("QueueStream").setMaster("local")
    // Create the context
    val ssc = new StreamingContext(sparkConf, Seconds(1))

    val topicsSet = Set("fact-topic")
    val brokers = "localhost:9092"
    val kafkaParams = Map[String, String](
      "bootstrap.servers" -> brokers,
      "group.id" -> "new_group",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> "false",
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer")

    val inputStream: DStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](topicsSet, kafkaParams))

    inputStream.foreachRDD(rdd => {
      rdd.foreach(s => {
        print("yes here I am!" + "k:" + s.key() + ", v:" + s.value())
      })
    })


    ssc.start()
    ssc.awaitTermination()

  }

}
