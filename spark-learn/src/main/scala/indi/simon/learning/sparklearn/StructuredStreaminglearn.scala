package indi.simon.learning.sparklearn

import org.apache.spark.sql.SparkSession

/**
  * Created with IntelliJ IDEA.
  * User: Simon
  * Date:
  * Time:
  */
object StructuredStreaminglearn {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder
      .appName("StructuredNetworkWordCount")
      .getOrCreate()

    val lines = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()

    lines.isStreaming    // Returns True for DataFrames that have streaming sources
    lines.printSchema

    // Split the lines into words
//    //val words = lines.as[String].flatMap(_.split(" "))
//
//    // Generate running word count
//    val wordCounts = words.groupBy("value").count()
//
//    // Start running the query that prints the running counts to the console
//    val query = wordCounts.writeStream
//      .outputMode("complete")
//      .format("console")
//      .start()
//
//    query.awaitTermination()

  }


}
