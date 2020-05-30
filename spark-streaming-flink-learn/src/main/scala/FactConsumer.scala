
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import com.alibaba.fastjson.{JSON, JSONException, JSONObject}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Durations, StreamingContext}

object FactConsumer {

  def main(args: Array[String]) {

    //setStreamingLogLevels()
    val sparkConf = new SparkConf().setAppName("QueueStream").setMaster("local")
    // Create the context
    val ssc = new StreamingContext(sparkConf, Seconds(1))

    val factTopicsSet = Set("fact-topic")
    val airPortTopicSet = Set("airport-topic")

    val brokers = "localhost:9092"
    val kafkaParams = Map[String, String](
      "bootstrap.servers" -> brokers,
      "group.id" -> "new_group",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> "false",
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer")

    val factInputStream: DStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](factTopicsSet, kafkaParams))
    val airportTopicStream: DStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](airPortTopicSet, kafkaParams))

    val factTransferredStream = factInputStream
      .map(item => parseJson(item.value()))
      .map(item => {
        val sourceAirportMark = item.getString("sourceAirportMark")
        val airlineCompanyShortName = item.getString("airlineCompanyShortName")
        val startTime = item.getString("startTime")
        (sourceAirportMark, (sourceAirportMark, airlineCompanyShortName, startTime))
      })

    factInputStream.foreachRDD(_.foreach(s =>
      println("fact stream!!!" + s)))


    val airportTransferredStream = airportTopicStream
      .map(item => parseJson(item.value()))
      .map(item => {
        val airportMark = item.getString("airportMark")
        val airportName = item.getString("airportName")
        val location = item.getString("location")
        (airportMark, (airportMark, airportName, location))
      })

    airportTopicStream.foreachRDD(_.foreach(s =>
      println("airport stream!!!" + s)))


    val joinedStream = factTransferredStream.join(airportTransferredStream)

    joinedStream.foreachRDD(_.foreach(s =>
      println("yes here I am!" + s)
    ))

    //    factInputStream.foreachRDD(rdd => {
    //      rdd.foreach(s => {
    //        print("yes here I am!" + "k:" + s.key() + ", v:" + s.value())
    //      })
    //    })


    ssc.start()
    ssc.awaitTermination()

  }

  def parseJson(log: String): JSONObject = {
    var ret: JSONObject = null
    try {
      ret = JSON.parseObject(log)
    } catch {
      //异常json数据处理
      case e: JSONException => println(log)
    }
    ret
  }

}
