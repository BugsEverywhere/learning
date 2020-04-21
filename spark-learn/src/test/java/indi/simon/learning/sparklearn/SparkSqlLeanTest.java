package indi.simon.sparklearn;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class SparkSqlLeanTest {

    public static void main(String[] args) throws IOException {

        File file = new File("/Users/nali/Desktop/spark练习用的json/small.json");
        File outputFile = new File("/Users/nali/Desktop/spark练习用的json/small.json");
        outputFile.createNewFile();
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        Long fileLength = file.length();
        byte[] buf = new byte[fileLength.intValue()];
        inputStream.read(buf);
        inputStream.close();
        String content = new String(buf,"UTF-8");
        content=content.replaceAll(" ","");
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(content);
        writer.close();

    }

}