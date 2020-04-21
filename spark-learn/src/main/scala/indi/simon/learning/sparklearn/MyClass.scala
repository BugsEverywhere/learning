package indi.simon.learning.sparklearn

import org.apache.spark.rdd.RDD

/**
  * Created with IntelliJ IDEA.
  * User: Simon
  * Date: 
  * Time: 
  */
class MyClass {

  def func1(s: String): String = {
    ""
  }

  def main(args: Array[String]):Unit = {

    val field = "Hello"
    def doStuff(rdd: RDD[String]): RDD[String] = { rdd.map(x => field + x) }

    val value2 = new MyClass


  }

}
