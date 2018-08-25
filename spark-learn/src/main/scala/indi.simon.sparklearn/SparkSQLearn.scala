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

    val spark = SparkSession.builder().master("local").appName("firstSparkSqlApp").getOrCreate();

    val sQLContext = spark.sqlContext;





  }

}
