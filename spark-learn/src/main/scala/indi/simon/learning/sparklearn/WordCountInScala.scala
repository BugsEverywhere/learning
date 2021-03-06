package indi.simon.learning.sparklearn

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created with IntelliJ IDEA.
  * User: Simon
  * Date:
  * Time:
  */
object WordCountInScala {

  def main(args: Array[String]):Unit = {

    val sparkConf = new SparkConf().setMaster("local").setAppName("MyScalaWordCount")

    System.setProperty("hadoop.home.dir", "D:\\development\\hadoop-common-2.2.0-bin-master")

    val sc = new SparkContext(sparkConf)

    val rowRdd = sc.textFile("C:\\Users\\SimonsDesk\\Desktop\\test\\好看的博客.txt");

    val resultRdd = rowRdd.flatMap(rows=>rows.split("\\s+")).map{word=>(word,1)}.reduceByKey(_+_)

    resultRdd.saveAsTextFile("C:\\Users\\SimonsDesk\\Desktop\\test\\result")

    //=====================================

    def m1(f:(Int,Int) => Int) : Int = {
      f(2,6)
    }

    val f1 = (x:Int,y:Int) => x + y

    val m2 = (m:Int,n:Int) => {
      m+n
    }




  }
}
