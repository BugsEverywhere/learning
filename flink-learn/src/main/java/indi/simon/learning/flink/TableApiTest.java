package indi.simon.learning.flink;

import org.apache.flink.table.api.*;
import org.apache.flink.table.descriptors.Csv;
import org.apache.flink.table.descriptors.FileSystem;
import org.apache.flink.table.descriptors.Schema;

/**
 * @author chenzhuo(zhiyue)
 */
public class TableApiTest {

    public static void main(String[] args) {

        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .inStreamingMode()
                //.inBatchMode()
                .build();

        TableEnvironment tableEnv = TableEnvironment.create(settings);

        //===============================================================Table API 方式注册源表，view表转换，写入目标表

        final Schema schema = new Schema()
                .field("a", DataTypes.INT())
                .field("b", DataTypes.STRING())
                .field("c", DataTypes.BIGINT());

        //从文件系统注册源表，connect表
        tableEnv.connect(new FileSystem().path("/path/to/sourceFile"))
                .withFormat(new Csv().fieldDelimiter('|').deriveSchema())
                .withSchema(schema)
                .createTemporaryTable("CsvSourceTable");

        //Table API方式对源表进行查询获得结果，得到view表
        Table tableApiResult = tableEnv.from("CsvSourceTable").select("...");

        //从文件系统注册目标表，也是connect表
        tableEnv.connect(new FileSystem().path("/path/to/sinkFile"))
                .withFormat(new Csv().fieldDelimiter('|').deriveSchema())
                .withSchema(schema)
                .createTemporaryTable("CsvSinkTable");

        //结果写目标表
        TableResult result = tableApiResult.executeInsert("CsvSinkTable");


        //===============================================================SQL API 方式注册源表，view表转换，写入目标表

        //注册源表，connect表，具体使用哪一种connector决定了从哪里获取源表，比如kafka，文件系统等等
        tableEnv.executeSql("CREATE TEMPORARY TABLE sqlApiSourceTable ... WITH ( 'connector' = ... )");

        //Sql API方式对源表进行查询获得结果，得到view表
        Table sqlApiResult = tableEnv.sqlQuery("SELECT ... FROM table1 ... ");

        //注册目标表，connect表
        tableEnv.executeSql("CREATE TEMPORARY TABLE sqlApiSinkTable ... WITH ( 'connector' = ... )");

        //结果写目标表
        tableEnv.executeSql(
                "INSERT INTO sqlApiSinkTable " +
                        "SELECT cID, cName, SUM(revenue) AS revSum " +
                        "FROM sqlApiResult " +
                        "WHERE cCountry = 'FRANCE' " +
                        "GROUP BY cID, cName"
        );


    }


}
