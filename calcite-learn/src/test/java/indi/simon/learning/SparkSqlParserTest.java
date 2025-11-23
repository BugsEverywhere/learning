package indi.simon.learning;

import org.apache.calcite.sql.parser.SqlParseException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Spark SQL 解析器测试用例
 */
public class SparkSqlParserTest {
    
    private SparkSqlParser parser = new SparkSqlParser();
    
    @Test
    public void testSimpleQuery() throws SqlParseException {
        String sql = "SELECT id, name, age FROM users WHERE age > 18";
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
        Assert.assertTrue("fields 应该只有 USERS.ID, USERS.NAME, USERS.AGE，不应该包含其他字段", fields.isEmpty());
    }
    
    @Test
    public void testJoinQuery() throws SqlParseException {
        String sql = "SELECT u.id, u.name, o.order_id, o.amount " +
                     "FROM users u JOIN orders o ON u.id = o.user_id " +
                     "WHERE u.age > 25";
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
        expectedFields.add("USERS.AGE");
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        Assert.assertTrue("应该包含 orders.order_id 字段", fields.contains("ORDERS.ORDER_ID"));
        Assert.assertTrue("应该包含 orders.amount 字段", fields.contains("ORDERS.AMOUNT"));
        Assert.assertTrue("应该包含 users.age 字段", fields.contains("USERS.AGE"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段", fields.isEmpty());
    }
    
    @Test
    public void testSubquery() throws SqlParseException {
        String sql = "SELECT name, age FROM users " +
                     "WHERE id IN (SELECT user_id FROM orders WHERE amount > 1000)";
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
        expectedFields.add("USERS.NAME");
        expectedFields.add("USERS.AGE");
        expectedFields.add("USERS.ID");
        expectedFields.add("ORDERS.USER_ID");
        expectedFields.add("ORDERS.AMOUNT");
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        Assert.assertTrue("应该包含 users.age 字段", fields.contains("USERS.AGE"));
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 orders.user_id 字段", fields.contains("ORDERS.USER_ID"));
        Assert.assertTrue("应该包含 orders.amount 字段", fields.contains("ORDERS.AMOUNT"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段", fields.isEmpty());
    }
    
    @Test
    public void testCTE() throws SqlParseException {
        String sql = "WITH high_value_orders AS (" +
                     "  SELECT user_id, SUM(amount) as total " +
                     "  FROM orders " +
                     "  GROUP BY user_id " +
                     "  HAVING SUM(amount) > 5000" +
                     ") " +
                     "SELECT u.name, hvo.total " +
                     "FROM users u JOIN high_value_orders hvo ON u.id = hvo.user_id";
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
        // 注意：hvo.total 是 CTE 的字段，不应该出现在 fields 中（因为 CTE 不是原始表）
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("ORDERS.USER_ID");
        expectedFields.add("ORDERS.AMOUNT");
        expectedFields.add("USERS.NAME");
        Assert.assertTrue("应该包含 orders.user_id 字段", fields.contains("ORDERS.USER_ID"));
        Assert.assertTrue("应该包含 orders.amount 字段", fields.contains("ORDERS.AMOUNT"));
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段（如 CTE 字段）", fields.isEmpty());
    }
    
    @Test
    public void testCaseWhen() throws SqlParseException {
        String sql = "SELECT id, name, " +
                     "CASE WHEN age < 18 THEN '未成年' " +
                     "     WHEN age < 60 THEN '成年' " +
                     "     ELSE '老年' END as age_group " +
                     "FROM users";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段，age_group 是别名，不应该出现）
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.ID");
        expectedFields.add("USERS.NAME");
        expectedFields.add("USERS.AGE");
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        Assert.assertTrue("应该包含 users.age 字段", fields.contains("USERS.AGE"));
        Assert.assertFalse("不应该包含 age_group 别名", fields.contains("AGE_GROUP"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段（如别名）", fields.isEmpty());
    }
    
    @Test
    public void testComplexNestedQuery() throws SqlParseException {
        String sql = "SELECT u.name, " +
                     "       (SELECT COUNT(*) FROM orders o WHERE o.user_id = u.id) as order_count, " +
                     "       (SELECT MAX(amount) FROM orders o2 WHERE o2.user_id = u.id) as max_amount " +
                     "FROM users u " +
                     "WHERE u.id IN (" +
                     "  SELECT DISTINCT user_id FROM orders WHERE order_date > '2023-01-01'" +
                     ")";
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
        expectedFields.add("USERS.NAME");
        expectedFields.add("ORDERS.USER_ID");
        expectedFields.add("USERS.ID");
        expectedFields.add("ORDERS.AMOUNT");
        expectedFields.add("ORDERS.ORDER_DATE");
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        Assert.assertTrue("应该包含 orders.user_id 字段", fields.contains("ORDERS.USER_ID"));
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 orders.amount 字段", fields.contains("ORDERS.AMOUNT"));
        Assert.assertTrue("应该包含 orders.order_date 字段", fields.contains("ORDERS.ORDER_DATE"));
        fields.removeAll(expectedFields);
        // 如果还有剩余字段，打印出来以便调试
        if (!fields.isEmpty()) {
            System.out.println("testComplexNestedQuery 额外字段: " + fields);
        }
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段。实际剩余字段: " + fields, fields.isEmpty());
    }
    
    @Test
    public void testUnionQuery() throws SqlParseException {
        String sql = "SELECT id, name FROM users WHERE age < 18 " +
                     "UNION " +
                     "SELECT id, name FROM users WHERE age > 60";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段，每个字段只出现一次）
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
    public void testSchemaTableField() throws SqlParseException {
        String sql = "SELECT db.users.id, db.users.name FROM db.users";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有，schema.table 格式）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("DB.USERS");
        Assert.assertTrue("应该包含 db.users 表", tables.contains("DB.USERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 DB.USERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，schema.table.field 格式）
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("DB.USERS.ID");
        expectedFields.add("DB.USERS.NAME");
        Assert.assertTrue("应该包含 db.users.id 字段", fields.contains("DB.USERS.ID"));
        Assert.assertTrue("应该包含 db.users.name 字段", fields.contains("DB.USERS.NAME"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段", fields.isEmpty());
    }
    
    @Test
    public void testGroupByHaving() throws SqlParseException {
        String sql = "SELECT user_id, SUM(amount) as total " +
                     "FROM orders " +
                     "GROUP BY user_id " +
                     "HAVING SUM(amount) > 1000";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("ORDERS");
        Assert.assertTrue("应该包含 orders 表", tables.contains("ORDERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 ORDERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段）
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("ORDERS.USER_ID");
        expectedFields.add("ORDERS.AMOUNT");
        Assert.assertTrue("应该包含 orders.user_id 字段", fields.contains("ORDERS.USER_ID"));
        Assert.assertTrue("应该包含 orders.amount 字段", fields.contains("ORDERS.AMOUNT"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段", fields.isEmpty());
    }
    
    @Test
    public void testOrderBy() throws SqlParseException {
        String sql = "SELECT id, name, age FROM users ORDER BY age DESC, name ASC";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段名（有且仅有，应该是原始表的字段，每个字段只出现一次）
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
    public void testLeftJoin() throws SqlParseException {
        String sql = "SELECT u.id, u.name, o.order_id " +
                     "FROM users u " +
                     "LEFT JOIN orders o ON u.id = o.user_id";
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
        Assert.assertTrue("应该包含 users.id 字段", fields.contains("USERS.ID"));
        Assert.assertTrue("应该包含 users.name 字段", fields.contains("USERS.NAME"));
        Assert.assertTrue("应该包含 orders.order_id 字段", fields.contains("ORDERS.ORDER_ID"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段", fields.isEmpty());
    }
    
    @Test
    public void testInvalidSQL() {
        String sql = "SELECT * FROM"; // 无效的 SQL
        try {
            parser.parse(sql);
            Assert.fail("应该抛出 SqlParseException");
        } catch (SqlParseException e) {
            // 预期会抛出异常
            Assert.assertNotNull("异常消息不应该为空", e.getMessage());
        }
    }
    
    @Test
    public void testParseAndFormat() {
        String sql = "SELECT id, name FROM users";
        String result = parser.parseAndFormat(sql);
        
        Assert.assertNotNull("结果不应该为空", result);
        Assert.assertTrue("结果应该包含 Tables", result.contains("Tables"));
        Assert.assertTrue("结果应该包含 Fields", result.contains("Fields"));
    }
    
    @Test
    public void testEmptyResult() throws SqlParseException {
        // 测试一个只包含字面量的查询（使用虚拟表）
        String sql = "SELECT 1 as num, 'test' as str FROM users WHERE 1 = 0";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS，不应该包含其他表", tables.isEmpty());
        
        // 应该没有字段（因为只有字面量，没有实际字段引用）
        Assert.assertTrue("应该没有字段", fields.isEmpty());
    }
    
    @Test
    public void testLiteralOnlyQuery() throws SqlParseException {
        // 测试一个只包含字面量和函数的查询
        String sql = "SELECT COUNT(*) as cnt FROM users WHERE 1 = 1";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名（有且仅有）
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS，不应该包含其他表", tables.isEmpty());
        
        // COUNT(*) 不会产生字段引用，cnt 是别名，不应该出现
        // WHERE 1 = 1 中的 1 是字面量，不应该被当作字段
        // 注意：如果 WHERE 子句中的 1 被解析为 SqlIdentifier，数字检查应该能捕获它
        // 如果没有 FROM 表信息，单个标识符不应该被当作字段
        Set<String> expectedFields = new HashSet<>();
        // 期望没有字段，所以 expectedFields 为空
        Assert.assertFalse("不应该包含 cnt 别名", fields.contains("CNT") || fields.contains("USERS.CNT"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该为空，不应该包含任何字段", fields.isEmpty());
    }
}
