package indi.simon.sparklearn;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class WordCount {

    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JavaWordCount");
        System.setProperty("hadoop.home.dir", "D:\\development\\hadoop-common-2.2.0-bin-master");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);

        //String filePath = "hdfs://master:9000/test/README.txt";
        String filePath = "C:\\Users\\SimonsDesk\\Desktop\\test\\好看的博客.txt";
        JavaRDD<String> lines = ctx.textFile(filePath);

        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(s.split(" ")).iterator());

        JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));

        JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);

        List<Tuple2<String, Integer>> output = counts.collect();

        for (Tuple2<?,?> tuple : output) {
            System.out.println(tuple._1() + ": " + tuple._2());
        }

        ctx.stop();

    }


}
