package indi.simon.learning.hbase;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * HBase表操作类
 * 包含创建表、删除表、插入数据、查询数据等操作
 */
public class HBaseTableOperator {

    private static final Logger logger = LoggerFactory.getLogger(HBaseTableOperator.class);

    /**
     * 创建表
     *
     * @param tableName      表名
     * @param columnFamilies 列族数组
     */
    public static void createTable(String tableName, String[] columnFamilies) throws IOException {
        try (Connection connection = HBaseConnectionUtil.getConnection();
             Admin admin = connection.getAdmin()) {

            TableName table = TableName.valueOf(tableName);

            if (admin.tableExists(table)) {
                logger.warn("表 {} 已存在", tableName);
                return;
            }

            TableDescriptorBuilder tableBuilder = TableDescriptorBuilder.newBuilder(table);

            for (String cf : columnFamilies) {
                ColumnFamilyDescriptor columnFamily = ColumnFamilyDescriptorBuilder
                        .newBuilder(Bytes.toBytes(cf))
                        .setMaxVersions(3)  // 保留3个版本
                        .build();
                tableBuilder.setColumnFamily(columnFamily);
            }

            admin.createTable(tableBuilder.build());
            logger.info("表 {} 创建成功", tableName);
        }
    }

    /**
     * 删除表
     */
    public static void deleteTable(String tableName) throws IOException {
        try (Connection connection = HBaseConnectionUtil.getConnection();
             Admin admin = connection.getAdmin()) {

            TableName table = TableName.valueOf(tableName);

            if (!admin.tableExists(table)) {
                logger.warn("表 {} 不存在", tableName);
                return;
            }

            // 先禁用表
            if (admin.isTableEnabled(table)) {
                admin.disableTable(table);
            }
            // 再删除表
            admin.deleteTable(table);
            logger.info("表 {} 删除成功", tableName);
        }
    }

    /**
     * 列出所有表
     */
    public static List<String> listTables() throws IOException {
        List<String> tableList = new ArrayList<>();
        try (Connection connection = HBaseConnectionUtil.getConnection();
             Admin admin = connection.getAdmin()) {

            TableName[] tableNames = admin.listTableNames();
            for (TableName tableName : tableNames) {
                tableList.add(tableName.getNameAsString());
            }
        }
        return tableList;
    }

    /**
     * 检查表是否存在
     */
    public static boolean tableExists(String tableName) throws IOException {
        try (Connection connection = HBaseConnectionUtil.getConnection();
             Admin admin = connection.getAdmin()) {
            return admin.tableExists(TableName.valueOf(tableName));
        }
    }
}
