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
//                .inBatchMode()
                .build();

        TableEnvironment tableEnv = TableEnvironment.create(settings);

        //=========================================================================================Table API 方式注册源表，view表转换，写入目标表

        final Schema schema = new Schema()
                .field("a", DataTypes.INT())
                .field("b", DataTypes.STRING())
                .field("c", DataTypes.BIGINT());

        //从文件系统注册源表，connect TABLE表
        tableEnv.connect(new FileSystem().path("/path/to/sourceFile"))
                .withFormat(new Csv().fieldDelimiter('|').deriveSchema())
                .withSchema(schema)
                .createTemporaryTable("CsvSourceTable");

        //Table API方式对源表进行查询获得结果，得到view表
        Table tableApiResult = tableEnv.from("CsvSourceTable").select("...");

        //从文件系统注册目标表，也是connect TABLE表
        tableEnv.connect(new FileSystem().path("/path/to/sinkFile"))
                .withFormat(new Csv().fieldDelimiter('|').deriveSchema())
                .withSchema(schema)
                .createTemporaryTable("CsvSinkTable");

        //结果写目标表
        //executeInsert就触发执行了
        TableResult apiResult = tableApiResult.executeInsert("CsvSinkTable");


        //=========================================================================================SQL API 方式注册源表，view表转换，写入目标表

        //注册源表，connect TABLE表，具体使用哪一种connector决定了从哪里获取源表，比如kafka，文件系统等等
        tableEnv.executeSql("CREATE TEMPORARY TABLE sqlApiSourceTable ... WITH ( 'connector' = ... )");

        //注册目标表，connect TABLE表
        tableEnv.executeSql("CREATE TEMPORARY TABLE sqlApiSinkTable ... WITH ( 'connector' = ... )");

        //计算结果并写目标表，方式一，executeSql就触发执行了
        tableEnv.executeSql(
                "INSERT INTO sqlApiSinkTable " +
                        "SELECT cID, cName, SUM(revenue) AS revSum " +
                        "FROM sqlApiSourceTable " +
                        "WHERE cCountry = 'FRANCE' " +
                        "GROUP BY cID, cName"
        );


        //计算结果并写目标表，方式二，通过Table对象使用Table API来写结果表
        Table sqlApiResult = tableEnv.sqlQuery("SELECT ... FROM sqlApiSourceTable ... ");
        TableResult sqlResult = sqlApiResult.executeInsert("CsvSinkTable");

        //==========================================================================================使用SQL API查询TABLE API的表

        //将TABLE API的Table对象注册为view表
        tableEnv.createTemporaryView("tableApiResult", tableApiResult);
        //然后再使用sql API去查询
        Table sqlQueryTableApi = tableEnv.sqlQuery(
                "SELECT cID, cName, SUM(revenue) AS revSum " +
                        "FROM tableApiResult " +
                        "WHERE cCountry = 'FRANCE' " +
                        "GROUP BY cID, cName"
        );

    }


}
