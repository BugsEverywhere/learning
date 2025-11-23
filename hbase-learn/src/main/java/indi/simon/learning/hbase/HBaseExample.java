package indi.simon.learning.hbase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HBase操作示例
 * 演示如何使用Java API操作HBase
 *
 * 使用前请确保:
 * 1. HBase集群已启动
 * 2. ZooKeeper已启动
 * 3. 修改HBaseConnectionUtil中的ZK配置
 */
public class HBaseExample {

    private static final String TABLE_NAME = "test_user";
    private static final String CF_INFO = "info";
    private static final String CF_CONTACT = "contact";

    public static void main(String[] args) {
        try {
            // 1. 创建表
            createTableDemo();

            // 2. 插入数据
            insertDataDemo();

            // 3. 查询数据
            queryDataDemo();

            // 4. 扫描数据
            scanDataDemo();

            // 5. 删除数据
            // deleteDataDemo();

            // 6. 删除表
            // deleteTableDemo();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            HBaseConnectionUtil.closeConnection();
        }
    }

    /**
     * 创建表示例
     */
    private static void createTableDemo() throws IOException {
        System.out.println("========== 创建表 ==========");

        // 检查表是否存在
        if (HBaseTableOperator.tableExists(TABLE_NAME)) {
            System.out.println("表已存在: " + TABLE_NAME);
            return;
        }

        // 创建带有两个列族的表
        String[] columnFamilies = {CF_INFO, CF_CONTACT};
        HBaseTableOperator.createTable(TABLE_NAME, columnFamilies);

        // 列出所有表
        List<String> tables = HBaseTableOperator.listTables();
        System.out.println("当前所有表: " + tables);
    }

    /**
     * 插入数据示例
     */
    private static void insertDataDemo() throws IOException {
        System.out.println("\n========== 插入数据 ==========");

        // 插入单条数据
        HBaseDataOperator.putData(TABLE_NAME, "user001", CF_INFO, "name", "张三");
        HBaseDataOperator.putData(TABLE_NAME, "user001", CF_INFO, "age", "25");
        HBaseDataOperator.putData(TABLE_NAME, "user001", CF_CONTACT, "phone", "13800138000");
        HBaseDataOperator.putData(TABLE_NAME, "user001", CF_CONTACT, "email", "zhangsan@example.com");

        // 插入一行多列数据
        Map<String, String> user2Info = new HashMap<>();
        user2Info.put("name", "李四");
        user2Info.put("age", "30");
        user2Info.put("city", "北京");
        HBaseDataOperator.putRow(TABLE_NAME, "user002", CF_INFO, user2Info);

        Map<String, String> user2Contact = new HashMap<>();
        user2Contact.put("phone", "13900139000");
        user2Contact.put("email", "lisi@example.com");
        HBaseDataOperator.putRow(TABLE_NAME, "user002", CF_CONTACT, user2Contact);

        // 插入更多数据用于扫描测试
        HBaseDataOperator.putData(TABLE_NAME, "user003", CF_INFO, "name", "王五");
        HBaseDataOperator.putData(TABLE_NAME, "user003", CF_INFO, "age", "28");

        System.out.println("数据插入完成");
    }

    /**
     * 查询数据示例
     */
    private static void queryDataDemo() throws IOException {
        System.out.println("\n========== 查询数据 ==========");

        // 根据RowKey查询整行
        System.out.println("查询 user001 的所有数据:");
        Map<String, String> user1Data = HBaseDataOperator.getByRowKey(TABLE_NAME, "user001");
        for (Map.Entry<String, String> entry : user1Data.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        // 查询指定列
        System.out.println("\n查询 user002 的姓名:");
        String name = HBaseDataOperator.getColumnValue(TABLE_NAME, "user002", CF_INFO, "name");
        System.out.println("  name = " + name);
    }

    /**
     * 扫描数据示例
     */
    private static void scanDataDemo() throws IOException {
        System.out.println("\n========== 扫描数据 ==========");

        // 全表扫描
        System.out.println("全表扫描:");
        List<Map<String, String>> allData = HBaseDataOperator.scanTable(TABLE_NAME);
        for (Map<String, String> row : allData) {
            System.out.println("  " + row);
        }

        // 范围扫描
        System.out.println("\n范围扫描 (user001 ~ user003):");
        List<Map<String, String>> rangeData = HBaseDataOperator.scanTable(TABLE_NAME, "user001", "user003");
        for (Map<String, String> row : rangeData) {
            System.out.println("  " + row);
        }

        // 前缀扫描
        System.out.println("\n前缀扫描 (user):");
        List<Map<String, String>> prefixData = HBaseDataOperator.scanWithPrefixFilter(TABLE_NAME, "user");
        for (Map<String, String> row : prefixData) {
            System.out.println("  " + row);
        }
    }

    /**
     * 删除数据示例
     */
    private static void deleteDataDemo() throws IOException {
        System.out.println("\n========== 删除数据 ==========");

        // 删除指定列
        HBaseDataOperator.deleteColumn(TABLE_NAME, "user001", CF_INFO, "age");
        System.out.println("已删除 user001 的 age 列");

        // 删除整行
        HBaseDataOperator.deleteRow(TABLE_NAME, "user003");
        System.out.println("已删除 user003 整行");
    }

    /**
     * 删除表示例
     */
    private static void deleteTableDemo() throws IOException {
        System.out.println("\n========== 删除表 ==========");
        HBaseTableOperator.deleteTable(TABLE_NAME);
    }
}
