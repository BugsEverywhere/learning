package indi.simon.learning.sparklearn

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created with IntelliJ IDEA.
  * User: Simon
  * Date:
  * Time:
  */
object SparkStreamingLearn {

  def main(args: Array[String]) {

    //setStreamingLogLevels()
    val sparkConf = new SparkConf().setAppName("QueueStream")
    // Create the context
    val ssc = new StreamingContext(sparkConf, Seconds(1))

    val topicsSet = Set("test")
    val brokers = "localhost:9092"
    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers,
      "serializer.class" -> "kafka.serializer.StringDecoder")

    val inputStream: DStream[(String, String)] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicsSet)

    inputStream.foreachRDD(rdd => {
      rdd.foreach(s => {
        print("k:" + s._1 + ", v:" + s._2)
      })
    })


    ssc.start()
    ssc.awaitTermination()

  }
}
