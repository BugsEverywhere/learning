package indi.simon.learning.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HBase数据操作类
 * 包含数据的增删改查操作
 */
public class HBaseDataOperator {

    private static final Logger logger = LoggerFactory.getLogger(HBaseDataOperator.class);

    // ==================== 写入操作 ====================

    /**
     * 插入单条数据
     *
     * @param tableName    表名
     * @param rowKey       行键
     * @param columnFamily 列族
     * @param column       列名
     * @param value        值
     */
    public static void putData(String tableName, String rowKey, String columnFamily,
                               String column, String value) throws IOException {
        try (Connection connection = HBaseConnectionUtil.getConnection();
             Table table = connection.getTable(TableName.valueOf(tableName))) {

            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
            table.put(put);
            logger.info("数据插入成功: table={}, rowKey={}", tableName, rowKey);
        }
    }

    /**
     * 批量插入数据
     *
     * @param tableName 表名
     * @param puts      Put对象列表
     */
    public static void batchPut(String tableName, List<Put> puts) throws IOException {
        try (Connection connection = HBaseConnectionUtil.getConnection();
             Table table = connection.getTable(TableName.valueOf(tableName))) {

            table.put(puts);
            logger.info("批量插入 {} 条数据成功", puts.size());
        }
    }

    /**
     * 插入一行多列数据
     *
     * @param tableName    表名
     * @param rowKey       行键
     * @param columnFamily 列族
     * @param columns      列名-值 Map
     */
    public static void putRow(String tableName, String rowKey, String columnFamily,
                              Map<String, String> columns) throws IOException {
        try (Connection connection = HBaseConnectionUtil.getConnection();
             Table table = connection.getTable(TableName.valueOf(tableName))) {

            Put put = new Put(Bytes.toBytes(rowKey));
            for (Map.Entry<String, String> entry : columns.entrySet()) {
                put.addColumn(Bytes.toBytes(columnFamily),
                        Bytes.toBytes(entry.getKey()),
                        Bytes.toBytes(entry.getValue()));
            }
            table.put(put);
            logger.info("行数据插入成功: table={}, rowKey={}", tableName, rowKey);
        }
    }

    // ==================== 查询操作 ====================

    /**
     * 根据RowKey获取单条数据
     *
     * @param tableName 表名
     * @param rowKey    行键
     * @return 结果Map
     */
    public static Map<String, String> getByRowKey(String tableName, String rowKey) throws IOException {
        Map<String, String> resultMap = new HashMap<>();

        try (Connection connection = HBaseConnectionUtil.getConnection();
             Table table = connection.getTable(TableName.valueOf(tableName))) {

            Get get = new Get(Bytes.toBytes(rowKey));
            Result result = table.get(get);

            if (result.isEmpty()) {
                logger.info("未找到数据: rowKey={}", rowKey);
                return resultMap;
            }

            for (Cell cell : result.listCells()) {
                String family = Bytes.toString(CellUtil.cloneFamily(cell));
                String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                resultMap.put(family + ":" + qualifier, value);
            }
        }
        return resultMap;
    }

    /**
     * 获取指定列的值
     */
    public static String getColumnValue(String tableName, String rowKey,
                                        String columnFamily, String column) throws IOException {
        try (Connection connection = HBaseConnectionUtil.getConnection();
             Table table = connection.getTable(TableName.valueOf(tableName))) {

            Get get = new Get(Bytes.toBytes(rowKey));
            get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
            Result result = table.get(get);

            if (result.isEmpty()) {
                return null;
            }

            byte[] value = result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
            return value != null ? Bytes.toString(value) : null;
        }
    }

    /**
     * 扫描全表
     *
     * @param tableName 表名
     * @return 结果列表
     */
    public static List<Map<String, String>> scanTable(String tableName) throws IOException {
        return scanTable(tableName, null, null);
    }

    /**
     * 范围扫描
     *
     * @param tableName   表名
     * @param startRowKey 起始行键（包含）
     * @param stopRowKey  结束行键（不包含）
     * @return 结果列表
     */
    public static List<Map<String, String>> scanTable(String tableName,
                                                      String startRowKey, String stopRowKey) throws IOException {
        List<Map<String, String>> results = new ArrayList<>();

        try (Connection connection = HBaseConnectionUtil.getConnection();
             Table table = connection.getTable(TableName.valueOf(tableName))) {

            Scan scan = new Scan();
            if (startRowKey != null) {
                scan.withStartRow(Bytes.toBytes(startRowKey));
            }
            if (stopRowKey != null) {
                scan.withStopRow(Bytes.toBytes(stopRowKey));
            }

            try (ResultScanner scanner = table.getScanner(scan)) {
                for (Result result : scanner) {
                    Map<String, String> row = new HashMap<>();
                    row.put("rowKey", Bytes.toString(result.getRow()));

                    for (Cell cell : result.listCells()) {
                        String family = Bytes.toString(CellUtil.cloneFamily(cell));
                        String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
                        String value = Bytes.toString(CellUtil.cloneValue(cell));
                        row.put(family + ":" + qualifier, value);
                    }
                    results.add(row);
                }
            }
        }
        logger.info("扫描到 {} 条数据", results.size());
        return results;
    }

