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
    println("checkpoint0")

    // Create the queue through which RDDs can be pushed to
    // a QueueInputDStream
    //val rddQueue = new mutable.Queue[RDD[Int]]()

    // Create the QueueInputDStream and use it do some processing
    //    val inputStream = ssc.queueStream(rddQueue)
    //    val mappedStream = inputStream.map(x => (x % 10, 1))
    //    val reducedStream = mappedStream.reduceByKey(_ + _)
    //    reducedStream.print()
    //    ssc.start()

    // Create and push some RDDs into rddQueue
    //    for (i <- 1 to 30) {
    //      rddQueue.synchronized {
    //        rddQueue += ssc.sparkContext.makeRDD(1 to 1000, 10)
    //      }
    //      Thread.sleep(1000)
    //    }
    //    val topicsSet = Set("xlog_apm")
    //    val brokers = "storage8.test.lan:9092,storage9.test.lan:9092,storage10.test.lan:9092"
    //    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers,
    //      "serializer.class" -> "kafka.serializer.StringDecoder")

    val topicsSet = Set("test")
    val brokers = "simonMac:9092"
    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers,
      "serializer.class" -> "kafka.serializer.StringDecoder")
    println("checkpoint1")

    val inputStream: DStream[(String, String)] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicsSet)

    inputStream.foreachRDD(rdd => {
      rdd.foreach(s => {
        println("checkpoint3")
        print("k:" + s._1 + ", v:" + s._2)
      })
    })
    //inputStream.join()


    ssc.start()
    ssc.awaitTermination()

  }
}
