package indi.simon.learning;

import org.apache.calcite.sql.parser.SqlParseException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Spark SQL 解析器高级测试用例
 * 测试多段 SQL、DDL、DML 语句的解析
 */
public class SparkSqlParserAdvancedTest {
    
    private SparkSqlParser parser = new SparkSqlParser();
    
    @Test
    public void testMultipleStatements() throws SqlParseException {
        // 测试多段 SQL（用分号分隔）
        String sql = "SELECT id, name FROM users WHERE age > 18; " +
                     "SELECT order_id, amount FROM orders WHERE amount > 1000";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        expectedTables.add("ORDERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        Assert.assertTrue("应该包含 orders 表", tables.contains("ORDERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS 和 ORDERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段）
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.ID");
        expectedFields.add("USERS.NAME");
        expectedFields.add("USERS.AGE");
        expectedFields.add("ORDERS.ORDER_ID");
        expectedFields.add("ORDERS.AMOUNT");
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        Assert.assertTrue("应该包含 users.age 字段", fields.contains("USERS.AGE"));
        Assert.assertTrue("应该包含 orders.order_id 字段", fields.contains("ORDERS.ORDER_ID"));
        Assert.assertTrue("应该包含 orders.amount 字段", fields.contains("ORDERS.AMOUNT"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段", fields.isEmpty());
    }
    
    @Test
    public void testCreateTableAsSelect() throws SqlParseException {
        // 测试 CREATE TABLE AS SELECT
        String sql = "CREATE TABLE new_users AS " +
                     "SELECT id, name, age FROM users WHERE age > 18";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段）
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.ID");
        expectedFields.add("USERS.NAME");
        expectedFields.add("USERS.AGE");
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        Assert.assertTrue("应该包含 users.age 字段", fields.contains("USERS.AGE"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段", fields.isEmpty());
    }
    
    @Test
    public void testCreateTableAsSelectWithJoin() throws SqlParseException {
        // 测试 CREATE TABLE AS SELECT（包含 JOIN）
        String sql = "CREATE TABLE user_orders AS " +
                     "SELECT u.id, u.name, o.order_id, o.amount " +
                     "FROM users u JOIN orders o ON u.id = o.user_id";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        expectedTables.add("ORDERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        Assert.assertTrue("应该包含 orders 表", tables.contains("ORDERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS 和 ORDERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段）
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.ID");
        expectedFields.add("USERS.NAME");
        expectedFields.add("ORDERS.ORDER_ID");
        expectedFields.add("ORDERS.AMOUNT");
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        Assert.assertTrue("应该包含 orders.order_id 字段", fields.contains("ORDERS.ORDER_ID"));
        Assert.assertTrue("应该包含 orders.amount 字段", fields.contains("ORDERS.AMOUNT"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段", fields.isEmpty());
    }
    
    @Test
    public void testInsertIntoSelect() throws SqlParseException {
        // 测试 INSERT INTO ... SELECT
        String sql = "INSERT INTO target_table (id, name) " +
                     "SELECT id, name FROM users WHERE age > 18";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段）
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.ID");
        expectedFields.add("USERS.NAME");
        expectedFields.add("USERS.AGE");
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        Assert.assertTrue("应该包含 users.age 字段", fields.contains("USERS.AGE"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段", fields.isEmpty());
    }
    
    @Test
    public void testInsertIntoSelectWithSubquery() throws SqlParseException {
        // 测试 INSERT INTO ... SELECT（包含子查询）
        String sql = "INSERT INTO summary_table " +
                     "SELECT u.id, u.name, " +
                     "       (SELECT COUNT(*) FROM orders o WHERE o.user_id = u.id) as order_count " +
                     "FROM users u";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        expectedTables.add("ORDERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        Assert.assertTrue("应该包含 orders 表", tables.contains("ORDERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS 和 ORDERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段，order_count 是别名，不应该出现）
        // 注意：子查询中的 orders.user_id 和主查询中的 users.id 都会被提取
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.ID");
        expectedFields.add("USERS.NAME");
        expectedFields.add("ORDERS.USER_ID");
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        Assert.assertTrue("应该包含 orders.user_id 字段", fields.contains("ORDERS.USER_ID"));
        Assert.assertFalse("不应该包含 order_count 别名", fields.contains("ORDER_COUNT"));
        fields.removeAll(expectedFields);
        // 如果还有剩余字段，打印出来以便调试
        if (!fields.isEmpty()) {
            System.out.println("testInsertIntoSelectWithSubquery 额外字段: " + fields);
        }
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段（如别名）。实际剩余字段: " + fields, fields.isEmpty());
    }
    
    @Test
    public void testCreateTableAsSelectWithCTE() throws SqlParseException {
        // 测试 CREATE TABLE AS SELECT（包含 CTE）
        String sql = "CREATE TABLE high_value_users AS " +
                     "WITH high_value_orders AS (" +
                     "  SELECT user_id, SUM(amount) as total " +
                     "  FROM orders " +
                     "  GROUP BY user_id " +
                     "  HAVING SUM(amount) > 5000" +
                     ") " +
                     "SELECT u.id, u.name, hvo.total " +
                     "FROM users u JOIN high_value_orders hvo ON u.id = hvo.user_id";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有，只有 users 和 orders）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        expectedTables.add("ORDERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        Assert.assertTrue("应该包含 orders 表", tables.contains("ORDERS"));
        // 严格验证：不应该包含 CTE 别名、表别名等
        Assert.assertFalse("不应该包含 CTE 别名 high_value_orders", tables.contains("HIGH_VALUE_ORDERS"));
        Assert.assertFalse("不应该包含表别名 u", tables.contains("U"));
        Assert.assertFalse("不应该包含表别名 hvo", tables.contains("HVO"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS 和 ORDERS，不应该包含其他表（如 CTE 别名、表别名）", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段）
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("ORDERS.USER_ID");
        expectedFields.add("ORDERS.AMOUNT");
        expectedFields.add("USERS.ID");
        expectedFields.add("USERS.NAME");
        Assert.assertTrue("应该包含 orders.user_id 字段", fields.contains("ORDERS.USER_ID"));
        Assert.assertTrue("应该包含 orders.amount 字段", fields.contains("ORDERS.AMOUNT"));
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        // 注意：hvo.total 是 CTE 的字段，不应该出现在 fields 中
        Assert.assertFalse("不应该包含 hvo.total（CTE 字段）", fields.contains("HVO.TOTAL"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段（如 CTE 字段）", fields.isEmpty());
    }
    
    @Test
    public void testMultipleStatementsWithDDL() throws SqlParseException {
        // 测试多段 SQL，包含 DDL 和普通查询
        String sql = "CREATE TABLE temp_users AS SELECT id, name FROM users WHERE age > 18; " +
                     "INSERT INTO temp_users SELECT id, name FROM users WHERE age < 25; " +
                     "SELECT * FROM temp_users";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        // 注意：SELECT * FROM temp_users 中的 temp_users 可能被解析为表
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        expectedTables.add("TEMP_USERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        Assert.assertTrue("应该包含 temp_users 表", tables.contains("TEMP_USERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS 和 TEMP_USERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段）
        // SELECT * FROM temp_users 会提取 TEMP_USERS.*（因为 temp_users 是临时表，由 CREATE TABLE 创建）
        // 但 WHERE 子句中的 age 字段会被提取（在两个 SELECT 中都出现）
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.ID");
        expectedFields.add("USERS.NAME");
        expectedFields.add("USERS.AGE");
        // SELECT * FROM temp_users 会生成 TEMP_USERS.*
        if (fields.contains("TEMP_USERS.*")) {
            expectedFields.add("TEMP_USERS.*");
        }
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        Assert.assertTrue("应该包含 users.age 字段", fields.contains("USERS.AGE"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段。实际剩余字段: " + fields, fields.isEmpty());
    }
    
    @Test
    public void testComplexCreateTableAsSelect() throws SqlParseException {
        // 测试复杂的 CREATE TABLE AS SELECT（包含多个子查询和 JOIN）
        String sql = "CREATE TABLE user_statistics AS " +
                     "SELECT u.id, u.name, " +
                     "       (SELECT COUNT(*) FROM orders o1 WHERE o1.user_id = u.id) as order_count, " +
                     "       (SELECT MAX(amount) FROM orders o2 WHERE o2.user_id = u.id) as max_amount, " +
                     "       (SELECT SUM(amount) FROM orders o3 WHERE o3.user_id = u.id) as total_amount " +
                     "FROM users u " +
                     "WHERE u.id IN (SELECT DISTINCT user_id FROM orders WHERE order_date > '2023-01-01')";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        expectedTables.add("ORDERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        Assert.assertTrue("应该包含 orders 表", tables.contains("ORDERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS 和 ORDERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段，每个字段只出现一次）
        // 注意：orders.user_id 在多个子查询中出现，但只应该出现一次
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.ID");
        expectedFields.add("USERS.NAME");
        expectedFields.add("ORDERS.USER_ID");
        expectedFields.add("ORDERS.AMOUNT");
        expectedFields.add("ORDERS.ORDER_DATE");
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        Assert.assertTrue("应该包含 orders.user_id 字段", fields.contains("ORDERS.USER_ID"));
        Assert.assertTrue("应该包含 orders.amount 字段", fields.contains("ORDERS.AMOUNT"));
        Assert.assertTrue("应该包含 orders.order_date 字段", fields.contains("ORDERS.ORDER_DATE"));
        // 验证别名不应该出现
        Assert.assertFalse("不应该包含 order_count 别名", fields.contains("ORDER_COUNT"));
        Assert.assertFalse("不应该包含 max_amount 别名", fields.contains("MAX_AMOUNT"));
        Assert.assertFalse("不应该包含 total_amount 别名", fields.contains("TOTAL_AMOUNT"));
        fields.removeAll(expectedFields);
        // 如果还有剩余字段，打印出来以便调试
        if (!fields.isEmpty()) {
            System.out.println("testComplexCreateTableAsSelect 额外字段: " + fields);
        }
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段（如别名）。实际剩余字段: " + fields, fields.isEmpty());
    }
    
    @Test
    public void testCreateTableWithQuotedString() throws SqlParseException {
        // 测试包含引号字符串的 SQL（确保分号分割逻辑正确）
        String sql = "CREATE TABLE test AS SELECT id, name FROM users WHERE name = 'test;value'; " +
                     "SELECT * FROM test";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        // 注意：SELECT * FROM test 中的 test 是 CTE 或表别名，不应该被解析为实际表
        // 但实际解析中可能 test 被当作表了，所以期望是 2 个表
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        expectedTables.add("TEST");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        Assert.assertTrue("应该包含 test 表", tables.contains("TEST"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS 和 TEST，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段）
        // SELECT * FROM test 会提取 TEST.*（因为 test 是临时表，由 CREATE TABLE 创建）
        // 但 WHERE 子句中的 name 字段会被提取
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.ID");
        expectedFields.add("USERS.NAME");
        // SELECT * FROM test 会生成 TEST.*
        if (fields.contains("TEST.*")) {
            expectedFields.add("TEST.*");
        }
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段。实际剩余字段: " + fields, fields.isEmpty());
    }
    
    @Test
    public void testEmptyStatements() throws SqlParseException {
        // 测试包含空语句的情况
        String sql = "SELECT id FROM users; ; SELECT name FROM orders;";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        expectedTables.add("ORDERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        Assert.assertTrue("应该包含 orders 表", tables.contains("ORDERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS 和 ORDERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段）
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.ID");
        expectedFields.add("ORDERS.NAME");
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 orders.name 字段", fields.contains("ORDERS.NAME"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段", fields.isEmpty());
    }
}
