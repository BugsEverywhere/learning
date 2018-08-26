package indi.simon.sparklearn

import org.apache.spark.sql.SparkSession

/**
  * Created with IntelliJ IDEA.
  * User: Simon
  * Date: 
  * Time: 
  */
object SparkSQLearn {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local").appName("firstSparkSqlApp").getOrCreate()

    val sQLContext = spark.sqlContext

    val dataFrame = spark.read.format("json").load("hdfs://localhost:9000/testJson")

    dataFrame.createOrReplaceTempView("data")

    val propJson = spark.sql("select props from data limit 100")

    propJson.write.format("text").save("hdfs://localhost:9000/output/")



  }

}