    /**
     * 使用过滤器扫描 - 前缀过滤器
     *
     * @param tableName  表名
     * @param rowPrefix  行键前缀
     * @return 结果列表
     */
    public static List<Map<String, String>> scanWithPrefixFilter(String tableName,
                                                                  String rowPrefix) throws IOException {
        List<Map<String, String>> results = new ArrayList<>();

        try (Connection connection = HBaseConnectionUtil.getConnection();
             Table table = connection.getTable(TableName.valueOf(tableName))) {

            Scan scan = new Scan();
            PrefixFilter prefixFilter = new PrefixFilter(Bytes.toBytes(rowPrefix));
            scan.setFilter(prefixFilter);

            try (ResultScanner scanner = table.getScanner(scan)) {
                for (Result result : scanner) {
                    Map<String, String> row = new HashMap<>();
                    row.put("rowKey", Bytes.toString(result.getRow()));

                    for (Cell cell : result.listCells()) {
                        String family = Bytes.toString(CellUtil.cloneFamily(cell));
                        String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
                        String value = Bytes.toString(CellUtil.cloneValue(cell));
                        row.put(family + ":" + qualifier, value);
                    }
                    results.add(row);
                }
            }
        }
        return results;
    }

    /**
     * 使用值过滤器扫描
     *
     * @param tableName    表名
     * @param columnFamily 列族
     * @param column       列名
     * @param value        值
     * @return 结果列表
     */
    public static List<Map<String, String>> scanWithValueFilter(String tableName,
                                                                 String columnFamily, String column, String value) throws IOException {
        List<Map<String, String>> results = new ArrayList<>();

        try (Connection connection = HBaseConnectionUtil.getConnection();
             Table table = connection.getTable(TableName.valueOf(tableName))) {

            Scan scan = new Scan();
            SingleColumnValueFilter filter = new SingleColumnValueFilter(
                    Bytes.toBytes(columnFamily),
                    Bytes.toBytes(column),
                    CompareOperator.EQUAL,
                    Bytes.toBytes(value)
            );
            filter.setFilterIfMissing(true);
            scan.setFilter(filter);

            try (ResultScanner scanner = table.getScanner(scan)) {
                for (Result result : scanner) {
                    Map<String, String> row = new HashMap<>();
                    row.put("rowKey", Bytes.toString(result.getRow()));

                    for (Cell cell : result.listCells()) {
                        String family = Bytes.toString(CellUtil.cloneFamily(cell));
                        String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
                        String val = Bytes.toString(CellUtil.cloneValue(cell));
                        row.put(family + ":" + qualifier, val);
                    }
                    results.add(row);
                }
            }
        }
        return results;
    }

    // ==================== 删除操作 ====================

    /**
     * 删除一行数据
     */
    public static void deleteRow(String tableName, String rowKey) throws IOException {
        try (Connection connection = HBaseConnectionUtil.getConnection();
             Table table = connection.getTable(TableName.valueOf(tableName))) {

            Delete delete = new Delete(Bytes.toBytes(rowKey));
            table.delete(delete);
            logger.info("删除成功: rowKey={}", rowKey);
        }
    }

    /**
     * 删除指定列
     */
    public static void deleteColumn(String tableName, String rowKey,
                                    String columnFamily, String column) throws IOException {
        try (Connection connection = HBaseConnectionUtil.getConnection();
             Table table = connection.getTable(TableName.valueOf(tableName))) {

            Delete delete = new Delete(Bytes.toBytes(rowKey));
            delete.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
            table.delete(delete);
            logger.info("删除列成功: rowKey={}, column={}:{}", rowKey, columnFamily, column);
        }
    }

    /**
     * 批量删除
     */
    public static void batchDelete(String tableName, List<String> rowKeys) throws IOException {
        try (Connection connection = HBaseConnectionUtil.getConnection();
             Table table = connection.getTable(TableName.valueOf(tableName))) {

            List<Delete> deletes = new ArrayList<>();
            for (String rowKey : rowKeys) {
                deletes.add(new Delete(Bytes.toBytes(rowKey)));
            }
            table.delete(deletes);
            logger.info("批量删除 {} 条数据成功", rowKeys.size());
        }
    }
}
